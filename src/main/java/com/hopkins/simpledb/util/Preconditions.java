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

  public static void checkArgument(boolean toCheck) {
    if (!toCheck) {
      throw new IllegalArgumentException();
    }
  }

  public static void checkState(boolean toCheck) {
    if (!toCheck) {
      throw new IllegalStateException();
    }
  }
}
