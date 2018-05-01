package com.hopkins.simpledb.catalog;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.util.Preconditions;

/**
 * Represents an index on a table in the database.  Includes the table name, index name, index schema, and root page
 * number for the index.
 *
 * <p>Corresponds to an index row in the {@link CatalogTable}.
 */
public final class IndexDescriptor {
  private final String tableName;
  private final String indexName;
  private final int rootPageNumber;
  private final Schema schema;

  public IndexDescriptor(String tableName, String indexName, int rootPageNumber, Schema schema) {
    Preconditions.checkNotEmpty(tableName);
    Preconditions.checkNotEmpty(indexName);
    Preconditions.checkArgument(rootPageNumber > 0);
    Preconditions.checkArgument(schema.getColumnCount() > 1);
    Preconditions.checkArgument(isLastColumnRecordId(schema));

    this.tableName = tableName;
    this.indexName = indexName;
    this.rootPageNumber = rootPageNumber;
    this.schema = schema;
  }

  private boolean isLastColumnRecordId(Schema schema) {
    Column lastColumn = schema.getColumn(schema.getColumnCount() - 1);
    return lastColumn.equals(Column.ROW_ID);
  }

  public String getTableName() {
    return tableName;
  }

  public String getIndexName() {
    return indexName;
  }

  public int getRootPageNumber() {
    return rootPageNumber;
  }

  public Schema getSchema() {
    return schema;
  }

  @Override
  public String toString() {
    return "IndexDescriptor{"
        + "tableName: " + tableName
        + ", indexName: " + indexName
        + ", rootPage: " + rootPageNumber
        + ", schema: " + schema
        + '}';
  }
}
