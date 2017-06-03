package com.hopkins.simpledb.data;

import com.hopkins.simpledb.util.ArrayUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class RecordIo {
  private static final byte STRING_PADDING = (byte) 0;
  private static final byte FALSE = (byte) 0;
  private static final byte TRUE = (byte) 1;
  private static final Charset ASCII = StandardCharsets.US_ASCII;

  public void writeRecord(DataOutputStream outputStream, Record record) {
    try {
      Schema schema = record.getSchema();
      for (int i = 0; i < schema.getColumnCount(); i++) {
        Column column = schema.getColumn(i);
        Object value = record.get(i);
        writeValue(outputStream, column, value);
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private void writeValue(DataOutputStream outputStream, Column column, Object value) throws IOException {
    switch (column.getType()) {
      case BOOL:
        boolean boolValue = (boolean) value;
        outputStream.write(boolValue ? TRUE : FALSE);
      case DOUBLE:
        double doubleValue = (double) value;
        outputStream.writeDouble(doubleValue);
      case INTEGER:
        int intValue = (int) value;
        outputStream.writeInt(intValue);
      case STRING:
        String stringValue = (String) value;
        byte[] bytes = stringValue.getBytes(ASCII);
        for (int i = 0; i < column.getLength(); i++) {
          if (i < bytes.length) {
            outputStream.write(bytes[i]);
          } else {
            outputStream.write(STRING_PADDING);
          }
        }
      default:
        throw new IllegalArgumentException("Unknown column type: " + column.getType());
    }
  }

  public Record readRecord(DataInputStream inputStream, Schema schema) {
    try {
      Record record = new Record(schema);
      for (int i = 0; i < schema.getColumnCount(); i++) {
        Column column = schema.getColumn(i);
        record.set(i, readValue(inputStream, column));
      }
      return record;
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private Object readValue(DataInputStream inputStream, Column column) throws IOException {
    switch (column.getType()) {
      case BOOL:
        return inputStream.readByte() == TRUE;
      case DOUBLE:
        return inputStream.readDouble();
      case INTEGER:
        return inputStream.readInt();
      case STRING:
        byte[] buffer = new byte[column.getLength()];
        inputStream.read(buffer);
        int paddingIndex = ArrayUtil.indexOf(buffer, STRING_PADDING);
        if (paddingIndex == -1) {
          paddingIndex = buffer.length;
        }
        return new String(buffer, 0, paddingIndex, ASCII);
      default:
        throw new IllegalArgumentException("Unknown column type: " + column.getType());
    }
  }
}
