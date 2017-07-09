package com.hopkins.simpledb.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class SchemaIo {
  public static Schema readSchema(ByteReader reader) throws IOException {
    int numColumns = reader.readInt();
    List<Column> columnList = new ArrayList<>(numColumns);
    for (int i = 0; i < numColumns; i++) {
      columnList.add(readColumn(reader));
    }
    return new Schema(columnList);
  }

  private static Column readColumn(ByteReader reader) {
    ColumnType columnType = ColumnType.fromValue(reader.readChar());
    int columnLength = reader.readInt();
    String columnName = reader.readVarLengthString();
    return buildColumn(columnType, columnName, columnLength);
  }

  private static Column buildColumn(ColumnType type, String name, int length) {
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

  public static void writeSchema(ByteWriter writer, Schema schema) throws IOException {
    writer.writeInt(schema.getColumnCount());
    for (int i = 0; i < schema.getColumnCount(); i++) {
      writeColumn(writer, schema.getColumn(i));
    }
  }

  private static void writeColumn(ByteWriter writer, Column column) {
    writer.writeChar(column.getType().getValue());
    writer.writeInt(column.getLength());
    writer.writeVarLengthString(column.getName());
  }
}
