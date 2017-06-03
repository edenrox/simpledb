package com.hopkins.simpledb.util;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class ArrayUtil {

  public static int indexOf(byte[] bytes, byte toFind) {
    for (int i = 0; i < bytes.length; i++) {
      if (bytes[i] == toFind) {
        return i;
      }
    }
    return -1;
  }
}
