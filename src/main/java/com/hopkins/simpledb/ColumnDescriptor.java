package com.hopkins.simpledb;

/**
 * Created by edenrox on 5/29/2017.
 */
public class ColumnDescriptor {
  private final ColumnType type;
  private final String name;
  private final int length;

  public static ColumnDescriptor newStringColumn(String name, int length) {
    return new ColumnDescriptor(ColumnType.STRING, name, length);
  }

  public static ColumnDescriptor newBoolColumn(String name) {
    return new ColumnDescriptor(ColumnType.BOOL, name, 1);
  }

  public static ColumnDescriptor newIntColumn(String name) {
    return new ColumnDescriptor(ColumnType.INTEGER, name, 4);
  }

  public static ColumnDescriptor newDoubleColumn(String name) {
    return new ColumnDescriptor(ColumnType.DOUBLE, name, 8);
  }

  private ColumnDescriptor(ColumnType type, String name, int length) {
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
