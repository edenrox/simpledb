package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

import java.util.NoSuchElementException;

/**
 * Created by edenrox on 5/31/2017.
 */
public class Limit implements DbIterator {
  private final DbIterator source;
  private final int offset;
  private final int limit;

  private int index;

  public Limit(DbIterator source, int offset, int limit) {
    this.source = source;
    this.offset = offset;
    this.limit = limit;
  }

  private void moveToFirst() {
    while (index < offset && source.hasNext()) {
      source.next();
      index++;
    }
  }

  @Override
  public void open() {
    source.open();
    index = 0;
    moveToFirst();
  }

  @Override
  public boolean hasNext() {
    return (index - offset) < limit && source.hasNext();
  }

  @Override
  public Record next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    index++;
    return source.next();
  }

  @Override
  public Schema getSchema() {
    return source.getSchema();
  }

  @Override
  public void close() {
    source.close();
  }

  @Override
  public String toString() {
    return "Limit {offset: " + offset + ", limit: " + limit + "}";
  }
}
