package com.hopkins.simpledb.data;

public class ColumnCompare {
  public static int compare(ColumnType type, Object a, Object b) {
    switch (type) {
      case BOOL:
        return ((Boolean) a).compareTo((Boolean) b);
      case INTEGER:
        return ((Integer) a).compareTo((Integer) b);
      case STRING:
        return ((String) a).compareTo((String) b);
      case DOUBLE:
        return ((Double) a).compareTo((Double) b);
      case BLOB:
        byte[] ba = (byte[]) a;
        byte[] bb = (byte[]) b;
        for (int i = 0; i < ba.length; i++) {
          if (bb.length < i) {
            return 1;
          } else if (ba[i] < bb[i]) {
            return -1;
          } else if (ba[i] > bb[i]) {
            return 1;
          }
        }
        if (bb.length > ba.length) {
          return -1;
        }
        return 0;
      default:
        throw new IllegalArgumentException("Unknown column type: " + type);
    }
  }
}
