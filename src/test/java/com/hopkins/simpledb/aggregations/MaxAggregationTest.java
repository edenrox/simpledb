package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class MaxAggregationTest {
  private MaxAggregation max;

  @Test
  public void getAggregate_withIntValues_returnsMin() {
    max = new MaxAggregation(Column.newIntColumn("test"));
    max.addValue(40);
    max.addValue(1000);
    max.addValue(24);

    assertThat(max.getAggregate()).isEqualTo(1000);
  }

  @Test
  public void getAggregate_withStringValues_returnsMin() {
    max = new MaxAggregation(Column.newStringColumn("test", 20));
    max.addValue("beta");
    max.addValue("alpha");
    max.addValue("gamma");

    assertThat(max.getAggregate()).isEqualTo("gamma");
  }
}
