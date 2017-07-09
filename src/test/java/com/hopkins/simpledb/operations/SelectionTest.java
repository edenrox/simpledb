package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringRecordIteratorBuilder;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.predicates.EqualsLiteralPredicate;
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
    EqualsLiteralPredicate predicate = new EqualsLiteralPredicate("sname", "Inder");
    Selection selection = new Selection(studentsIterator, predicate);
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
    EqualsLiteralPredicate predicate = new EqualsLiteralPredicate("sname", "Inder");
    Selection selection = new Selection(studentsIterator, predicate);
    selection.open();

    assertThat(selection.hasNext()).isTrue();
    selection.next();
    assertThat(selection.hasNext()).isTrue();
    selection.next();
    assertThat(selection.hasNext()).isFalse();

    selection.close();
  }
}
