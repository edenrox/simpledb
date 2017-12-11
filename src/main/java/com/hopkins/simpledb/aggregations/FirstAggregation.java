package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;

final class FirstAggregation implements Aggregation {
  private final Column column;
  private Object first;

  FirstAggregation(Column column) {
    this.column = column;
  }

  @Override
  public AggregationType getType() {
    return AggregationType.FIRST;
  }

  @Override
  public void addValue(Object value) {
    if (first == null) {
      first = value;
    }
  }

  @Override
  public Object getAggregate() {
    return first;
  }

  @Override
  public void reset() {
    first = null;
  }

  @Override
  public Column getOutputColumn() {
    return column;
  }
}
