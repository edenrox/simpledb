package com.hopkins.simpledb;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.operations.RecordIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edenrox on 5/31/2017.
 */
public class StringRecordIteratorBuilder {

  private String name;
  private String[] columns;
  private List<String[]> rows = new ArrayList<>();

  public StringRecordIteratorBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public StringRecordIteratorBuilder setColumns(String... columns) {
    this.columns = columns;
    return this;
  }

  public StringRecordIteratorBuilder addRow(String... values) {
    rows.add(values);
    return this;
  }

  public RecordIterator build() {
    List<Column> columnDescList = new ArrayList<>();
    for (String column : columns) {
      columnDescList.add(Column.newStringColumn(column, 20));
    }
    Schema schema = new Schema(columnDescList);
    List<Record> recordList = new ArrayList<>(rows.size());
    for (String[] row : rows) {
      Record record = new Record(schema);
      for (int i = 0; i < row.length; i++) {
        record.set(i, row[i]);
      }
      recordList.add(record);
    }
    return new RecordIterator(schema, recordList);
  }
}
