package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringRecordIteratorBuilder;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class NestedLoopJoinTest {

  private RecordIterator studentsIterator;
  private RecordIterator coursesIterator;

  private RecordIterator emptyStudentsIterator;
  private RecordIterator emptyCoursesIterator;

  @Before
  public void setup() {
    studentsIterator =
        new StringRecordIteratorBuilder()
            .setName("students")
            .setColumns("sid", "sname")
            .addRow("1", "Bobby")
            .addRow("2", "Robin")
            .build();

    coursesIterator =
        new StringRecordIteratorBuilder()
            .setName("courses")
            .setColumns("cid", "cname")
            .addRow("CS101", "Intro to Java")
            .addRow("CS102", "Data Structures")
            .build();

    emptyStudentsIterator =
        new StringRecordIteratorBuilder()
            .setName("students")
            .setColumns("sid", "sname")
            .build();

    emptyCoursesIterator =
        new StringRecordIteratorBuilder()
            .setName("courses")
            .setColumns("cid", "cname")
            .build();
  }

  @Test
  public void getTupleDescriptor() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(studentsIterator, coursesIterator);
    join.open();

    // Execute
    Schema schema = join.getSchema();

    // Verify
    assertThat(schema.getColumnCount()).isEqualTo(4);
    assertThat(schema.getLength()).isEqualTo(
        studentsIterator.getSchema().getLength() + coursesIterator.getSchema().getLength());
    assertThat(schema.getColumnName(0)).isEqualTo("sid");
    assertThat(schema.getColumnName(1)).isEqualTo("sname");
    assertThat(schema.getColumnName(2)).isEqualTo("cid");
    assertThat(schema.getColumnName(3)).isEqualTo("cname");

    join.close();
  }

  @Test
  public void next_returnsAllCombinations() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(studentsIterator, coursesIterator);
    join.open();
    String expected[][] = new String[][] {
        new String[] {"1", "Bobby", "CS101", "Intro to Java"},
        new String[] {"1", "Bobby", "CS102", "Data Structures"},
        new String[] {"2", "Robin", "CS101", "Intro to Java"},
        new String[] {"2", "Robin", "CS102", "Data Structures"},
    };

    // Execute & Verify
    assertThat(join.hasNext()).isTrue();
    for (int i = 0; i < expected.length; i++) {
      Record record = join.next();

      assertThat(record.getSchema().getColumnCount()).isEqualTo(4);
      String[] expectedRow = expected[i];
      for (int j = 0; j < expectedRow.length; j++) {
        assertThat(record.get(j)).isEqualTo(expectedRow[j]);
      }
    }
    assertThat(join.hasNext()).isFalse();

    join.close();
  }

  @Test
  public void hasNext_withEmptyTables_returnsFalse() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(emptyStudentsIterator, emptyCoursesIterator);
    join.open();

    // Execute & Verify
    assertThat(join.hasNext()).isFalse();

    join.close();
  }

  @Test
  public void hasNext_withEmptyOuterTable_returnsFalse() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(emptyStudentsIterator, coursesIterator);
    join.open();

    // Execute & Verify
    assertThat(join.hasNext()).isFalse();

    join.close();
  }

  @Test
  public void hasNext_withEmptyInnerTable_returnsFalse() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(studentsIterator, emptyCoursesIterator);
    join.open();

    // Execute & Verify
    assertThat(join.hasNext()).isFalse();

    join.close();
  }
}
