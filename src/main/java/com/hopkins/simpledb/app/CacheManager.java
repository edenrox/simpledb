package com.hopkins.simpledb.app;

public interface CacheManager {

  Page getPage(int pageNumber);

  Page getNewPage();

  int getNumPinnedPages();
}
