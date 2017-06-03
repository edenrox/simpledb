package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringTableBuilder;
import com.hopkins.simpledb.Table;
import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class NestedLoopJoinTest {

  private Table studentsTable;
  private Table coursesTable;

  private Table emptyStudentsTable;
  private Table emptyCoursesTable;

  @Before
  public void setup() {
    studentsTable =
        new StringTableBuilder()
            .setName("students")
            .setColumns("sid", "sname")
            .addRow("1", "Bobby")
            .addRow("2", "Robin")
            .build();

    coursesTable =
        new StringTableBuilder()
            .setName("courses")
            .setColumns("cid", "cname")
            .addRow("CS101", "Intro to Java")
            .addRow("CS102", "Data Structures")
            .build();

    emptyStudentsTable =
        new StringTableBuilder()
            .setName("students")
            .setColumns("sid", "sname")
            .build();

    emptyCoursesTable =
        new StringTableBuilder()
            .setName("courses")
            .setColumns("cid", "cname")
            .build();
  }

  @Test
  public void getTupleDescriptor() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(
        new SequentialScan(studentsTable),
        new SequentialScan(coursesTable));
    join.open();

    // Execute
    Schema schema = join.getSchema();

    // Verify
    assertThat(schema.getColumnCount()).isEqualTo(4);
    assertThat(schema.getLength()).isEqualTo(
        studentsTable.getSchema().getLength() + coursesTable.getSchema().getLength());
    assertThat(schema.getColumnName(0)).isEqualTo("sid");
    assertThat(schema.getColumnName(1)).isEqualTo("sname");
    assertThat(schema.getColumnName(2)).isEqualTo("cid");
    assertThat(schema.getColumnName(3)).isEqualTo("cname");

    join.close();
  }

  @Test
  public void next_returnsAllCombinations() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(
        new SequentialScan(studentsTable),
        new SequentialScan(coursesTable));
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
      Tuple tuple = join.next();

      assertThat(tuple.getDescriptor().getColumnCount()).isEqualTo(4);
      String[] expectedRow = expected[i];
      for (int j = 0; j < expectedRow.length; j++) {
        assertThat(tuple.getString(j)).isEqualTo(expectedRow[j]);
      }
    }
    assertThat(join.hasNext()).isFalse();

    join.close();
  }

  @Test
  public void hasNext_withEmptyTables_returnsFalse() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(
        new SequentialScan(emptyStudentsTable),
        new SequentialScan(emptyCoursesTable));
    join.open();

    // Execute & Verify
    assertThat(join.hasNext()).isFalse();

    join.close();
  }

  @Test
  public void hasNext_withEmptyOuterTable_returnsFalse() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(
        new SequentialScan(emptyStudentsTable),
        new SequentialScan(coursesTable));
    join.open();

    // Execute & Verify
    assertThat(join.hasNext()).isFalse();

    join.close();
  }

  @Test
  public void hasNext_withEmptyInnerTable_returnsFalse() {
    // Setup
    NestedLoopJoin join = new NestedLoopJoin(
        new SequentialScan(studentsTable),
        new SequentialScan(emptyCoursesTable));
    join.open();

    // Execute & Verify
    assertThat(join.hasNext()).isFalse();

    join.close();
  }

}
