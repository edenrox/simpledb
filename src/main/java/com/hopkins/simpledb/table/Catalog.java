package com.hopkins.simpledb.table;

import com.hopkins.simpledb.data.Schema;

/**
 * Created by ian_000 on 6/2/2017.
 */
public interface Catalog {

  Table getTable(int tableId);

  Table getTable(String name);

  Table createTable(String name, Schema schema);

  boolean hasTable(String name);

  void dropTable(String name);
}
