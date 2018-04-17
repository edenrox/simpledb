package com.hopkins.simpledb.util;

/**
 * Created by ian_000 on 5/31/2017.
 */
public class Preconditions {

  public static void checkNotNull(Object arg) {
    if (arg == null) {
      throw new NullPointerException();
    }
  }

  public static void checkNotEmpty(String arg) {
    if (arg == null || arg.length() == 0) {
      throw new NullPointerException();
    }
  }

  public static void checkArgument(boolean toCheck) {
    if (!toCheck) {
      throw new IllegalArgumentException();
    }
  }

  public static void checkArgument(boolean toCheck, String message) {
    if (!toCheck) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkState(boolean toCheck) {
    if (!toCheck) {
      throw new IllegalStateException();
    }
  }

  public static void checkBounds(int index, int size) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Negative index: " + index);
    } else if (index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + " greater than size: " + size);
    }
  }
}
