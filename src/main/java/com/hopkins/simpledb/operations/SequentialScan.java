package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.Table;
import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.TupleDescriptor;

import java.util.NoSuchElementException;

/**
 * Created by edenrox on 5/29/2017.
 */
public class SequentialScan implements DbIterator {
  private final Table table;

  private int index;

  public SequentialScan(Table table) {
    this.table = table;
  }

  public void open() {
    index = 0;
  }

  public boolean hasNext() {
    return table.getSize() > index;
  }

  public Tuple next() {
    if (index >= table.getSize()) {
      throw new NoSuchElementException();
    }
    Tuple tuple = table.getRow(index);
    index++;
    return tuple;
  }

  public TupleDescriptor getTupleDescriptor() {
    return table.getTupleDescriptor();
  }

  public void reset() {
    index = 0;
  }

  public void close() { }
}
