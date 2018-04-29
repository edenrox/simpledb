package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringRecordIteratorBuilder;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.expression.ColumnExpression;
import com.hopkins.simpledb.expression.ComparisonExpression;
import com.hopkins.simpledb.expression.Expression;
import com.hopkins.simpledb.expression.LiteralExpression;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by ian_000 on 7/8/2017.
 */
public class SelectionTest {

  private RecordIterator studentsIterator;

  @Before
  public void setup() {
    studentsIterator =
        new StringRecordIteratorBuilder()
            .setName("students")
            .setColumns("sid", "sname")
            .addRow("1", "Bobby")
            .addRow("2", "Robin")
            .addRow("3", "Inder")
            .addRow("4", "Tabitha")
            .addRow("5", "Jessica")
            .addRow("6", "Inder")
            .build();
  }

  @Test
  public void next_returnsOnlyMatchingRows() {
    Expression expr =
        new ComparisonExpression(
            new ColumnExpression("sname"),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new LiteralExpression(ColumnType.STRING, "Inder"));
    Selection selection = new Selection(studentsIterator, expr);
    selection.open();

    Record record = selection.next();
    assertThat(record.get("sid")).isEqualTo("3");
    assertThat(record.get("sname")).isEqualTo("Inder");

    record = selection.next();
    assertThat(record.get("sid")).isEqualTo("6");
    assertThat(record.get("sname")).isEqualTo("Inder");

    selection.close();
  }

  @Test
  public void hasNext_returnsTrueUntilEndOfMatchingRows() {
    Expression expr =
        new ComparisonExpression(
            new ColumnExpression("sname"),
            ComparisonExpression.ComparisonOperator.EQUAL,
            new LiteralExpression(ColumnType.STRING, "Inder"));
    Selection selection = new Selection(studentsIterator, expr);
    selection.open();

    assertThat(selection.hasNext()).isTrue();
    selection.next();
    assertThat(selection.hasNext()).isTrue();
    selection.next();
    assertThat(selection.hasNext()).isFalse();

    selection.close();
  }
}
