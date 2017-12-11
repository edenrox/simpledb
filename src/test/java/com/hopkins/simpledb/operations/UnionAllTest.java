package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringRecordIteratorBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class UnionAllTest {

  private RecordIterator scan1;
  private RecordIterator scan2;

  @Before
  public void setup() {
    scan1 =
        new StringRecordIteratorBuilder()
            .setName("scan1")
            .setColumns("name", "age")
            .addRow("Bobby", "31")
            .build();
    scan2 =
        new StringRecordIteratorBuilder()
            .setName("scan2")
            .setColumns("name", "age")
            .addRow("Joanne", "35")
            .addRow("Markus", "21")
            .build();
  }

  @Test
  public void testReturnsAllRecords() {
    UnionAll unionAll = new UnionAll(scan1, scan2);
    unionAll.open();

    List<Object> names = DbIteratorUtil.readAllColumn(unionAll, "name");
    assertThat(names).containsExactly("Bobby", "Joanne", "Markus").inOrder();

    unionAll.close();
  }

}
