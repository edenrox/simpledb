package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.util.Preconditions;

final class AverageAggregation implements Aggregation {
  private static final String COLUMN_NAME = "average";
  private final Column column;

  private double sum;
  private int count;

  AverageAggregation(Column column) {
    Preconditions.checkArgument(column.getType() == ColumnType.DOUBLE || column.getType() == ColumnType.INTEGER);
    this.column = column;
  }

  @Override
  public AggregationType getType() {
    return AggregationType.AVERAGE;
  }

  @Override
  public void addValue(Object value) {
    if (column.getType() == ColumnType.INTEGER) {
      sum += (Integer) value;
    } else { // ColumnType.DOUBLE
      sum += (Double) value;
    }
    count++;
  }

  @Override
  public Object getAggregate() {
    return sum / count;
  }

  @Override
  public void reset() {
    sum = 0.0;
    count = 0;
  }

  @Override
  public Column getOutputColumn() {
    return Column.newDoubleColumn(COLUMN_NAME);
  }
}
