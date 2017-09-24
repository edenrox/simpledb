package com.hopkins.simpledb.data;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ByteReaderWriterTest {

  private byte[] bytes;
  private ByteReader byteReader;
  private ByteWriter byteWriter;

  @Before
  public void setup() {
    bytes = new byte[100];
    byteReader = new ByteReader(bytes);
    byteWriter = new ByteWriter(bytes);
  }

  @Test
  public void getPosition_initial_returnsZero() {
    assertThat(byteReader.getPosition()).isEqualTo(0);
    assertThat(byteWriter.getPosition()).isEqualTo(0);
  }

  @Test
  public void readInt_afterWriteInt_returnsSameValue() {
    int value = 25;
    byteWriter.writeInt(value);
    assertThat(byteReader.readInt()).isEqualTo(value);

    value = 1;
    byteWriter.writeInt(value);
    assertThat(byteReader.readInt()).isEqualTo(value);

    value = 0;
    byteWriter.writeInt(value);
    assertThat(byteReader.readInt()).isEqualTo(value);

    value = 51452;
    byteWriter.writeInt(value);
    assertThat(byteReader.readInt()).isEqualTo(value);
  }

  @Test
  public void readInt_afterWriteNegativeInt_returnsSameValue() {
    int value = -2;
    byteWriter.writeInt(value);
    assertThat(byteReader.readInt()).isEqualTo(value);

    value = -50;
    byteWriter.writeInt(value);
    assertThat(byteReader.readInt()).isEqualTo(value);

    value = -13456;
    byteWriter.writeInt(value);
    assertThat(byteReader.readInt()).isEqualTo(value);
  }

  @Test
  public void readFixedLengthString_afterWriteFixedLengthString_returnsSameValue() {
    String value = "Test";
    byteWriter.writeFixedLengthString(value, 20);
    assertThat(byteReader.readFixedLengthString(20)).isEqualTo(value);

    value = "More over and ~!#";
    byteWriter.writeFixedLengthString(value, 20);
    assertThat(byteReader.readFixedLengthString(20)).isEqualTo(value);
  }
}
