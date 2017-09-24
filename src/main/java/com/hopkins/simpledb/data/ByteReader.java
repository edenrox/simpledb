package com.hopkins.simpledb.data;

/**
 * Created by ian_000 on 7/7/2017.
 */
public class ByteReader {
  private final byte[] buffer;
  private int position;

  public ByteReader(byte[] buffer) {
    this.buffer = buffer;
    position = 0;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public boolean readBoolean() {
    return readByte() > 0;
  }

  public byte readByte() {
    byte b = buffer[position];
    position++;
    return b;
  }

  public char readChar() {
    int c1 = buffer[position] & 0xff;
    int c2 = buffer[position + 1] & 0xff;
    position += 2;

    return (char) ((c1 << 8) | c2);
  }

  public int readInt() {
    int b1 = buffer[position] & 0xff;
    int b2 = buffer[position + 1] & 0xff;
    int b3 = buffer[position + 2] & 0xff;
    int b4 = buffer[position + 3] & 0xff;

    position += 4;
    return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
  }

  public double readDouble() {
    long l1 = buffer[position] & 0xff;
    long l2 = buffer[position + 1] & 0xff;
    long l3 = buffer[position + 2] & 0xff;
    long l4 = buffer[position + 3] & 0xff;
    long l5 = buffer[position + 4] & 0xff;
    long l6 = buffer[position + 5] & 0xff;
    long l7 = buffer[position + 6] & 0xff;
    long l8 = buffer[position + 7] & 0xff;
    long bits =
        (l1 << 56) | (l2 << 48) | (l3 << 40) | (l4 << 32)
            | (l5 << 24) | (l6 << 16) | (l7 << 8) | l8;
    return Double.longBitsToDouble(bits);
  }

  public String readVarLengthString() {
    int length = readInt();
    return readFixedLengthString(length);
  }

  public String readFixedLengthString(int length) {
    int initialPosition = position;
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = readChar();
      if (c == 0) {
        break;
      }
      sb.append(c);
    }
    position = initialPosition + length * 2;
    return sb.toString();
  }

  public byte[] readFixedLengthBlob(int length) {
    byte[] value = new byte[length];
    for (int i = 0; i < length; i++) {
      value[i] = readByte();
    }
    return value;
  }
}
