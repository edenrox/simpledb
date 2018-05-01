package com.hopkins.simpledb.freepages;

import com.hopkins.simpledb.app.*;
import com.hopkins.simpledb.data.ByteReader;
import com.hopkins.simpledb.data.ByteWriter;


public class FreePageManagerImpl implements FreePageManager {
  private final CacheManager cacheManager;

  public FreePageManagerImpl(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  private FreePage getRootFreePage() {
    Page rootPage = cacheManager.getPage(Page.CATALOG_ROOT_PAGE_NUMBER);
    return new FreePage(rootPage);
  }

  @Override
  public void freePage(int pageNumber) {
    // Get the head of the free page list
    FreePage rootFreePage = getRootFreePage();
    int oldFreePageNumber = rootFreePage.getNextFreePage();

    // Initialize the requested page as a free page
    FreePage newFreePage = FreePage.initializePage(cacheManager.getPage(pageNumber));
    newFreePage.setNextFreePage(oldFreePageNumber);
    newFreePage.unpin();

    // Update the head of the free page list
    rootFreePage.setNextFreePage(pageNumber);
    rootFreePage.unpin();
  }

  @Override
  public int allocPage() {
    // Find the free page pointed to from the catalog root
    FreePage rootFreePage = getRootFreePage();
    int freePageNumber = rootFreePage.getNextFreePage();

    if (freePageNumber == FreePageManager.NO_FREE_PAGE_INDEX) {
      rootFreePage.unpin();

      // No free pages, allocate a new page
      FreePage freePage = FreePage.initializePage(cacheManager.getNewPage());
      freePageNumber = freePage.getPageNumber();
      freePage.unpin();
      return freePageNumber;
    }

    FreePage freePage = new FreePage(cacheManager.getPage(freePageNumber));
    int nextFreePageNumber = freePage.getNextFreePage();
    freePage.unpin();

    // Update the root page
    rootFreePage.setNextFreePage(nextFreePageNumber);
    rootFreePage.unpin();

    return freePageNumber;
  }
}
