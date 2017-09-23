package com.hopkins.simpledb.freepages;

import com.hopkins.simpledb.app.CacheManager;
import com.hopkins.simpledb.app.FreePageManager;
import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.app.PageType;
import com.hopkins.simpledb.data.ByteReader;
import com.hopkins.simpledb.data.ByteWriter;


public class FreePageManagerImpl implements FreePageManager {
  private static final int NO_FREE_PAGE_INDEX = -1;
  private static final int ROOT_PAGE = 0;

  private int freePage = NO_FREE_PAGE_INDEX;

  private final CacheManager cacheManager;

  public FreePageManagerImpl(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @Override
  public void freePage(int pageNumber) {
    // Set the freePage on the catalog root page
    Page rootPage = cacheManager.getPage(Page.CATALOG_ROOT_PAGE_NUMBER);
    ByteReader rootByteReader = new ByteReader(rootPage.getBuffer());
    rootByteReader.setPosition(1);
    int oldFreePageNumber = rootByteReader.readInt();
    ByteWriter rootByteWriter = new ByteWriter(rootPage.getBuffer());
    rootByteReader.setPosition(1);
    rootByteWriter.writeInt(pageNumber);
    rootPage.setIsDirty(true);
    rootPage.unpin();

    // Set the type and next free page items on the newly free page
    Page freePage = cacheManager.getPage(pageNumber);
    ByteWriter freePageWriter = new ByteWriter(freePage.getBuffer());
    freePageWriter.setPosition(0);
    freePageWriter.writeByte(PageType.FREE.getValue());
    freePageWriter.writeInt(oldFreePageNumber);
    freePage.setIsDirty(true);
    freePage.unpin();
  }

  @Override
  public int allocPage() {
    // Find the free page pointed to from the catalog root
    Page rootPage = cacheManager.getPage(Page.CATALOG_ROOT_PAGE_NUMBER);
    ByteReader rootByteReader = new ByteReader(rootPage.getBuffer());
    rootByteReader.setPosition(1);
    int freePageNumber = rootByteReader.readInt();

    if (freePageNumber <= 0) {
      // No free pages, allocate a new page
    }

    // Free page found, read the next free page number
    Page freePage = cacheManager.getPage(freePageNumber);
    ByteReader freePageReader = new ByteReader(freePage.getBuffer());
    freePageReader.setPosition(1);
    int nextFreePageNumber = freePageReader.readInt();
    freePage.unpin();

    // Update the root page
    ByteWriter rootWriter = new ByteWriter(rootPage.getBuffer());
    rootWriter.setPosition(1);
    rootWriter.writeInt(nextFreePageNumber);
    rootPage.setIsDirty(true);
    rootPage.unpin();

    return freePageNumber;
  }
}
