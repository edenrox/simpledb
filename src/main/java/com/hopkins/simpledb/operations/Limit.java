package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.TupleDescriptor;

import java.util.NoSuchElementException;

/**
 * Created by edenrox on 5/31/2017.
 */
public class Limit implements DbIterator {

  private final DbIterator source;
  private final int limit;

  private int index;

  public Limit(DbIterator source, int limit) {
    this.source = source;
    this.limit = limit;
  }

  @Override
  public void open() {
    source.open();
    index = 0;
  }

  @Override
  public boolean hasNext() {
    return index < limit && source.hasNext();
  }

  @Override
  public Tuple next() {
    if (index >= limit) {
      throw new NoSuchElementException();
    }
    index++;
    return source.next();
  }

  @Override
  public TupleDescriptor getTupleDescriptor() {
    return source.getTupleDescriptor();
  }

  @Override
  public void reset() {
    source.reset();
    index = 0;
  }

  @Override
  public void close() {
    source.close();
  }
}
