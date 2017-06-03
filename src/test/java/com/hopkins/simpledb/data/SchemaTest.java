package com.hopkins.simpledb.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

public class SchemaTest {

  private Schema descriptor;

  @Before
  public void setup() {
    descriptor =
        new Schema(Arrays.asList(
            Column.newIntColumn("id"),
            Column.newStringColumn("name", 20),
            Column.newBoolColumn("is_hidden")
        ));
  }

  @Test
  public void indexOf_returnsCorrectIndex() {
    assertThat(descriptor.indexOf("id")).isEqualTo(0);
    assertThat(descriptor.indexOf("is_hidden")).isEqualTo(2);
  }

  @Test
  public void indexOf_invalidColumn_returnsInvalidIndex() {
    assertThat(descriptor.indexOf("bogus")).isEqualTo(-1);
  }

  @Test
  public void getLength_returnsFieldSizeSum() {
    assertThat(descriptor.getLength()).isEqualTo(25);
  }

  @Test
  public void getSize_returnsNumColumns() {
    assertThat(descriptor.getColumnCount()).isEqualTo(3);
  }

  @Test
  public void getOffset_withFirstField_returnsZero() {
    assertThat(descriptor.getFieldOffset(0)).isEqualTo(0);
  }

  @Test
  public void getOffset_returnsCorrectOffset() {
    assertThat(descriptor.getFieldOffset(1)).isEqualTo(4);
    assertThat(descriptor.getFieldOffset(2)).isEqualTo(24);
  }
}
