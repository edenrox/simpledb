package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

import java.util.NoSuchElementException;

/**
 * Created by edenrox on 5/29/2017.
 */
public interface DbIterator {

  void open();

  boolean hasNext();

  Record next() throws NoSuchElementException;

  Schema getSchema();

  void reset();

  void close();
}
