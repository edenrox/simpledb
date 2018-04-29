package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

public class BooleanExpressionTest {
  private Schema schema;
  private Record record;
  private Expression trueExpr;
  private Expression falseExpr;

  @Before
  public void setup() {
    schema = new Schema(Collections.singletonList(Column.newIntColumn("test")));
    record = new Record(schema);
    trueExpr = new LiteralExpression(ColumnType.BOOL, Boolean.TRUE);
    falseExpr = new LiteralExpression(ColumnType.BOOL, Boolean.FALSE);
  }

  @Test
  public void getType_returnsBool() {
    BooleanExpression expr = new BooleanExpression(trueExpr, BooleanExpression.BooleanOperator.AND, falseExpr);
    assertThat(expr.getType(schema)).isEqualTo(ColumnType.BOOL);
  }

  @Test
  public void and() {
    BooleanExpression expr;

    expr = new BooleanExpression(falseExpr, BooleanExpression.BooleanOperator.AND, falseExpr);
    assertThat(expr.getValue(record)).isFalse();

    expr = new BooleanExpression(trueExpr, BooleanExpression.BooleanOperator.AND, falseExpr);
    assertThat(expr.getValue(record)).isFalse();

    expr = new BooleanExpression(trueExpr, BooleanExpression.BooleanOperator.AND, trueExpr);
    assertThat(expr.getValue(record)).isTrue();
  }

  @Test
  public void or() {
    BooleanExpression expr;

    expr = new BooleanExpression(falseExpr, BooleanExpression.BooleanOperator.OR, falseExpr);
    assertThat(expr.getValue(record)).isFalse();

    expr = new BooleanExpression(trueExpr, BooleanExpression.BooleanOperator.OR, falseExpr);
    assertThat(expr.getValue(record)).isTrue();

    expr = new BooleanExpression(trueExpr, BooleanExpression.BooleanOperator.OR, trueExpr);
    assertThat(expr.getValue(record)).isTrue();
  }
}
