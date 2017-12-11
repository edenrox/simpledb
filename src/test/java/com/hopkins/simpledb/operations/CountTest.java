package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.StringRecordIteratorBuilder;
import com.hopkins.simpledb.data.Record;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CountTest {
  private RecordIterator iterator;
  private Count countOp;

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
  public void hasNext_afterInit_returnsTrue() {
    countOp = new Count(iterator);
    countOp.open();

    assertThat(countOp.hasNext()).isTrue();

    countOp.close();
  }

  @Test
  public void hasNext_afterNext_returnsFalse() {
    countOp = new Count(iterator);
    countOp.open();
    countOp.next();

    assertThat(countOp.hasNext()).isFalse();

    countOp.close();
  }

  @Test
  public void next_returnsNumRows() {
    countOp = new Count(iterator);
    countOp.open();
    Record record = countOp.next();

    assertThat(record.getInt("count")).isEqualTo(5);

    countOp.close();
  }

  @Test
  public void next_withOneRowSource_returnsOne() {
    RecordIterator oneRowIter =
        new StringRecordIteratorBuilder()
            .setName("tableName")
            .setColumns("name", "rank")
            .addRow("Bob Smith", "Corporal")
            .build();

    countOp = new Count(oneRowIter);
    countOp.open();
    Record record = countOp.next();

    assertThat(record.getInt("count")).isEqualTo(1);

    countOp.close();
  }
}
