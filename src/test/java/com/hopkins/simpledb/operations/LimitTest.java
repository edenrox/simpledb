package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringTableBuilder;
import com.hopkins.simpledb.Table;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by edenrox on 5/31/2017.
 */
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
    limit = new Limit(scan, 0);
    limit.open();
    assertThat(limit.hasNext()).isFalse();
    limit.close();
  }

  @Test
  public void hasNext_withLimit_returnsTrueUntilLimitReached() {
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
    limit = new Limit(scan, 0);
    limit.open();
    assertThat(limit.getTupleDescriptor()).isEqualTo(scan.getTupleDescriptor());
    limit.close();
  }

}
