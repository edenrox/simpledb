package com.hopkins.simpledb.data;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.ColumnType;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ColumnTest {

  @Test
  public void newBoolColumn_setsNameTypeAndLength() {
    String name = "boolColumn";
    Column desc = Column.newBoolColumn(name);

    assertThat(desc.getName()).isEqualTo(name);
    assertThat(desc.getType()).isEqualTo(ColumnType.BOOL);
    assertThat(desc.getLength()).isEqualTo(1);
  }

  @Test
  public void newDoubleColumn_setsNameTypeAndLength() {
    String name = "doubleColumn";
    Column desc = Column.newDoubleColumn(name);

    assertThat(desc.getName()).isEqualTo(name);
    assertThat(desc.getType()).isEqualTo(ColumnType.DOUBLE);
    assertThat(desc.getLength()).isEqualTo(8);
  }

  @Test
  public void newStringColumn_setsNameTypeAndLength() {
    String name = "stringColumn";
    int length = 12;
    Column desc = Column.newStringColumn(name, 12);

    assertThat(desc.getName()).isEqualTo(name);
    assertThat(desc.getType()).isEqualTo(ColumnType.STRING);
    assertThat(desc.getLength()).isEqualTo(length);
  }
}
