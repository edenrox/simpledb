package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SumAggregationTest {
  private SumAggregation sum;

  @Test
  public void getAggregate_withIntColumn_returnsSum() {
    sum = new SumAggregation(Column.newIntColumn("test"));
    sum.addValue(1);
    sum.addValue(2);
    sum.addValue(3);
    sum.addValue(4);

    assertThat(sum.getAggregate()).isEqualTo(10);
  }

  @Test
  public void getAggregate_withDoubleColumn_returnsSum() {
    sum = new SumAggregation(Column.newDoubleColumn("test"));
    sum.addValue(1.1);
    sum.addValue(2.2);
    sum.addValue(3.3);
    sum.addValue(4.4);

    assertThat(sum.getAggregate()).isEqualTo(11.0);
  }
}
