package com.hopkins.simpledb.catalog;

import com.hopkins.simpledb.app.CatalogManager;
import com.hopkins.simpledb.app.Config;
import com.hopkins.simpledb.app.FreePageManager;
import com.hopkins.simpledb.app.HeapManager;
import com.hopkins.simpledb.data.*;
import com.hopkins.simpledb.operations.DbIterator;
import com.hopkins.simpledb.operations.Projection;
import com.hopkins.simpledb.operations.Selection;
import com.hopkins.simpledb.predicates.AndPredicate;
import com.hopkins.simpledb.predicates.EqualsLiteralPredicate;
import com.hopkins.simpledb.predicates.Predicate;

public class CatalogManagerImpl implements CatalogManager {
  private static final int CATALOG_TABLE_ROOT_PAGE = 0;

  private final Config config;
  private final FreePageManager freePageManager;
  private final HeapManager heapManager;
  private final TableDescriptor catalogTableDescriptor;

  public CatalogManagerImpl(Config config, FreePageManager freePageManager, HeapManager heapManager) {
    this.config = config;
    this.freePageManager = freePageManager;
    this.heapManager = heapManager;
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
    if (record != null) {
      int rootPageNumber = (Integer) record.get(CatalogTable.Columns.ROOT_PAGE);
      byte[] data = (byte[]) record.get(CatalogTable.Columns.DATA);
      ByteReader byteReader = new ByteReader(data);
      Schema schema = SchemaIo.readSchema(byteReader);
      return new TableDescriptor(name, rootPageNumber, schema);
    }

    return null;
  }

  @Override
  public void createTable(String name, Schema schema) {
    if (hasTable(name)) {
      throw new IllegalArgumentException("Can not CREATE TABLE, already exists: " + name);
    }
    name = name.toLowerCase();

    // allocate a root page
    int rootPageNumber = freePageManager.allocPage();

    Record record = new Record(CatalogTable.getSchema());
    record.set(CatalogTable.Columns.TYPE, CatalogType.TABLE.name());
    record.set(CatalogTable.Columns.NAME, name);
    record.set(CatalogTable.Columns.ROOT_PAGE, rootPageNumber);
    byte[] schemaBytes = new byte[CatalogTable.DATA_LENGTH];
    ByteWriter byteWriter = new ByteWriter(schemaBytes);
    SchemaIo.writeSchema(byteWriter, schema);
    record.set(CatalogTable.Columns.DATA, schemaBytes);


    // INSERT INTO simpledb_master (type, name, table_name, root_page, schema);
    heapManager.insert(catalogTableDescriptor, record);

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

    // DELETE FROM simpledb_master WHERE type=table AND name={name}
    Record record =
        findInCatalog(
            new String[]{CatalogTable.Columns.ROW_ID},
            name,
            CatalogType.TABLE);
    if (record != null) {
      int rowId = (Integer) record.get(CatalogTable.Columns.ROW_ID);
      heapManager.delete(catalogTableDescriptor, rowId);
    }
  }

  private Record findInCatalog(String[] columns, String name, CatalogType type) {
    // SELECT columns FROM catalog WHERE type={type} AND name={name}
    DbIterator scan = heapManager.getScan(catalogTableDescriptor);
    Predicate filter = new AndPredicate(
        new EqualsLiteralPredicate(CatalogTable.Columns.TYPE, type.name()),
        new EqualsLiteralPredicate(CatalogTable.Columns.NAME, name));
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
}
