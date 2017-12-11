package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;

public class CountAggregation implements Aggregation {
  private static final String COLUMN_NAME = "count";

  private int count;

  @Override
  public AggregationType getType() {
    return AggregationType.COUNT;
  }

  @Override
  public void addValue(Object value) {
    count++;
  }

  @Override
  public Object getAggregate() {
    return count;
  }

  @Override
  public void reset() {
    count = 0;
  }

  @Override
  public Column getOutputColumn() {
    return Column.newIntColumn(COLUMN_NAME);
  }
}
