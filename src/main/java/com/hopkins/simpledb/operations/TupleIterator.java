package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.data.Schema;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ian_000 on 5/31/2017.
 */
public class TupleIterator implements DbIterator {
  private final Schema schema;
  private final Iterable<Tuple> iterable;

  private Iterator<Tuple> iterator;

  public TupleIterator(Schema schema, Iterable<Tuple> iterable) {
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
  public Tuple next() throws NoSuchElementException {
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
