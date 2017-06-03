package com.hopkins.simpledb.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class SchemaIo {
  private static final int COLUMN_NAME_LENGTH = 64;
  private final StringIo stringIo;

  public Schema readSchema(DataInputStream inputStream) throws IOException {
    int numColumns = inputStream.readInt();
    List<Column> columnList = new ArrayList<>(numColumns);
    for (int i = 0; i < numColumns; i++) {
      ColumnType columnType = ColumnType.fromValue(inputStream.readChar());
      int columnLength = inputStream.readInt();
      String columnName = stringIo.readString(inputStream, COLUMN_NAME_LENGTH);
      columnList.add(buildColumn(columnType, columnName, columnLength));
    }
    return new Schema(columnList);
  }

  private Column buildColumn(ColumnType type, String name, int length) {
    switch (type) {
      case BOOL:
        return Column.newBoolColumn(name);
      case DOUBLE:
        return Column.newDoubleColumn(name);
      case STRING:
        return Column.newStringColumn(name, length);
      case INTEGER:
        return Column.newIntColumn(name);
      default:
        throw new IllegalArgumentException("Unknown column type: " + type);
    }
  }

  public void writeSchema(DataOutputStream outputStream, Schema schema) throws IOException {
    outputStream.writeInt(schema.getColumnCount());
    for (int i = 0; i < schema.getColumnCount(); i++) {
      Column column = schema.getColumn(i);
      outputStream.writeChar(column.getType().getValue());
      outputStream.writeInt(column.getLength());
      stringIo.writeString(outputStream, COLUMN_NAME_LENGTH, column.getName());
    }
  }
}
