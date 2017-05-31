package com.hopkins.simpledb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edenrox on 5/29/2017.
 */
public class Catalog {
  private final List<Table> tableList;

  public Catalog() {
    tableList = new ArrayList<>();
  }

  public boolean hasTable(String tableName) {
     return indexOf(tableName) != -1;
  }

  public Table getTable(String tableName) {
    int index = indexOf(tableName);
    if (index == -1) {
      return null;
    }
    return tableList.get(index);
  }

  public void dropTable(String tableName) {
    int index = indexOf(tableName);
    if (index >= 0) {
      tableList.remove(index);
    }
  }

  public void createTable(String tableName, TupleDescriptor tupleDescriptor) {
    int index = indexOf(tableName);
    if (index >= 0) {
      throw new IllegalArgumentException("Table already exists (name: " + tableName + ")");
    }
    tableList.add(Table.createTable(tableName, tupleDescriptor));
  }

  private int indexOf(String tableName) {
    for (int i = 0; i < tableList.size(); i++) {
      Table table = tableList.get(i);
      if (table.getName().equals(tableName)) {
        return i;
      }
    }
    return -1;
  }
}
