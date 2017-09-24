package com.hopkins.simpledb.data;

import com.hopkins.simpledb.util.Preconditions;

public class Column {
  public static final String ROW_ID_NAME = "_id";
  public static final Column ROW_ID = Column.newIntColumn(ROW_ID_NAME);

  private final ColumnType type;
  private final String name;
  private final int length;

  public static Column newStringColumn(String name, int length) {
    return new Column(ColumnType.STRING, name, length);
  }

  public static Column newBoolColumn(String name) {
    return new Column(ColumnType.BOOL, name, 1);
  }

  public static Column newIntColumn(String name) {
    return new Column(ColumnType.INTEGER, name, 4);
  }

  public static Column newDoubleColumn(String name) {
    return new Column(ColumnType.DOUBLE, name, 8);
  }

  public static Column newBlobColumn(String name, int length) {
    return new Column(ColumnType.BLOB, name, length);
  }

  private Column(ColumnType type, String name, int length) {
    Preconditions.checkNotNull(type);
    Preconditions.checkNotEmpty(name);

    this.type = type;
    this.name = name;
    this.length = length;
  }

  public ColumnType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public int getLength() {
    return length;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Column)) {
      return false;
    }
    Column that = (Column) obj;
    return type == that.type
        && name.equals(that.name)
        && length == that.length;
  }

  @Override
  public int hashCode() {
    int result = type.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + length;
    return result;
  }

  @Override
  public String toString() {
    return "Column {"
        + "type: " + type
        + ", name: " + name
        + ", length: " + length
        + '}';
  }
}
