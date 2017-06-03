package com.hopkins.simpledb.bufferpool;

import com.hopkins.simpledb.HeapFile;
import com.hopkins.simpledb.app.ServiceLocator;
import com.hopkins.simpledb.table.Catalog;
import com.hopkins.simpledb.table.CatalogImpl;
import com.hopkins.simpledb.table.Table;
import com.hopkins.simpledb.util.Preconditions;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by ian_000 on 5/31/2017.
 */
public class BufferPool {
  public static final int DEFAULT_PAGE_SIZE = 4 * 1024;
  public static final int DEFAULT_NUM_PAGES = 100;

  private final ServiceLocator serviceLocator;
  private final int pageSize;
  private final int numPages;
  private final BufferPage[] pages;
  private final HashMap<PageId, BufferPage> pageMap;

  public static BufferPool newDefaultPool(ServiceLocator serviceLocator) {
    return new BufferPool(serviceLocator, DEFAULT_PAGE_SIZE, DEFAULT_NUM_PAGES);
  }

  public BufferPool(ServiceLocator serviceLocator, int pageSize, int numPages) {
    Preconditions.checkNotNull(serviceLocator);
    Preconditions.checkArgument(pageSize >= 1024);
    Preconditions.checkArgument(numPages >= 1);

    this.serviceLocator = serviceLocator;
    this.pageSize = pageSize;
    this.numPages = numPages;
    this.pageMap = new HashMap<>();

    // Allocate the buffer pages & add to the free page list
    byte[][] buffers = new byte[numPages][pageSize];
    this.pages = new BufferPage[numPages];
    for (int i = 0; i < numPages; i++) {
      pages[i] = new BufferPage(buffers[i]);
    }
  }

  public int getPageSize() {
    return pageSize;
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

    HeapFile heapFile = getHeapFile(pageId.getTableId());
    try {
      heapFile.writePage(pageId.getPageNumber(), page.getBuffer());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
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
    HeapFile heapFile = getHeapFile(pageId.getTableId());
    try {
      heapFile.readPage(pageId.getPageNumber(), page.getBuffer());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }

    page.setPageId(pageId);
    pageMap.put(pageId, page);
    return page;
  }

  private HeapFile getHeapFile(int tableId) {
    Catalog catalog = serviceLocator.get(Catalog.class);
    Table table = catalog.getTable(tableId);
    return table.getHeapFile();
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
