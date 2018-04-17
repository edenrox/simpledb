package com.hopkins.simpledb.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class RecordCompareTest {
  private Record record1;
  private Record record2;
  private Record record3;
  private Record record4;

  private Schema schema;

  @Before
  public void setup() {
    schema = new Schema(Arrays.asList(
        Column.newStringColumn("name", 32),
        Column.newIntColumn("age")));

    record1 = new Record(schema);
    record1.setAll("Alice", 21);
    record2 = new Record(schema);
    record2.setAll("Bob", 15);
    record3 = new Record(schema);
    record3.setAll("Bob", 25);
    record4 = new Record(schema);
    record4.setAll("Candy", 27);
  }

  @Test
  public void testOrdering() {
    List<Record> list = Arrays.asList(record3, record4, record1, record2);

    list.sort(RecordCompare::compare);

    assertThat(list).containsExactly(record1, record2, record3, record4).inOrder();
  }
}
