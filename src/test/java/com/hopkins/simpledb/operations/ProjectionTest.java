package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.*;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ProjectionTest {
  private RecordIterator iterator;

  @Before
  public void setup() {
    iterator = new StringRecordIteratorBuilder()
        .setName("makes")
        .setColumns("id", "name", "is_hidden")
        .addRow("1", "Ford", "false")
        .addRow("2", "Chevrolet", "false")
        .build();
  }

  @Test
  public void getTupleDescriptor_returnsProjectedColumnsInRequestedOrder() {
    // SELECT is_hidden, id FROM makes
    Projection projection = new Projection(iterator, "is_hidden", "id");
    projection.open();

    Schema descriptor = projection.getSchema();
    assertThat(descriptor.getColumnCount()).isEqualTo(2);
    assertThat(descriptor.getColumnName(0)).isEqualTo("is_hidden");
    assertThat(descriptor.getColumnName(1)).isEqualTo("id");

    projection.close();
  }

  @Test
  public void next_returnsEachTuple() {
    // SELECT is_hidden, id FROM makes
    Projection projection = new Projection(iterator, "is_hidden", "id");
    projection.open();

    Record record = projection.next();
    assertThat(record.get("id")).isEqualTo("1");

    record = projection.next();
    assertThat(record.get("id")).isEqualTo("2");
  }
}
