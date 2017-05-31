package com.hopkins.simpledb;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ColumnDescriptorTest {

  @Test
  public void newBoolColumn_setsNameTypeAndLength() {
    String name = "boolColumn";
    ColumnDescriptor desc = ColumnDescriptor.newBoolColumn(name);

    assertThat(desc.getName()).isEqualTo(name);
    assertThat(desc.getType()).isEqualTo(ColumnType.BOOL);
    assertThat(desc.getLength()).isEqualTo(1);
  }

  @Test
  public void newDoubleColumn_setsNameTypeAndLength() {
    String name = "doubleColumn";
    ColumnDescriptor desc = ColumnDescriptor.newDoubleColumn(name);

    assertThat(desc.getName()).isEqualTo(name);
    assertThat(desc.getType()).isEqualTo(ColumnType.DOUBLE);
    assertThat(desc.getLength()).isEqualTo(8);
  }

  @Test
  public void newStringColumn_setsNameTypeAndLength() {
    String name = "stringColumn";
    int length = 12;
    ColumnDescriptor desc = ColumnDescriptor.newStringColumn(name, 12);

    assertThat(desc.getName()).isEqualTo(name);
    assertThat(desc.getType()).isEqualTo(ColumnType.STRING);
    assertThat(desc.getLength()).isEqualTo(length);
  }
}
