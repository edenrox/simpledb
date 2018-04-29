package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

/**
 * An {@link Expression} which returns a literal value.
 */
public final class LiteralExpression implements Expression {
  private final ColumnType type;
  private final Object value;

  public LiteralExpression(ColumnType type, Object value) {
    this.type = type;
    this.value = value;
  }

  @Override
  public ColumnType getType(Schema schema) {
    return type;
  }

  @Override
  public Object getValue(Record record) {
    return value;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String toString() {
    return "LiteralExpr {"
        + "type: " + type
        + ", value: " + value
        + '}';
  }
}
