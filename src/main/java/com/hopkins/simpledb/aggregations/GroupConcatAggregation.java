package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;

final class GroupConcatAggregation implements Aggregation {
  private static final String COLUMN_NAME = "group_concat";
  private final String separator;

  public GroupConcatAggregation(String separator) {
    this.separator = separator;
  }

  private StringBuilder builder = new StringBuilder();

  @Override
  public AggregationType getType() {
    return AggregationType.GROUP_CONCAT;
  }

  @Override
  public void addValue(Object value) {
    if (builder.length() > 0) {
      builder.append(separator);
    }
    builder.append(value);
  }

  @Override
  public Object getAggregate() {
    return builder.toString();
  }

  @Override
  public void reset() {
    builder.setLength(0);
  }

  @Override
  public Column getOutputColumn() {
    return Column.newStringColumn(COLUMN_NAME, 200);
  }
}
