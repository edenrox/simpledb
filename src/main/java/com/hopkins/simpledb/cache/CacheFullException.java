package com.hopkins.simpledb.cache;

/**
 * A {@link RuntimeException} thrown when the {@link com.hopkins.simpledb.app.CacheManager} runs out of free pages in
 * its in memory page buffer.
 */
public final class CacheFullException extends RuntimeException {
  public CacheFullException(String message) {
    super(message);
  }
}
