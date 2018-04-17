package com.hopkins.simpledb.data;

public class RecordCompare {

  public static int compare(Record a, Record b) {
    Schema schema = a.getSchema();
    for (int i = 0; i < schema.getColumnCount(); i++) {
      ColumnType columnType = schema.getColumnType(i);
      int compare = ColumnCompare.compare(columnType, a.get(i), b.get(i));
      if (compare != 0) {
        return compare;
      }
    }
    return 0;
  }
}
