package com.hopkins.simpledb.catalog;

import com.hopkins.simpledb.app.*;
import com.hopkins.simpledb.btree.BTreePage;
import com.hopkins.simpledb.data.*;
import com.hopkins.simpledb.expression.*;
import com.hopkins.simpledb.heap.HeapPage;
import com.hopkins.simpledb.operations.DbIterator;
import com.hopkins.simpledb.operations.Projection;
import com.hopkins.simpledb.operations.Selection;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of the tables, indexes, and views in the database.  Reads and writes data to/from the
 * {@link CatalogTable}.
 */
public class CatalogManagerImpl implements CatalogManager {
  private static final int CATALOG_TABLE_ROOT_PAGE = 0;

  private final Config config;
  private final FreePageManager freePageManager;
  private final HeapManager heapManager;
  private final CacheManager cacheManager;
  private final TableDescriptor catalogTableDescriptor;
  private final MutationManager mutationManager;

  public CatalogManagerImpl(Config config, FreePageManager freePageManager, HeapManager heapManager, CacheManager cacheManager, MutationManager mutationmanager) {
    this.config = config;
    this.freePageManager = freePageManager;
    this.heapManager = heapManager;
    this.cacheManager = cacheManager;
    this.mutationManager = mutationmanager;
    this.catalogTableDescriptor =
        new TableDescriptor(
            config.getCatalogTableName(), CatalogTable.getRootPageNumber(), CatalogTable.getSchema());
  }

  @Override
  public boolean hasTable(String name) {
    return getTable(name) != null;
  }

  private boolean isCatalogTable(String name) {
    return config.getCatalogTableName().equalsIgnoreCase(name);
  }

  @Override
  public TableDescriptor getTable(String name) {
    if (isCatalogTable(name)) {
      return catalogTableDescriptor;
    }
    name = name.toLowerCase();

    Record record =
        findInCatalog(
            new String[]{CatalogTable.Columns.ROOT_PAGE, CatalogTable.Columns.DATA},
            name,
            CatalogType.TABLE);
    if (record == null) {
      return null;
    }

    int rootPageNumber = (Integer) record.get(CatalogTable.Columns.ROOT_PAGE);
    byte[] data = (byte[]) record.get(CatalogTable.Columns.DATA);
    ByteReader byteReader = new ByteReader(data);
    Schema schema = SchemaIo.readSchema(byteReader);
    return new TableDescriptor(name, rootPageNumber, schema);
  }

  @Override
  public void createTable(String name, Schema schema) {
    if (hasTable(name)) {
      throw new IllegalArgumentException("Can not CREATE TABLE, already exists: " + name);
    }
    name = name.toLowerCase();

    // allocate a root page
    int rootPageNumber = freePageManager.allocPage();
    Page tableRootPage = cacheManager.getPage(rootPageNumber);
    HeapPage.initializePage(tableRootPage, true);
    tableRootPage.unpin();

    // Create a record for this table in the catalog table
    Record record = CatalogTable.createRecord(CatalogType.TABLE, name, /* tableName= */ "", rootPageNumber, schema);
    mutationManager.insert(catalogTableDescriptor, record);
  }

  @Override
  public void dropTable(String name) {
    name = name.toLowerCase();
    if (isCatalogTable(name)) {
      throw new IllegalArgumentException("Can not DROP system TABLE: " + name);
    }
    if (!hasTable(name)) {
      throw new IllegalArgumentException("Can not DROP TABLE, does not exist: " + name);
    }

    // free all pages from the table

    // Drop all the indexes on this table
    List<String> indexNameList = findTableIndexNames(name);
    for (String indexName : indexNameList) {
      dropIndex(indexName);
    }

    // Drop the table from the catalog
    mutationManager.delete(config.getCatalogTableName(), buildTypeAndNameExpression(CatalogType.TABLE, name));
  }

  @Override
  public boolean hasIndex(String name) {
    return getIndex(name) != null;
  }

  @Override
  public IndexDescriptor getIndex(String name) {
    name = name.toLowerCase();

    Record record = findInCatalog(
            new String[] {CatalogTable.Columns.ROOT_PAGE, CatalogTable.Columns.TABLE_NAME, CatalogTable.Columns.NAME},
            name,
            CatalogType.INDEX);

    if (record == null) {
      return null;
    }

    int rootPageNumber = record.getInt(CatalogTable.Columns.ROOT_PAGE);
    String tableName = (String) record.get(CatalogTable.Columns.TABLE_NAME);
    String indexName = (String) record.get(CatalogTable.Columns.NAME);
    byte[] data = (byte[]) record.get(CatalogTable.Columns.DATA);
    ByteReader byteReader = new ByteReader(data);
    Schema schema = SchemaIo.readSchema(byteReader);

    return new IndexDescriptor(tableName, indexName, rootPageNumber, schema);
  }

  @Override
  public void createIndex(String name, String tableName, Schema indexColumns) {
    name = name.toLowerCase();
    tableName = tableName.toLowerCase();
    if (hasIndex(name)) {
      throw new IllegalArgumentException("Can not CREATE INDEX, already exists: " + name);
    }
    TableDescriptor tableDescriptor = getTable(name);
    if (tableDescriptor == null) {
      throw new IllegalArgumentException("Can not CREATE INDEX, table does not exist: " + tableName);
    }
    Schema tableSchema = tableDescriptor.getSchema();
    for (int i = 0; i < indexColumns.getColumnCount(); i++) {
      Column column = indexColumns.getColumn(i);
      int tableColumnIndex = tableSchema.indexOf(column.getName());
      if (tableColumnIndex == -1) {
        throw new IllegalArgumentException("Column in index does not exist in table: " + column);
      }
    }

    // allocate a root page
    int rootPageNumber = freePageManager.allocPage();

    // initialize the root index page
    Page indexRootPage = cacheManager.getPage(rootPageNumber);
    BTreePage.initializePage(indexRootPage, /* isLeaf= */ true);
    indexRootPage.unpin();

    Record record = CatalogTable.createRecord(CatalogType.TABLE, name, null, rootPageNumber, indexColumns);

    // INSERT INTO simpledb_master
    mutationManager.insert(catalogTableDescriptor, record);

    // Populate the index with the table data

  }

  @Override
  public List<IndexDescriptor> findIndexes(String tableName) {
    List<String> indexNames = findTableIndexNames(tableName);
    List<IndexDescriptor> indexList = new ArrayList<>(indexNames.size());
    for (String indexName : indexNames) {
      indexList.add(getIndex(indexName));
    }
    return indexList;
  }

  @Override
  public void dropIndex(String name) {
    name = name.toLowerCase();

    if (!hasIndex(name)) {
      throw new IllegalArgumentException("Can not DROP INDEX, does not exist: " + name);
    }

    // DELETE FROM simpledb_master WHERE type=index AND name={name}
    mutationManager.delete(config.getCatalogTableName(), buildTypeAndNameExpression(CatalogType.INDEX, name));
  }

  private List<String> findTableIndexNames(String tableName) {
    DbIterator scan = heapManager.getScan(catalogTableDescriptor);
    Expression filter = buildTypeAndTableNameExpression(CatalogType.INDEX, tableName);
    DbIterator query = new Projection(new Selection(scan, filter), CatalogTable.Columns.NAME);

    List<String> indexNames = new ArrayList<>();
    try {
      query.open();
      while (query.hasNext()) {
        Record record = query.next();
        indexNames.add((String) record.get(CatalogTable.Columns.NAME));
      }
      return indexNames;
    } finally {
      query.close();
    }
  }

  private Record findInCatalog(String[] columns, String name, CatalogType type) {
    // SELECT columns FROM catalog WHERE type={type} AND name={name}
    DbIterator scan = heapManager.getScan(catalogTableDescriptor);
    Expression filter = buildTypeAndNameExpression(type, name);
    DbIterator query = new Projection(new Selection(scan, filter), columns);
    try {
      query.open();
      if (query.hasNext()) {
        return query.next();
      } else {
        return null;
      }
    } finally {
      query.close();
    }
  }

  private Expression buildTypeAndNameExpression(CatalogType type, String name) {
    return new BooleanExpression(
        new ComparisonExpression(
            new ColumnExpression(CatalogTable.Columns.TYPE),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new LiteralExpression(ColumnType.STRING, type.name())),
        BooleanExpression.BooleanOperator.AND,
        new ComparisonExpression(
            new ColumnExpression(CatalogTable.Columns.NAME),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new LiteralExpression(ColumnType.STRING, name)));
  }

  private Expression buildTypeAndTableNameExpression(CatalogType type, String tableName) {
    return new BooleanExpression(
        new ComparisonExpression(
            new ColumnExpression(CatalogTable.Columns.TYPE),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new LiteralExpression(ColumnType.STRING, type.name())),
        BooleanExpression.BooleanOperator.AND,
        new ComparisonExpression(
            new ColumnExpression(CatalogTable.Columns.TABLE_NAME),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new LiteralExpression(ColumnType.STRING, tableName)));
  }
}
