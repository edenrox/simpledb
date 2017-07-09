package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ian_000 on 5/31/2017.
 */
public class RecordIterator implements DbIterator {
  private final Schema schema;
  private final Iterable<Record> iterable;

  private Iterator<Record> iterator;

  public RecordIterator(Schema schema, Iterable<Record> iterable) {
    this.schema = schema;
    this.iterable = iterable;
  }

  @Override
  public void open() {
    iterator = iterable.iterator();
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public Record next() throws NoSuchElementException {
    return iterator.next();
  }

  @Override
  public Schema getSchema() {
    return schema;
  }

  @Override
  public void reset() {
    close();
    open();
  }

  @Override
  public void close() {
    iterator = null;
  }
}
