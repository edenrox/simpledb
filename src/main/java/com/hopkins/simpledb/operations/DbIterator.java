package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.data.Schema;

import java.util.NoSuchElementException;

/**
 * Created by edenrox on 5/29/2017.
 */
public interface DbIterator {

  void open();

  boolean hasNext();

  Tuple next() throws NoSuchElementException;

  Schema getSchema();

  void reset();

  void close();
}
