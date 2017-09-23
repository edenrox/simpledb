package com.hopkins.simpledb.cache;

public final class CacheFullException extends RuntimeException {
  public CacheFullException(String message) {
    super(message);
  }
}
