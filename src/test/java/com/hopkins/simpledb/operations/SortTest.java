package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringRecordIteratorBuilder;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class SortTest {

  private RecordIterator studentsIterator;
  private Sort sort;

  @Before
  public void setup() {
    studentsIterator =
        new StringRecordIteratorBuilder()
            .setName("students")
            .setColumns("sid", "sname", "grade")
            .addRow("2", "Robin", "A")
            .addRow("1", "Bobby", "A")
            .addRow("3", "Inder", "B")
            .addRow("6", "Josiah", "B")
            .addRow("4", "Tabitha", "C")
            .addRow("5", "Jessica", "C")
            .build();
  }

  @Test
  public void next_returnsFirstSortedItem() {
    sort = new Sort(studentsIterator, new SortColumn("sname", SortColumn.SortDirection.ASCENDING));
    sort.open();
    Record record = sort.next();

    assertThat(record.get("sname")).isEqualTo("Bobby");

    sort.close();
  }

  @Test
  public void next_withDescending_returnsFirstSortedItem() {
    sort = new Sort(studentsIterator, new SortColumn("sname", SortColumn.SortDirection.DESCENDING));
    sort.open();
    Record record = sort.next();

    assertThat(record.get("sname")).isEqualTo("Tabitha");

    sort.close();
  }

  @Test
  public void testFullResult() {
    sort = new Sort(studentsIterator, new SortColumn("sname", SortColumn.SortDirection.ASCENDING));
    sort.open();

    List<Object> names = DbIteratorUtil.readAllColumn(sort, "sname");

    assertThat(names).containsExactly("Bobby", "Inder", "Jessica", "Josiah", "Robin", "Tabitha").inOrder();

    sort.close();
  }

  @Test
  public void testFullResult_withMultiColumn() {
    sort =
        new Sort(
            studentsIterator,
            new SortColumn("grade", SortColumn.SortDirection.ASCENDING),
            new SortColumn("sname", SortColumn.SortDirection.DESCENDING));
    sort.open();

    List<Object> names = DbIteratorUtil.readAllColumn(sort, "sname");

    assertThat(names).containsExactly("Robin", "Bobby", "Josiah", "Inder", "Tabitha", "Jessica").inOrder();

    sort.close();
  }

  @Test
  public void testFullResult_withIntegerColumns() {
    Schema schema = new Schema(Arrays.asList(
        Column.newIntColumn("year"),
        Column.newIntColumn("month"),
        Column.newIntColumn("id")));
    Record record1 = new Record(schema);
    record1.setAll(2015, 5, 4);
    Record record2 = new Record(schema);
    record2.setAll(2013, 1, 7);
    Record record3 = new Record(schema);
    record3.setAll(2013, 1, 1);
    RecordIterator iterator = new RecordIterator(
        schema,
        Arrays.asList(record1, record2, record3));
    sort =
        new Sort(
            iterator,
            new SortColumn("year", SortColumn.SortDirection.ASCENDING),
            new SortColumn("month", SortColumn.SortDirection.ASCENDING));
    sort.open();

    List<Object> ids = DbIteratorUtil.readAllColumn(sort, "id");
    assertThat(ids).containsExactly(7, 1, 4).inOrder();

    sort.close();
  }
}
