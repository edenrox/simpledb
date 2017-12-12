package com.hopkins.simpledb;

import com.hopkins.simpledb.data.Record;

import java.util.List;

public class RecordUtil {
  public static void printAll(List<Record> recordList) {
    if (recordList.isEmpty()) {
      System.err.println("Empty recordList");
    }
    System.err.println(recordList.get(0).getSchema());
    for (Record record : recordList) {
      System.err.println(record.toString());
    }
  }
}
