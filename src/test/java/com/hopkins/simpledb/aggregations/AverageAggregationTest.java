package com.hopkins.simpledb.aggregations;

import com.hopkins.simpledb.data.Column;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class AverageAggregationTest {
  private AverageAggregation average;

  @Test
  public void getAggregate_withIntColumn_returnsAverage() {
    average = new AverageAggregation(Column.newIntColumn("test"));
    average.addValue(1);
    average.addValue(2);
    average.addValue(3);

    assertThat(average.getAggregate()).isEqualTo(2.0);
  }

  @Test
  public void getAggregate_withDoubleColumn_returnsAverage() {
    average = new AverageAggregation(Column.newDoubleColumn("test"));
    average.addValue(2.4);
    average.addValue(2.1);
    average.addValue(5.1);
    average.addValue(0.4);

    assertThat(average.getAggregate()).isEqualTo(2.5);
  }
}
