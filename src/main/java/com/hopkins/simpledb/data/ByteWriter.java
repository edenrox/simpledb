package com.hopkins.simpledb.data;

/**
 * Created by ian_000 on 7/7/2017.
 */
public class ByteWriter {
  private final byte[] buffer;
  private int position;

  public ByteWriter(byte[] buffer) {
    this.buffer = buffer;
    this.position = 0;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  public void writeBoolean(boolean value) {
    writeByte((byte) (value ? 1 : 0));
  }

  public void writeByte(byte value) {
    buffer[position] = value;
    position++;
  }

  public void writeChar(char value) {
    buffer[position] = (byte) ((value >> 8) & 0xFF);
    buffer[position + 1] = (byte) value;
    position += 2;
  }

  public void writeInt(int value) {
    buffer[position] = (byte) (value >> 24);
    buffer[position + 1] = (byte) (value >> 16);
    buffer[position + 2] = (byte) (value >> 8);
    buffer[position + 3] = (byte) value;
    position += 4;
  }

  public void writeDouble(double value) {
    long longBits = Double.doubleToLongBits(value);
    writeInt((int) (longBits >> 32));
    writeInt((int) longBits);
    position += 8;
  }

  public void writeVarLengthString(String value) {
    writeInt(value.length());
    for (int i = 0; i < value.length(); i++) {
      writeChar(value.charAt(i));
    }
  }

  public void writeFixedLengthString(String value, int length) {
    for (int i = 0; i < length; i++) {
      if (i < value.length()) {
        writeChar(value.charAt(i));
      } else {
        writeChar((char) 0);
      }
    }
  }

  public void writeFixedLengthBlob(byte[] value, int length) {
    for (int i = 0; i < length; i++) {
      if (i < value.length) {
        writeByte(value[i]);
      } else {
        writeByte((byte) 0);
      }
    }
  }
}
