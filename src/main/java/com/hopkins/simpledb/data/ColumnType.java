package com.hopkins.simpledb.data;

/**
 * Created by edenrox on 5/29/2017.
 */
public enum ColumnType {
  INTEGER('c'),
  STRING('s'),
  DOUBLE('d'),
  BOOL('b');

  public static ColumnType fromValue(char value) {
    switch (value) {
      case 'c':
        return INTEGER;
      case 's':
        return STRING;
      case 'd':
        return DOUBLE;
      case 'b':
        return BOOL;
      default:
        throw new IllegalArgumentException("Unknown value: " + value);
    }
  }

  private final char value;

  ColumnType(char value) {
    this.value = value;
  }

  public char getValue() {
    return value;
  }
}
