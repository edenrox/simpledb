package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.util.Preconditions;

import java.util.Collections;
import java.util.NoSuchElementException;

public final class GenerateSeries implements DbIterator {
  public static final String COLUMN_NAME = "value";

  private final Schema schema;
  private final int start;
  private final int stop;
  private final int step;

  private int value;

  public GenerateSeries(int start, int stop, int step) {
    Preconditions.checkArgument(step != 0);

    this.schema = new Schema(Collections.singletonList(Column.newIntColumn(COLUMN_NAME)));
    this.start = start;
    this.stop = stop;
    this.step = step;
  }

  @Override
  public void open() {
    this.value = start;
  }

  private boolean isAscending() {
    return step > 0;
  }

  @Override
  public boolean hasNext() {
    if (isAscending()) {
      return value <= stop;
    } else {
      return value >= stop;
    }
  }

  @Override
  public Record next() throws NoSuchElementException {
    // Build the record to return
    Record record = new Record(schema);
    record.set(0, value);

    // Move to next
    value += step;

    // Return the record
    return record;
  }

  @Override
  public Schema getSchema() {
    return schema;
  }

  @Override
  public void close() { }
}
