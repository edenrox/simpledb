package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;

public interface Aggregation {
  AggregationType getType();

  void addValue(Object value);

  Object getAggregate();

  void reset();

  Column getOutputColumn();
}
