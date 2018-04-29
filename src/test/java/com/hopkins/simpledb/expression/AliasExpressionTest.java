package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

public class AliasExpressionTest {
  private static final String STRING_VALUE = "test";
  private static final int INT_VALUE = 12;

  private Schema schema;
  private Record record;
  private LiteralExpression stringExpr;
  private LiteralExpression intExpr;

  @Before
  public void setup() {
    schema = new Schema(Collections.singletonList(Column.newIntColumn("test")));
    record = new Record(schema);
    stringExpr = new LiteralExpression(ColumnType.STRING, STRING_VALUE);
    intExpr = new LiteralExpression(ColumnType.INTEGER, INT_VALUE);
  }

  @Test
  public void getName_returnsName() {
    String columnName = "boom";
    AliasExpression expr = new AliasExpression(stringExpr, columnName);

    assertThat(expr.getName()).isEqualTo(columnName);
  }

  @Test
  public void getType_returnsTypeFromExpr() {
    AliasExpression expr;

    expr = new AliasExpression(stringExpr, "banana");
    assertThat(expr.getType(schema)).isEqualTo(stringExpr.getType(schema));

    expr = new AliasExpression(intExpr, "apple");
    assertThat(expr.getType(schema)).isEqualTo(intExpr.getType(schema));
  }

  @Test
  public void getValue_returnsValueFromExpr() {
    AliasExpression expr;

    expr = new AliasExpression(stringExpr, "banana");
    assertThat(expr.getValue(record)).isEqualTo(stringExpr.getValue(record));

    expr = new AliasExpression(intExpr, "apple");
    assertThat(expr.getValue(record)).isEqualTo(intExpr.getValue(record));
  }

}
