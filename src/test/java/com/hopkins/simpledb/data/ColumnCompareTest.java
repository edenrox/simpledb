package com.hopkins.simpledb.data;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ColumnCompareTest {

  @Test
  public void compare_withIntegers_returnsCorrect() {
    assertThat(ColumnCompare.compare(ColumnType.INTEGER, 1, 5)).isLessThan(0);
    assertThat(ColumnCompare.compare(ColumnType.INTEGER, -60, -5)).isLessThan(0);

    assertThat(ColumnCompare.compare(ColumnType.INTEGER, 10, -3)).isGreaterThan(0);
    assertThat(ColumnCompare.compare(ColumnType.INTEGER, 10000, 4)).isGreaterThan(0);

    assertThat(ColumnCompare.compare(ColumnType.INTEGER, 10, 10)).isEqualTo(0);
  }

  @Test
  public void compare_withStrings_returnsCorrect() {
    assertThat(ColumnCompare.compare(ColumnType.STRING, "a", "b")).isLessThan(0);
    assertThat(ColumnCompare.compare(ColumnType.STRING, "alpha", "gamma")).isLessThan(0);

    assertThat(ColumnCompare.compare(ColumnType.STRING, "B", "A")).isGreaterThan(0);
    assertThat(ColumnCompare.compare(ColumnType.STRING, "epsilon", "beta")).isGreaterThan(0);

    assertThat(ColumnCompare.compare(ColumnType.STRING, "theta", "theta")).isEqualTo(0);
  }

  @Test
  public void compare_withBooleans_returnsCorrect() {
    assertThat(ColumnCompare.compare(ColumnType.BOOL, false, true)).isLessThan(0);

    assertThat(ColumnCompare.compare(ColumnType.BOOL, true, false)).isGreaterThan(0);

    assertThat(ColumnCompare.compare(ColumnType.BOOL, false, false)).isEqualTo(0);
  }
}
