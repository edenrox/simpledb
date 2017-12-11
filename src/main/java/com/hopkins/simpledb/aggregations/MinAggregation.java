package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.util.Preconditions;

final class MinAggregation implements Aggregation {
  private static final String COLUMN_NAME = "min";
  private final Column column;

  private Comparable minValue;

  MinAggregation(Column column) {
    Preconditions.checkArgument(column.getType() != ColumnType.BLOB);
    this.column = column;
  }

  @Override
  public AggregationType getType() {
    return AggregationType.MIN;
  }

  @Override
  public void addValue(Object value) {
    Comparable comparableValue = getComparable(value);
    if (minValue == null || minValue.compareTo(comparableValue) > 0) {
      minValue = comparableValue;
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
    return minValue;
  }

  @Override
  public void reset() {
    minValue = null;
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
