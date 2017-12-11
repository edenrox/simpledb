package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringRecordIteratorBuilder;
import com.hopkins.simpledb.data.Record;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LimitTest {
  private RecordIterator iterator;
  private Limit limit;

  @Before
  public void setup() {
    iterator = new StringRecordIteratorBuilder()
        .setName("tableName")
        .setColumns("name", "rank")
        .addRow("Bob Smith", "Corporal")
        .addRow("John Johnson", "Captain")
        .addRow("Grant Tomas", "Rookie")
        .addRow("Cleatus Tanner", "Rookie")
        .addRow("Sunbeam Holtham", "Rookie")
        .build();
  }

  @Test
  public void hasNext_withZeroLimit_returnsFalse() {
    // SELECT * FROM tableName LIMIT 0
    limit = new Limit(iterator, /* offset= */ 0, /* limit= */ 0);
    limit.open();
    assertThat(limit.hasNext()).isFalse();
    limit.close();
  }

  @Test
  public void hasNext_withLimit_returnsTrueUntilLimitReached() {
    // SELECT * FROM tableName LIMIT 4
    limit = new Limit(iterator, /* offset= */ 0, /* limit= */ 4);
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
  public void next_withOffset_returnsOffsetRows() {
    limit = new Limit(iterator, /* offset= */ 2, /* limit= */ 2);
    limit.open();

    Record record = limit.next();
    assertThat(record.get("name")).isEqualTo("Grant Tomas");
    record = limit.next();
    assertThat(record.get("name")).isEqualTo("Cleatus Tanner");
    assertThat(limit.hasNext()).isFalse();

    limit.close();
  }

  @Test
  public void getTupleDescriptor_returnsSourceTupleDescriptor() {
    // SELECT * FROM tableName LIMIT 1
    limit = new Limit(iterator, /* offset= */ 0, /* limit= */ 1);
    limit.open();
    assertThat(limit.getSchema()).isEqualTo(iterator.getSchema());
    limit.close();
  }
}
