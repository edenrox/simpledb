package com.hopkins.simpledb.bufferpool;

import com.hopkins.simpledb.app.Config;
import com.hopkins.simpledb.app.ServiceLocator;
import com.hopkins.simpledb.util.Preconditions;

import java.util.HashMap;

/**
 * Created by ian_000 on 5/31/2017.
 */
public class BufferPool {
  public static final int DEFAULT_NUM_PAGES = 100;

  private final ServiceLocator serviceLocator;
  private final int numPages;
  private final BufferPage[] pages;
  private final HashMap<PageId, BufferPage> pageMap;

  public static BufferPool newDefaultPool(ServiceLocator serviceLocator) {
    return new BufferPool(serviceLocator, DEFAULT_NUM_PAGES);
  }

  public BufferPool(ServiceLocator serviceLocator, int numPages) {
    Preconditions.checkNotNull(serviceLocator);
    Preconditions.checkArgument(numPages >= 1);

    this.serviceLocator = serviceLocator;
    this.numPages = numPages;
    this.pageMap = new HashMap<>();

    // Allocate the buffer pages & add to the free page list
    byte[][] buffers = new byte[numPages][Config.PAGE_SIZE];
    this.pages = new BufferPage[numPages];
    for (int i = 0; i < numPages; i++) {
      pages[i] = new BufferPage(buffers[i]);
    }
  }

  public int getPageSize() {
    return Config.PAGE_SIZE;
  }

  public int getNumPages() {
    return numPages;
  }

  public void flushPage(PageId pageId) {
    if (pageId.isTemporaryPage()) {
      // temporary pages don't need to be flushed
      return;
    }
    BufferPage page = pageMap.get(pageId);
    Preconditions.checkState(page != null);

    DiskManager.writePage(pageId, page.getBuffer());
    page.setDirty(false);
  }

  /** Returns the {@link BufferPage} for the specified {@link PageId}. */
  public BufferPage getPage(PageId pageId) {
    if (pageId.isTemporaryPage()) {
      return findPage();
    }

    if (pageMap.containsKey(pageId)) {
      return pageMap.get(pageId);
    }
    BufferPage page = findPage();
    DiskManager.readPage(pageId, page.getBuffer());

    page.setPageId(pageId);
    pageMap.put(pageId, page);
    return page;
  }

  private BufferPage findPage() {
    // Replacement policy: random

    // Find a non-dirty page, and replace it
    int offset = (int) (Math.random() * numPages);
    for (int i = 0; i < numPages; i++) {
      int index = (i + offset) % numPages;
      BufferPage page = pages[index];
      if (!page.isDirty()) {
        return removePage(page);
      }
    }

    // Find a dirty page
    for (int i = 0; i < numPages; i++) {
      int index = (i + offset) % numPages;
      BufferPage page = pages[index];
      return removePage(page);
    }

    throw new IllegalStateException("No pages found");
  }

  private BufferPage removePage(BufferPage page) {
    if (page.isDirty()) {
      // dirty pages should be flushed
      flushPage(page.getPageId());
    }
    pageMap.remove(page.getPageId());
    page.setPageId(null);
    return page;
  }
}
