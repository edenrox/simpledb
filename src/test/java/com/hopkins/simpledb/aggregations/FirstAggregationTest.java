package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class FirstAggregationTest {
  private FirstAggregation first;

  @Test
  public void getAggregate_afterReset_returnsFirstAfterReset() {
    first = new FirstAggregation(Column.newIntColumn("test"));
    first.addValue(1);
    first.reset();
    first.addValue(2);
    first.addValue(3);

    assertThat(first.getAggregate()).isEqualTo(2);
  }

  @Test
  public void getAggregate_withIntColumn_returnsFirst() {
    first = new FirstAggregation(Column.newIntColumn("test"));
    first.addValue(1);
    first.addValue(2);
    first.addValue(3);

    assertThat(first.getAggregate()).isEqualTo(1);
  }

  @Test
  public void getAggregate_withStringColumn_returnsFirst() {
    first = new FirstAggregation(Column.newStringColumn("test", 20));
    first.addValue("e");
    first.addValue("b");
    first.addValue("c");

    assertThat(first.getAggregate()).isEqualTo("e");
  }
}
