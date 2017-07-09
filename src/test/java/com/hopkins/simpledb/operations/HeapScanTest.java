package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.*;
import com.hopkins.simpledb.app.ServiceLocator;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.table.Table;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

public class HeapScanTest {
  private ServiceLocator serviceLocator;
  private Table table;

  @Before
  public void setup() {
    serviceLocator = new ServiceLocator();
    Schema schema = new Schema(Arrays.asList(
        Column.newIntColumn("id"),
        Column.newStringColumn("name", 20)
    ));
    table = new Table(1, "makes", schema);
  }

  @Test
  public void getTupleDescriptor_returnsTableTupleDescriptor() {
    // SELECT * FROM makes
    HeapScan scan = new HeapScan(table, serviceLocator);
    scan.open();

    assertThat(scan.getSchema()).isEqualTo(table.getSchema());

    scan.close();
  }

  @Test
  public void next_returnsEachTuple() {
    // SELECT * FROM makes
    HeapScan scan = new HeapScan(table, serviceLocator);
    scan.open();

    Record record = scan.next();
    assertThat(record).isNotNull();
    assertThat(record.get("name")).isEqualTo("Ford");

    record = scan.next();
    assertThat(record).isNotNull();
    assertThat(record.get("name")).isEqualTo("Chevrolet");

    scan.close();
  }

  @Test
  public void hasNext_returnsTrueUntilEnd() {
    // SELECT * FROM makes
    HeapScan scan = new HeapScan(table, serviceLocator);
    scan.open();

    assertThat(scan.hasNext()).isTrue();
    scan.next();
    assertThat(scan.hasNext()).isTrue();
    scan.next();
    assertThat(scan.hasNext()).isFalse();

    scan.close();
  }
}
