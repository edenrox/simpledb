package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.util.Preconditions;

final class SumAggregation implements Aggregation {
  private static final String COLUMN_NAME = "sum";
  private final Column column;

  private int intSum;
  private double doubleSum;

  SumAggregation(Column column) {
    Preconditions.checkArgument(column.getType() == ColumnType.DOUBLE || column.getType() == ColumnType.INTEGER);
    this.column = column;
  }

  @Override
  public AggregationType getType() {
    return AggregationType.SUM;
  }

  @Override
  public void addValue(Object value) {
    if (column.getType() == ColumnType.INTEGER) {
      intSum += (Integer) value;
    } else { // ColumnType.DOUBLE
      doubleSum += (Double) value;
    }
  }

  @Override
  public Object getAggregate() {
    if (column.getType() == ColumnType.INTEGER) {
      return intSum;
    } else {
      return doubleSum;
    }
  }

  @Override
  public void reset() {
    intSum = 0;
    doubleSum = 0.0;
  }

  @Override
  public Column getOutputColumn() {
    if (column.getType() == ColumnType.INTEGER) {
      return Column.newIntColumn(COLUMN_NAME);
    } else {
      return Column.newDoubleColumn(COLUMN_NAME);
    }
  }
}
