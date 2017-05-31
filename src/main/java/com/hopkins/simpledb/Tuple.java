package com.hopkins.simpledb;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by edenrox on 5/29/2017.
 */
public class Tuple {
  private static final byte FALSE = (byte) 0;
  private static final byte TRUE = (byte) 1;

  private static final Charset ASCII = StandardCharsets.US_ASCII;

  private final int id;
  private final TupleDescriptor descriptor;
  private final ByteBuffer byteBuffer;

  public Tuple(int id, TupleDescriptor descriptor, ByteBuffer byteBuffer) {
    this.id = id;
    this.descriptor = descriptor;
    this.byteBuffer = byteBuffer;
  }

  public int getId() {
    return id;
  }

  public TupleDescriptor getDescriptor() {
    return descriptor;
  }

  public byte[] getData() {
    return byteBuffer.array();
  }

  public Object get(int index) {
    ColumnType type = descriptor.getFieldType(index);
    switch (type) {
      case BOOL:
        return getBoolean(index);
      case INTEGER:
        return getInteger(index);
      case DOUBLE:
        return getDouble(index);
      case STRING:
        return getString(index);
      default:
        throw new IllegalArgumentException("Unexpected column (index: " + index + ", type: " + type + "}");
    }
  }

  public double getDouble(int index) {
    int offset = descriptor.getFieldOffset(index);
    return byteBuffer.getDouble(offset);
  }

  public void setDouble(int index, double value) {
    int offset = descriptor.getFieldOffset(index);
    byteBuffer.putDouble(offset, value);
  }

  public int getInteger(int index) {
    int offset = descriptor.getFieldOffset(index);
    return byteBuffer.getInt(index);
  }

  public void setInteger(int index, int value) {
    int offset = descriptor.getFieldOffset(index);
    byteBuffer.putInt(index, value);
  }

  public String getString(int index) {
    int offset = descriptor.getFieldOffset(index);
    int length = descriptor.getFieldLength(index);

    byte[] dest = new byte[length];
    int strLength = length;
    for (int i = 0; i < length; i++) {
      dest[i] = byteBuffer.get(offset + i);
      if (dest[i] == 0) {
        strLength = i;
        break;
      }
    }
    return new String(dest, 0, strLength, ASCII);
  }

  public void setString(int index, String value) {
    int offset = descriptor.getFieldOffset(index);
    int fieldLength = descriptor.getFieldLength(index);

    if (value.length() > fieldLength) {
      value = value.substring(0, fieldLength);
    }
    byte[] bytes = value.getBytes(ASCII);
    for (int i = 0; i < fieldLength; i++) {
      if (i < bytes.length) {
        byteBuffer.put(offset + i, bytes[i]);
      } else {
        byteBuffer.put(offset + i, (byte) 0);
      }
    }
  }

  public boolean getBoolean(int index) {
    int offset = descriptor.getFieldOffset(index);
    return byteBuffer.get(offset) == TRUE;
  }

  public void setBoolean(int index, boolean value) {
    int offset = descriptor.getFieldOffset(index);
    byteBuffer.put(offset, value ? TRUE : FALSE);
  }

  public int indexOf(String columnName) {
    return descriptor.indexOf(columnName);
  }
}
