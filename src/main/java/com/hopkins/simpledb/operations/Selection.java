package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.expression.Expression;
import com.hopkins.simpledb.util.Preconditions;

import java.util.NoSuchElementException;

/**
 * Created by ian_000 on 7/2/2017.
 */
public class Selection implements DbIterator {
  private final Expression expression;
  private final DbIterator source;

  private Record nextRecord;

  public Selection(DbIterator source, Expression expression) {
    Preconditions.checkNotNull(source);
    this.source = source;
    this.expression = expression;
  }

  @Override
  public void open() {
    source.open();
    Preconditions.checkState(expression.getType(source.getSchema()) == ColumnType.BOOL);
    nextRecord = findNextRecord();
  }

  private Record findNextRecord() {
    while (source.hasNext()) {
      Record record = source.next();
      boolean result = (Boolean) expression.getValue(record);
      if (result) {
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
  public void close() {
    source.close();
    nextRecord = null;
  }
}
