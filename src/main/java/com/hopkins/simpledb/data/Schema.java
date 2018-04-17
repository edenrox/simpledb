package com.hopkins.simpledb.data;

import com.hopkins.simpledb.util.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by edenrox on 5/29/2017.
 */
public class Schema {
  private final List<Column> columnList;
  private final int[] offsets;
  private final int length;

  public Schema(List<Column> columnList) {
    Preconditions.checkArgument(!columnList.isEmpty());
    this.columnList = Collections.unmodifiableList(new ArrayList<>(columnList));

    // Calculate offsets + length
    this.offsets = new int[columnList.size()];
    int offset = 0;
    for (int i = 0; i < columnList.size(); i++) {
      offsets[i] = offset;
      int columnLength = columnList.get(i).getLength();
      offset += columnLength;
      if (columnList.get(i).getType() == ColumnType.STRING) {
        // two bytes per character
        offset += columnLength;
      }
    }
    this.length = offset;
  }

  /**
   * Returns the number of columns in this descriptor.
   */
  public int getColumnCount() {
    return columnList.size();
  }

  /**
   * Return the length of a tuple with this descriptor in bytes.
   */
  public int getLength() {
    return length;
  }

  public int indexOf(String columnName) {
    for (int i = 0; i < columnList.size(); i++) {
      if (columnList.get(i).getName().equals(columnName)) {
        return i;
      }
    }
    return -1;
  }

  public String getColumnName(int index) {
    return columnList.get(index).getName();
  }

  public ColumnType getColumnType(int index) {
    return columnList.get(index).getType();
  }

  public int getColumnLength(int index) {
    return columnList.get(index).getLength();
  }

  public int getFieldOffset(int index) {
    return offsets[index];
  }

  public Column getColumn(int index) {
    return columnList.get(index);
  }

  public List<Column> getColumnDescriptors() {
    return columnList;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Schema) {
      Schema that = (Schema) o;
      return columnList.equals(that.columnList);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return columnList.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Schema {");
    builder.append("columns: [");
    columnList.forEach((column) -> {
      builder
          .append(column.getName())
          .append(" ")
          .append(column.getType())
          .append(", ");
    });
    if (!columnList.isEmpty()) {
      // Remove the last ", "
      builder.setLength(builder.length() - 2);
    }
    builder.append("],");
    builder.append(" length: ").append(length);
    builder.append("}");

    return builder.toString();
  }
}
