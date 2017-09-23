package com.hopkins.simpledb.app;

public interface FreePageManager {

  void freePage(int pageNumber);

  int allocPage();
}
