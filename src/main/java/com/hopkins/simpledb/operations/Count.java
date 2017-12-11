package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

import java.util.Collections;
import java.util.NoSuchElementException;

public class Count implements DbIterator {
  private final Schema COUNT_SCHEMA = new Schema(Collections.singletonList(Column.newIntColumn("count")));

  private final DbIterator source;

  private boolean hasNext;

  public Count(DbIterator source) {
    this.source = source;
  }

  @Override
  public void open() {
    source.open();
    this.hasNext = true;
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public Record next() throws NoSuchElementException {
    hasNext = false;
    int count = 0;
    while (source.hasNext()) {
      count++;
      source.next();
    }
    Record record = new Record(COUNT_SCHEMA);
    record.set(0, count);
    return record;
  }

  @Override
  public Schema getSchema() {
    return COUNT_SCHEMA;
  }

  @Override
  public void close() {
    source.close();
  }
}
