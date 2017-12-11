package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Record;

import java.util.ArrayList;
import java.util.List;

public class DbIteratorUtil {

  public static void reset(DbIterator iterator) {
    iterator.close();
    iterator.open();
  }

  public static List<Record> openReadAllClose(DbIterator iterator) {
    iterator.open();
    List<Record> list = readAll(iterator);
    iterator.close();
    return list;
  }

  public static List<Record> readAll(DbIterator iterator) {
    List<Record> list = new ArrayList<>();
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }
    return list;
  }

  public static List<Object> openReadAllColumnClose(DbIterator iterator, String columnName) {
    iterator.open();
    List<Object> list = readAllColumn(iterator, columnName);
    iterator.close();
    return list;
  }

  public static List<Object> readAllColumn(DbIterator iterator, String columnName) {
    List<Object> list = new ArrayList<>();
    while (iterator.hasNext()) {
      list.add(iterator.next().get(columnName));
    }
    return list;
  }

  public static int openCountClose(DbIterator iterator) {
    iterator.open();
    int count = count(iterator);
    iterator.close();
    return count;
  }

  public static int count(DbIterator iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      count++;
      iterator.next();
    }
    return count;
  }
}
