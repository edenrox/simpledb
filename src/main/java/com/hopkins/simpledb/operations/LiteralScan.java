package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.util.Preconditions;

import java.util.NoSuchElementException;

/**
 * Created by ian_000 on 7/7/2017.
 */
public class LiteralScan implements DbIterator {
  private final Record record;
  private boolean hasNext;

  public LiteralScan(Record record) {
    Preconditions.checkNotNull(record);
    this.record = record;
  }

  @Override
  public void open() {
    hasNext = true;
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public Record next() throws NoSuchElementException {
    if (!hasNext) {
      throw new NoSuchElementException();
    }
    hasNext = false;
    return record;
  }

  @Override
  public Schema getSchema() {
    return record.getSchema();
  }

  @Override
  public void close() {

  }
}
