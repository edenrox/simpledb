package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class MinAggregationTest {
  private MinAggregation min;

  @Test
  public void getAggregate_withIntValues_returnsMin() {
    min = new MinAggregation(Column.newIntColumn("test"));
    min.addValue(4);
    min.addValue(1);
    min.addValue(2);

    assertThat(min.getAggregate()).isEqualTo(1);
  }

  @Test
  public void getAggregate_withStringValues_returnsMin() {
    min = new MinAggregation(Column.newStringColumn("test", 20));
    min.addValue("beta");
    min.addValue("alpha");
    min.addValue("gamma");

    assertThat(min.getAggregate()).isEqualTo("alpha");
  }
}
