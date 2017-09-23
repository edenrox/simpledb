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

public class CacheManagerImpl implements CacheManager {

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
    this.pageList = new ArrayList<>(maxPages);
    this.pageLookup = new HashMap<>(maxPages);
  }

  @Override
  public Page getPage(int pageNumber) {
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

    // The cache has un-initialized pages, initialize a new one
    if (pageList.size() < maxPages) {
      // allocate a new page cache page
      byte[] pageBuffer = new byte[config.getPageSize()];
      Page page = new Page(pageBuffer);
      pageList.add(page);

      readPage(page, pageNumber);
      pageLookup.put(pageNumber, page);

      return page;
    }

    // Find an evictable page
    Page page = findEvictablePage();
    pageLookup.remove(page.getPageNumber());

    readPage(page, pageNumber);
    pageLookup.put(pageNumber, page);
    return page;
  }

  private void readPage(Page page, int pageNumber) {
    page.setPageNumber(pageNumber);
    try {
      diskFileManager.readPage(pageNumber, page.getBuffer());
    } catch (IOException ex) {
      throw new RuntimeException("Error reading page", ex);
    }
  }

  private Page findEvictablePage() {
    int numPages = pageList.size();
    // Look for a non-dirty, un-pinned page
    for (int i = 0; i < numPages * 2; i++) {
      int position = (index + i) % numPages;
      Page page = pageList.get(position);
      if (page.getPinCount() > 0 || page.isDirty()) {
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
}
