package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;

public class AggregationFactory {
  public Aggregation getAggregation(AggregationType type, Column column, String separator) {
    switch (type) {
      case MAX:
        return new MaxAggregation(column);
      case MIN:
        return new MinAggregation(column);
      case SUM:
        return new SumAggregation(column);
      case COUNT:
        return new CountAggregation();
      case FIRST:
        return new FirstAggregation(column);
      case AVERAGE:
        return new AverageAggregation(column);
      case GROUP_CONCAT:
        return new GroupConcatAggregation(separator);
      default:
        throw new IllegalArgumentException("Unknown aggregation type: " + type);
    }
  }
}
