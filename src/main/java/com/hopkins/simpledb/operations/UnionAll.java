package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.util.Preconditions;

import java.util.NoSuchElementException;

/**
 * Created by ian_000 on 6/3/2017.
 */
public class UnionAll implements DbIterator {

  private final DbIterator first;
  private final DbIterator second;

  public UnionAll(DbIterator first, DbIterator second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public void open() {
    first.open();
    second.open();
    Preconditions.checkState(first.getSchema().equals(second.getSchema()));
  }

  @Override
  public Schema getSchema() {
    return first.getSchema();
  }

  @Override
  public boolean hasNext() {
    return first.hasNext() || second.hasNext();
  }

  @Override
  public Tuple next() throws NoSuchElementException {
    if (first.hasNext()) {
      return first.next();
    } else {
      return second.next();
    }
  }

  @Override
  public void reset() {
    first.reset();
    second.reset();
  }

  @Override
  public void close() {
    first.close();
    second.close();
  }
}
