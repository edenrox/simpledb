package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringTableBuilder;
import com.hopkins.simpledb.Table;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LimitTest {
  private Table table;
  private SequentialScan scan;
  private Limit limit;

  @Before
  public void setup() {
    table = new StringTableBuilder()
        .setName("tableName")
        .setColumns("name", "rank")
        .addRow("Bob Smith", "Corporal")
        .addRow("John Johnson", "Captain")
        .addRow("Grant Tomas", "Rookie")
        .addRow("Cleatus Tanner", "Rookie")
        .addRow("Sunbeam Holtham", "Rookie")
        .build();

    scan = new SequentialScan(table);
  }

  @Test
  public void hasNext_withZeroLimit_returnsFalse() {
    // SELECT * FROM tableName LIMIT 0
    limit = new Limit(scan, 0);
    limit.open();
    assertThat(limit.hasNext()).isFalse();
    limit.close();
  }

  @Test
  public void hasNext_withLimit_returnsTrueUntilLimitReached() {
    // SELECT * FROM tableName LIMIT 4
    limit = new Limit(scan, 4);
    limit.open();
    assertThat(limit.hasNext()).isTrue();
    limit.next();
    assertThat(limit.hasNext()).isTrue();
    limit.next();
    assertThat(limit.hasNext()).isTrue();
    limit.next();
    assertThat(limit.hasNext()).isTrue();
    limit.next();
    assertThat(limit.hasNext()).isFalse();
    limit.close();
  }

  @Test
  public void getTupleDescriptor_returnsSourceTupleDescriptor() {
    // SELECT * FROM tableName LIMIT 1
    limit = new Limit(scan, 1);
    limit.open();
    assertThat(limit.getSchema()).isEqualTo(scan.getSchema());
    limit.close();
  }

}
