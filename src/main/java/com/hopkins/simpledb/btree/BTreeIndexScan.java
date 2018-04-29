package com.hopkins.simpledb.btree;

import com.hopkins.simpledb.app.BTreeManager;
import com.hopkins.simpledb.catalog.IndexDescriptor;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.RecordCompare;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.operations.DbIterator;

import java.util.NoSuchElementException;

final class BTreeIndexScan implements DbIterator {

  private final BTreeManager bTreeManager;
  private final IndexDescriptor index;
  private final Record start;
  private final Record end;

  private Record next;

  BTreeIndexScan(BTreeManager bTreeManager, IndexDescriptor index, Record start, Record end) {
    this.bTreeManager = bTreeManager;
    this.index = index;
    this.start = start;
    this.end = end;
  }

  @Override
  public void open() {
    moveToFirst();
  }

  private void moveToFirst() {
    Record first = bTreeManager.findFirst(index, start);
    if (start == null || RecordCompare.compare(first, end) <= 0) {
      next = first;
    } else {
      next = null;
    }
  }

  private void moveToNext() {
    Record theNext = bTreeManager.findNext(index, next);
    if (end == null
        || theNext == null
        || RecordCompare.compare(theNext, end) <= 0) {
      next = theNext;
    } else {
      next = null;
    }
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public Record next() throws NoSuchElementException {
    Record record = next;
    moveToNext();;
    return record;
  }

  @Override
  public Schema getSchema() {
    return index.getSchema();
  }

  @Override
  public void close() {
    next = null;
  }
}
