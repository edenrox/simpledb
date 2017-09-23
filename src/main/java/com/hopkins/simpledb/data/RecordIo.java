package com.hopkins.simpledb.data;

import java.io.IOException;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class RecordIo {

  public static void writeRecord(ByteWriter writer, Record record) {
    try {
      Schema schema = record.getSchema();
      for (int i = 0; i < schema.getColumnCount(); i++) {
        Column column = schema.getColumn(i);
        Object value = record.get(i);
        writeValue(writer, column, value);
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private static void writeValue(ByteWriter writer, Column column, Object value) throws IOException {
    switch (column.getType()) {
      case BOOL:
        writer.writeBoolean((boolean) value);
        break;
      case DOUBLE:
        writer.writeDouble((double) value);
        break;
      case INTEGER:
        writer.writeInt((int) value);
        break;
      case STRING:
        writer.writeFixedLengthString((String) value, column.getLength());
        break;
      case BLOB:
        writer.writeFixedLengthBlob((byte[]) value, column.getLength());
      default:
        throw new IllegalArgumentException("Unknown column type: " + column.getType());
    }
  }

  public static Record readRecord(ByteReader reader, Schema schema) {
    try {
      Record record = new Record(schema);
      for (int i = 0; i < schema.getColumnCount(); i++) {
        Column column = schema.getColumn(i);
        record.set(i, readValue(reader, column));
      }
      return record;
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private static Object readValue(ByteReader reader, Column column) throws IOException {
    switch (column.getType()) {
      case BOOL:
        return reader.readBoolean();
      case DOUBLE:
        return reader.readDouble();
      case INTEGER:
        return reader.readInt();
      case STRING:
        return reader.readFixedLengthString(column.getLength());
      case BLOB:
        return reader.readFixedLengthBlob(column.getLength());
      default:
        throw new IllegalArgumentException("Unknown column type: " + column.getType());
    }
  }
}
