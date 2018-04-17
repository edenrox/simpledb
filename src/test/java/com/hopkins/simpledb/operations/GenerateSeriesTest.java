package com.hopkins.simpledb.operations;

import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class GenerateSeriesTest {

  @Test
  public void testBasicSeries() {
    GenerateSeries source = new GenerateSeries(1, 4, 1);

    List<Object> values = DbIteratorUtil.openReadAllColumnClose(source, GenerateSeries.COLUMN_NAME);

    assertThat(values).containsExactly(1, 2, 3, 4).inOrder();
  }

  @Test
  public void testEvenSeries() {
    GenerateSeries source = new GenerateSeries(0, 5, 2);

    List<Object> values = DbIteratorUtil.openReadAllColumnClose(source, GenerateSeries.COLUMN_NAME);

    assertThat(values).containsExactly(0, 2, 4).inOrder();
  }

  @Test
  public void testDescendingSeries() {
    GenerateSeries source = new GenerateSeries(10, 0, -5);

    List<Object> values = DbIteratorUtil.openReadAllColumnClose(source, GenerateSeries.COLUMN_NAME);

    assertThat(values).containsExactly(10, 5, 0).inOrder();
  }

  @Test(expected = IllegalArgumentException.class)
  public void init_WithZeroStep_throws() {
    new GenerateSeries(1, 2, 0);
  }
}
