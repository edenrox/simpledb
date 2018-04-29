package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

/**
 * An {@link Expression} which provides an alias (aka name) for a value.
 *
 * <p>Handles "[expr] AS [name]" in projection.
 */
public final class AliasExpression implements Expression {
  private final Expression expression;
  private final String name;

  public AliasExpression(Expression expression, String name) {
    this.expression = expression;
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ColumnType getType(Schema schema) {
    return expression.getType(schema);
  }

  @Override
  public Object getValue(Record record) {
    return expression.getValue(record);
  }
}
