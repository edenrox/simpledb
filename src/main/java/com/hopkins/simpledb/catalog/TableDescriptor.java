package com.hopkins.simpledb.catalog;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.util.Preconditions;

/**
 * Represents a table in the database.  Includes the table name, schema, and root page number.
 *
 * <p>Corresponds to a table row in the {@link CatalogTable}.
 */
public final class TableDescriptor {
  private final String name;
  private final int rootPageNumber;
  private final Schema schema;

  public TableDescriptor(String name, int rootPageNumber, Schema schema) {
    Preconditions.checkNotEmpty(name);
    Preconditions.checkArgument(schema.getColumn(0).equals(Column.ROW_ID));

    this.name = name;
    this.rootPageNumber = rootPageNumber;
    this.schema = schema;
  }

  public String getName() {
    return name;
  }

  public int getRootPageNumber() {
    return rootPageNumber;
  }

  public Schema getSchema() {
    return schema;
  }

  @Override
  public String toString() {
    return "TableDesc {"
        + "name: " + name
        + ", rootPage: " + rootPageNumber
        + ", schema: " + schema
        + "}";
  }
}
