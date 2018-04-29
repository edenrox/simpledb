package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

/**
 * An {@link Expression} which returns the value of a column.
 *
 * <p>Handles "[columnName]".
 */
public final class ColumnExpression implements Expression {
  private final String columnName;

  public ColumnExpression(String columnName) {
    this.columnName = columnName;
  }

  @Override
  public String getName() {
    return columnName;
  }

  @Override
  public ColumnType getType(Schema schema) {
    int columnIndex = schema.indexOf(columnName);
    return schema.getColumnType(columnIndex);
  }

  @Override
  public Object getValue(Record record) {
    return record.get(columnName);
  }

  @Override
  public String toString() {
    return "ColumnExpr {"
        + "columnName: " + columnName
        + '}';
  }
}
