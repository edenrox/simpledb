package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.*;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by edenrox on 5/29/2017.
 */
public class ProjectionTest {
  private Table table;
  private SequentialScan scan;

  @Before
  public void setup() {
    table = new StringTableBuilder()
        .setName("makes")
        .setColumns("id", "name", "is_hidden")
        .addRow("1", "Ford", "false")
        .addRow("2", "Chevrolet", "false")
        .build();

    scan = new SequentialScan(table);
  }

  @Test
  public void getTupleDescriptor_returnsProjectedColumnsInRequestedOrder() {
    Projection projection = new Projection(scan, "is_hidden", "id");
    projection.open();

    TupleDescriptor descriptor = projection.getTupleDescriptor();
    assertThat(descriptor.getSize()).isEqualTo(2);
    assertThat(descriptor.getFieldName(0)).isEqualTo("is_hidden");
    assertThat(descriptor.getFieldName(1)).isEqualTo("id");

    projection.close();
  }

  @Test
  public void next_returnsEachTuple() {
    Projection projection = new Projection(scan, "is_hidden", "id");
    projection.open();

    Tuple tuple = projection.next();
    assertThat(tuple.getString(tuple.indexOf("id"))).isEqualTo("1");

    tuple = projection.next();
    assertThat(tuple.getString(tuple.indexOf("id"))).isEqualTo("2");
  }

}
