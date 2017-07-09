package com.hopkins.simpledb.table;

import com.hopkins.simpledb.data.Schema;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class Table {
  private final int id;
  private final String name;
  private final Schema schema;

  public Table(int id, String name, Schema schema) {
    this.id = id;
    this.name = name;
    this.schema = schema;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Schema getSchema() {
    return schema;
  }
}
