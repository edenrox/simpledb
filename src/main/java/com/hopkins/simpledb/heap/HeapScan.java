package com.hopkins.simpledb.heap;

import com.hopkins.simpledb.app.CacheManager;
import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.operations.DbIterator;

import java.util.NoSuchElementException;

final class HeapScan implements DbIterator {
  private final TableDescriptor table;
  private final CacheManager cacheManager;
  private HeapPage heapPage;
  private int index;

  HeapScan(TableDescriptor table, CacheManager cacheManager) {
    this.table = table;
    this.cacheManager = cacheManager;
  }

  @Override
  public void open() {
    Page rootPage = cacheManager.getPage(table.getRootPageNumber());
    heapPage = new HeapPage(rootPage, table);
    index = -1;
    moveToNext();
  }

  private void moveToNext() {
    if (index + 1 < heapPage.getNumRecords()) {
      index++;
      return;
    }
    int nextPageNumber = heapPage.getNextPageNumber();
    heapPage.unpin();
    if (nextPageNumber == -1) {
      heapPage = null;
    } else {
      Page nextPage = cacheManager.getPage(nextPageNumber);
      heapPage = new HeapPage(nextPage, table);
    }
  }

  @Override
  public boolean hasNext() {
    return heapPage != null;
  }

  @Override
  public Record next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Record record = heapPage.getRecord(index);
    moveToNext();
    return record;
  }

  @Override
  public Schema getSchema() {
    return table.getSchema();
  }

  @Override
  public void reset() {
    close();
    open();
  }

  @Override
  public void close() {
    if (heapPage != null) {
      heapPage.unpin();
    }
    heapPage = null;
  }
}
