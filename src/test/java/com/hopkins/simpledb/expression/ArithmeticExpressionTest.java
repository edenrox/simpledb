package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

public class ArithmeticExpressionTest {
  private Schema schema;
  private Record record;

  @Before
  public void setup() {
    schema = new Schema(Collections.singletonList(Column.newIntColumn("test")));
    record = new Record(schema);
  }

  @Test
  public void getValue_withIntegerAddition_returnsCorrectInteger() {
    ArithmeticExpression expr =
        new ArithmeticExpression(
            new LiteralExpression(ColumnType.INTEGER, 12),
            ArithmeticExpression.ArithmeticOperation.ADDITION,
            new LiteralExpression(ColumnType.INTEGER, -5));

    Object value = expr.getValue(record);
    assertThat(value).isInstanceOf(Integer.class);
    assertThat(value).isEqualTo(7);
  }

  @Test
  public void getValue_withIntegerAndDoubleAddition_returnsCorrectInteger() {
    ArithmeticExpression expr =
        new ArithmeticExpression(
            new LiteralExpression(ColumnType.INTEGER, 12),
            ArithmeticExpression.ArithmeticOperation.ADDITION,
            new LiteralExpression(ColumnType.DOUBLE, -5.0));

    Object value = expr.getValue(record);
    assertThat(value).isInstanceOf(Double.class);
    assertThat(value).isEqualTo(7.0);
  }

  @Test
  public void getType_withIntegerAddition_returnsInteger() {
    ArithmeticExpression expr =
        new ArithmeticExpression(
            new LiteralExpression(ColumnType.INTEGER, 12),
            ArithmeticExpression.ArithmeticOperation.ADDITION,
            new LiteralExpression(ColumnType.INTEGER, -5));

    assertThat(expr.getType(schema)).isEqualTo(ColumnType.INTEGER);
  }
}
