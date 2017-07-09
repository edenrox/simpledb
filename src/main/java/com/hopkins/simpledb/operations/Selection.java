package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.predicates.Predicate;

import java.util.NoSuchElementException;

/**
 * Created by ian_000 on 7/2/2017.
 */
public class Selection implements DbIterator {
  private final Predicate predicate;
  private final DbIterator source;

  private Record nextRecord;

  public Selection(DbIterator source, Predicate predicate) {
    this.source = source;
    this.predicate = predicate;
  }

  @Override
  public void open() {
    source.open();
    nextRecord = findNextRecord();
  }

  private Record findNextRecord() {
    while (source.hasNext()) {
      Record record = source.next();
      if (predicate.matches(record)) {
        return record;
      }
    }
    return null;
  }

  @Override
  public boolean hasNext() {
    return nextRecord != null;
  }

  @Override
  public Record next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Record record = nextRecord;
    nextRecord = findNextRecord();
    return record;
  }

  @Override
  public Schema getSchema() {
    return source.getSchema();
  }

  @Override
  public void reset() {
    source.reset();
    nextRecord = null;
  }

  @Override
  public void close() {
    source.close();
  }
}
