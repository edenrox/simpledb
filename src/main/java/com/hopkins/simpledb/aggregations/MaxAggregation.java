package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.util.Preconditions;

final class MaxAggregation implements Aggregation {
  private static final String COLUMN_NAME = "max";
  private final Column column;

  private Comparable maxValue;

  MaxAggregation(Column column) {
    Preconditions.checkArgument(column.getType() != ColumnType.BLOB);
    this.column = column;
  }

  @Override
  public AggregationType getType() {
    return AggregationType.MAX;
  }

  @Override
  public void addValue(Object value) {
    Comparable comparableValue = getComparable(value);
    if (maxValue == null || maxValue.compareTo(comparableValue) < 0) {
      maxValue = comparableValue;
    }
  }

  private Comparable getComparable(Object value) {
    switch (column.getType()) {
      case STRING:
        return (String) value;
      case BOOL:
        return (Boolean) value;
      case INTEGER:
        return (Integer) value;
      case DOUBLE:
      default:
        return (Double) value;
    }
  }

  @Override
  public Object getAggregate() {
    return maxValue;
  }

  @Override
  public void reset() {
    maxValue = null;
  }

  @Override
  public Column getOutputColumn() {
    switch (column.getType()) {
      case STRING:
        return Column.newStringColumn(COLUMN_NAME, column.getLength());
      case BOOL:
        return Column.newBoolColumn(COLUMN_NAME);
      case INTEGER:
        return Column.newIntColumn(COLUMN_NAME);
      case DOUBLE:
      default:
        return Column.newDoubleColumn(COLUMN_NAME);
    }
  }
}
