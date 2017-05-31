package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.*;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by edenrox on 5/29/2017.
 */
public class SequentialScanTest {
  private Table table;

  @Before
  public void setup() {
    table = new StringTableBuilder()
        .setName("makes")
        .setColumns("id", "name", "is_hidden")
        .addRow("1", "Ford", "false")
        .addRow("2", "Chevrolet", "false")
        .build();
  }

  @Test
  public void getTupleDescriptor_returnsTableTupleDescriptor() {
    SequentialScan scan = new SequentialScan(table);
    scan.open();

    assertThat(scan.getTupleDescriptor()).isEqualTo(table.getTupleDescriptor());

    scan.close();
  }

  @Test
  public void next_returnsEachTuple() {
    SequentialScan scan = new SequentialScan(table);
    scan.open();

    Tuple tuple = scan.next();
    assertThat(tuple).isNotNull();
    assertThat(tuple.getString(tuple.indexOf("name"))).isEqualTo("Ford");

    tuple = scan.next();
    assertThat(tuple).isNotNull();
    assertThat(tuple.getString(tuple.indexOf("name"))).isEqualTo("Chevrolet");

    scan.close();
  }

  @Test
  public void hasNext_returnsTrueUntilEnd() {
    SequentialScan scan = new SequentialScan(table);
    scan.open();

    assertThat(scan.hasNext()).isTrue();
    scan.next();
    assertThat(scan.hasNext()).isTrue();
    scan.next();
    assertThat(scan.hasNext()).isFalse();
  }
}
