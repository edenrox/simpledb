package com.hopkins.simpledb.data;

/**
 * Created by edenrox on 5/29/2017.
 */
public class Column {
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

  private Column(ColumnType type, String name, int length) {
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
}
