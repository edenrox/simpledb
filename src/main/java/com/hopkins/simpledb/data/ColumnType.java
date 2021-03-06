package com.hopkins.simpledb.data;

/**
 * Created by edenrox on 5/29/2017.
 */
public enum ColumnType {
  INTEGER('c', 0),
  STRING('s', ""),
  DOUBLE('d', 0.0),
  BOOL('z', false),
  BLOB('b', new byte[0]);

  public static ColumnType fromValue(char value) {
    switch (value) {
      case 'c':
        return INTEGER;
      case 's':
        return STRING;
      case 'd':
        return DOUBLE;
      case 'z':
        return BOOL;
      case 'b':
        return BLOB;
      default:
        throw new IllegalArgumentException("Unknown value: " + value);
    }
  }

  private final char value;
  private final Object defaultValue;

  ColumnType(char value, Object defaultValue) {
    this.value = value;
    this.defaultValue = defaultValue;
  }

  public char getValue() {
    return value;
  }

  public Object getDefaultValue() {
    return defaultValue;
  }
}
