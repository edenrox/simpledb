package com.hopkins.simpledb.heap;

import com.hopkins.simpledb.app.CacheManager;
import com.hopkins.simpledb.app.HeapManager;
import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.operations.DbIterator;

public class HeapManagerImpl implements HeapManager {
  private final CacheManager cacheManager;

  public HeapManagerImpl(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @Override
  public DbIterator getScan(TableDescriptor table) {
    return new HeapScan(table, cacheManager);
  }

  @Override
  public void insert(TableDescriptor table, Record record) {
    HeapPage rootPage = getRootPage(table);
    int rowId = rootPage.getAndIncRowId();
    rootPage.unpin();

    record.set(Column.ROW_ID_NAME, rowId);
    HeapPage heapPage = findPageWithEmptySpace(table);
    heapPage.insertRecord(record);
    heapPage.unpin();
  }

  @Override
  public void delete(TableDescriptor table, int rowId) {
    HeapPage heapPage = findPageWithRowId(table, rowId);
    if (heapPage != null) {
      heapPage.removeRecord(rowId);
      heapPage.unpin();
    }
  }

  @Override
  public void update(TableDescriptor table, int rowId, Record record) {
    HeapPage heapPage = findPageWithRowId(table, rowId);
    if (heapPage != null) {
      heapPage.replaceRecord(rowId, record);
      heapPage.unpin();
    }
  }

  private HeapPage findPageWithEmptySpace(TableDescriptor table) {
    HeapPage heapPage = getRootPage(table);
    while (true) {
      if (heapPage.hasEmptySpace()) {
        return heapPage;
      }
      int nextPageNumber = heapPage.getNextPageNumber();
      if (nextPageNumber > 0) {
        // Move to the next page in the table to look for empty space
        heapPage.unpin();
        heapPage = new HeapPage(cacheManager.getPage(nextPageNumber), table);
      } else {
        // We've reached the last page in the table, allocate a new page
        Page newPage = cacheManager.getNewPage();
        HeapPage.initializePage(newPage, false);
        HeapPage newHeapPage = new HeapPage(newPage, table);
        heapPage.setNextPageNumber(newHeapPage.getPage().getPageNumber());
        heapPage.unpin();
        return newHeapPage;
      }
    }
  }

  private HeapPage findPageWithRowId(TableDescriptor table, int rowId) {
    HeapPage heapPage = getRootPage(table);
    while (true) {
      if (heapPage.containsRowId(rowId)) {
        return heapPage;
      }
      int nextPageNumber = heapPage.getNextPageNumber();
      heapPage.unpin();
      if (nextPageNumber <= 0) {
        return null;
      }
      heapPage = new HeapPage(cacheManager.getPage(nextPageNumber), table);
    }
  }

  private HeapPage getRootPage(TableDescriptor table) {
    return new HeapPage(cacheManager.getPage(table.getRootPageNumber()), table);
  }
}
