package com.hopkins.simpledb.heap;

import com.hopkins.simpledb.app.CacheManager;
import com.hopkins.simpledb.app.DiskFileManager;
import com.hopkins.simpledb.app.Page;

public class HeapInitializer {
  private final DiskFileManager diskFileManager;
  private final CacheManager cacheManager;

  public HeapInitializer(DiskFileManager diskFileManager, CacheManager cacheManager) {
    this.diskFileManager = diskFileManager;
    this.cacheManager = cacheManager;
  }

  public void init() {
    if (diskFileManager.getPageCount() == 0) {
      Page rootPage = cacheManager.getNewPage();
      HeapPage.initializePage(rootPage, true);
      rootPage.unpin();
    } else {
      // Verify that the first page matches the expected format
      throw new RuntimeException("unexpected");
    }
  }
}
