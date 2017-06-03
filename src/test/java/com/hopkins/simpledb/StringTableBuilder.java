package com.hopkins.simpledb;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edenrox on 5/31/2017.
 */
public class StringTableBuilder {

  private String name;
  private String[] columns;
  private List<String[]> rows = new ArrayList<>();

  public StringTableBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public StringTableBuilder setColumns(String... columns) {
    this.columns = columns;
    return this;
  }

  public StringTableBuilder addRow(String... values) {
    rows.add(values);
    return this;
  }

  public Table build() {
    List<Column> columnDescList = new ArrayList<>();
    for (String column : columns) {
      columnDescList.add(Column.newStringColumn(column, 20));
    }
    Schema desc = new Schema(columnDescList);
    Table table = Table.createTable(name, desc);
    for (String[] row : rows) {
      Tuple tuple = table.createTuple();
      for (int i = 0; i < row.length; i++) {
        tuple.setString(i, row[i]);
      }
      table.insert(tuple);
    }
    return table;
  }
}
