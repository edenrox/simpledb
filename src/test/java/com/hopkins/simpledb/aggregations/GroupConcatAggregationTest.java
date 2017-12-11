package com.hopkins.simpledb.aggregations;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class GroupConcatAggregationTest {
  private GroupConcatAggregation groupConcat;

  @Test
  public void getAggregate_withStringColumn_returnsConcatenation() {
    groupConcat = new GroupConcatAggregation(" ");
    groupConcat.addValue("a");
    groupConcat.addValue("b");
    groupConcat.addValue("c");
    groupConcat.addValue("d");

    assertThat(groupConcat.getAggregate()).isEqualTo("a b c d");
  }

  @Test
  public void getAggregate_withIntColumn_returnsConcatenation() {
    groupConcat = new GroupConcatAggregation(",");
    groupConcat.addValue(1);
    groupConcat.addValue(2);
    groupConcat.addValue(3);
    groupConcat.addValue(4);

    assertThat(groupConcat.getAggregate()).isEqualTo("1,2,3,4");
  }

  @Test
  public void getAggregate_withEmpty_returnsEmptyString() {
    groupConcat = new GroupConcatAggregation(",");

    assertThat(groupConcat.getAggregate()).isEqualTo("");
  }
}
