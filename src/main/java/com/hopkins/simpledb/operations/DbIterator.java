package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.TupleDescriptor;

import java.util.NoSuchElementException;

/**
 * Created by edenrox on 5/29/2017.
 */
public interface DbIterator {

  void open();

  boolean hasNext();

  Tuple next() throws NoSuchElementException;

  TupleDescriptor getTupleDescriptor();

  void reset();

  void close();
}
