package com.hopkins.simpledb;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

public class TupleDescriptorTest {

  private TupleDescriptor descriptor;

  @Before
  public void setup() {
    descriptor =
        new TupleDescriptor(Arrays.asList(
            ColumnDescriptor.newIntColumn("id"),
            ColumnDescriptor.newStringColumn("name", 20),
            ColumnDescriptor.newBoolColumn("is_hidden")
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
    assertThat(descriptor.getSize()).isEqualTo(3);
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
