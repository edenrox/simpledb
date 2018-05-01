package com.hopkins.simpledb.cache;

import com.hopkins.simpledb.app.CacheManager;
import com.hopkins.simpledb.app.Config;
import com.hopkins.simpledb.app.DiskFileManager;
import com.hopkins.simpledb.app.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheManagerImpl implements CacheManager {
  private static final Logger logger = Logger.getLogger("CacheManager");

  private final Config config;
  private final DiskFileManager diskFileManager;
  private final int maxPages;
  private final List<Page> pageList;
  private final Map<Integer, Page> pageLookup;

  private int index;

  public CacheManagerImpl(Config config, DiskFileManager diskFileManager) {
    this.config = config;
    this.diskFileManager = diskFileManager;

    maxPages = config.getCacheSize() / config.getPageSize();
    pageList = new ArrayList<>(maxPages);
    pageLookup = new HashMap<>(maxPages);
  }

  @Override
  public Page getPage(int pageNumber) {
    if (logger.isLoggable(Level.FINER)) {
      logger.log(Level.FINER, "getPage(" + pageNumber + ")");
    }
    Page page = getPageInternal(pageNumber);
    page.setRecentlyUsed(true);
    page.pin();
    return page;
  }

  private Page getPageInternal(int pageNumber) {
    // The page is already in cache, just return it
    if (pageLookup.containsKey(pageNumber)) {
      return pageLookup.get(pageNumber);
    }

    // Find an evictable page
    Page page = findEvictablePage();
    evictPage(page);

    readPage(page, pageNumber);
    pageLookup.put(pageNumber, page);
    return page;
  }

  private void evictPage(Page page) {
    if (logger.isLoggable(Level.FINER)) {
      logger.log(Level.FINER, "evictPage(" + page.getPageNumber() + ")");
    }
    if (page.isDirty()) {
      writePage(page);
    }
    pageLookup.remove(page.getPageNumber());
  }

  private void readPage(Page page, int pageNumber) {
    if (logger.isLoggable(Level.FINER)) {
      logger.log(Level.FINER, "readPage(" + pageNumber + ")");
    }

    page.setPageNumber(pageNumber);
    try {
      diskFileManager.readPage(pageNumber, page.getBuffer());
    } catch (IOException ex) {
      throw new RuntimeException("Error reading page", ex);
    }
  }

  private void writePage(Page page) {
    if (logger.isLoggable(Level.FINER)) {
      logger.log(Level.FINER, "writePage(" + page.getPageNumber() + ")");
    }
    try {
      diskFileManager.writePage(page.getPageNumber(), page.getBuffer());
      page.setIsDirty(false);
    } catch (IOException ex) {
      throw new RuntimeException("Error writing page", ex);
    }
  }

  private Page findEvictablePage() {
    int numPages = pageList.size();

    // The cache has un-initialized pages, initialize a new one
    if (pageList.size() < maxPages) {
      // allocate a new page cache page
      byte[] pageBuffer = new byte[config.getPageSize()];
      Page page = new Page(pageBuffer);
      pageList.add(page);
      return page;
    }

    // Look for a non-dirty, un-pinned page
    for (int i = 0; i < numPages * 2; i++) {
      int position = (index + i) % numPages;
      Page page = pageList.get(position);
      if (page.getPinCount() > 0) {
        continue;
      }
      if (page.isRecentlyUsed()) {
        page.setRecentlyUsed(false);
      } else {
        index = position;
        return page;
      }
    }
    throw new CacheFullException("No un-pinned pages found");
  }

  @Override
  public Page getNewPage() {
    if (logger.isLoggable(Level.FINER)) {
      logger.log(Level.FINER, "getNewPage()");
    }
    // Find an evictable page
    Page page = findEvictablePage();
    evictPage(page);

    // Reset the page to empty
    resetPage(page);

    // Write the page to disk
    int pageNumber = diskFileManager.getPageCount();

    page.setPageNumber(pageNumber);
    page.setRecentlyUsed(true);
    page.pin();
    writePage(page);

    pageLookup.put(pageNumber, page);
    return page;
  }

  private void resetPage(Page page) {
    if (logger.isLoggable(Level.FINER)) {
      logger.log(Level.FINER, "resetPage(" + page.getPageNumber() + ")");
    }
    byte[] buffer = page.getBuffer();
    for (int i = 0; i < config.getPageSize(); i++) {
      buffer[i] = 0;
    }
  }

  @Override
  public int getNumPinnedPages() {
    int num = 0;
    for (Page page : pageList) {
      if (page.getPinCount() > 0) {
        num++;
      }
    }
    return num;
  }
}
