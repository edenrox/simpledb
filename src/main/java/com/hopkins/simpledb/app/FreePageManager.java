package com.hopkins.simpledb.app;

public interface FreePageManager {
  public static final int NO_FREE_PAGE_INDEX = -1;

  void freePage(int pageNumber);

  int allocPage();
}
