package com.hopkins.simpledb.aggregations;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CountAggregationTest {
  private CountAggregation count;

  @Test
  public void getAggregate_withEmpty_returnsZero() {
    count = new CountAggregation();

    assertThat(count.getAggregate()).isEqualTo(0);
  }

  @Test
  public void getAggregate_withIntColumn_returnsCount() {
    count = new CountAggregation();
    count.addValue(1);
    count.addValue(2);
    count.addValue(3);
    count.addValue(-2);

    assertThat(count.getAggregate()).isEqualTo(4);
  }
}
