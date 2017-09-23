package com.hopkins.simpledb.catalog;

import com.hopkins.simpledb.data.Schema;

public class TableDescriptor {
  private final String name;
  private final int rootPageNumber;
  private final Schema schema;

  public TableDescriptor(String name, int rootPageNumber, Schema schema) {
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
