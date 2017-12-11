package com.hopkins.simpledb.operations;

public class SortColumn {
  public enum SortDirection {
    ASCENDING,
    DESCENDING
  }

  private final String columnName;
  private final SortDirection direction;

  public SortColumn(String columnName, SortDirection direction) {
    this.columnName = columnName;
    this.direction = direction;
  }

  public String getColumnName() {
    return columnName;
  }

  public SortDirection getDirection() {
    return direction;
  }

  @Override
  public String toString() {
    return "SortColumn {"
        + "column: " + columnName
        + ", direction: " + direction
        + "}";
  }
}
