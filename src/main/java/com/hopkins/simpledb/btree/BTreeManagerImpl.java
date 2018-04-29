package com.hopkins.simpledb.btree;

import com.hopkins.simpledb.app.*;
import com.hopkins.simpledb.catalog.IndexDescriptor;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.RecordCompare;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.operations.DbIterator;

public class BTreeManagerImpl implements BTreeManager {

  private final HeapManager heapManager;
  private final CacheManager cacheManager;

  public BTreeManagerImpl(HeapManager heapManager, CacheManager cacheManager) {
    this.heapManager = heapManager;
    this.cacheManager = cacheManager;
  }

  @Override
  public void populateIndex(IndexDescriptor index, TableDescriptor table) {
    DbIterator iter = heapManager.getScan(table);
    try {
      iter.open();
      while (iter.hasNext()) {
        Record tableRecord = iter.next();
        insert(index, tableRecord);
      }
    } finally {
      iter.close();
    }
  }

  private BTreePage getRoot(IndexDescriptor index) {
    return getBTreePage(index, index.getRootPageNumber());
  }

  private BTreePage getBTreePage(IndexDescriptor index, int pageNumber) {
    Page page = cacheManager.getPage(pageNumber);
    PageType pageType = PageType.fromValue(page.getBuffer()[0]);
    if (pageType == PageType.INDEX_BTREE_LEAF) {
      return new BTreeLeafPage(index, page);
    } else {
      return new BTreeInteriorPage(index, page);
    }
  }

  @Override
  public void insert(IndexDescriptor index, Record tableRecord) {
    Record indexRecord = buildIndexRecord(index, tableRecord);
    int pageNumber = index.getRootPageNumber();

    BTreeInteriorPage newPage = insert(index, pageNumber, indexRecord);
    if (newPage != null) {
      // Create a new root

    }
  }

  private BTreeInteriorPage insert(IndexDescriptor index, int pageNumber, Record indexRecord) {
    Page page = cacheManager.getPage(pageNumber);
    BTreeInteriorPage parent = null;
    BTreeInteriorPage current = new BTreeInteriorPage(index, page);
    BTreeInteriorPage newPage = null;

    while (true) {
      if (current.hasCapacity()) {

      }
    }
  }

  @Override
  public void update(IndexDescriptor index, Record oldTableRecord, Record newTableRecord) {
    delete(index, oldTableRecord);
    insert(index, newTableRecord);
  }

  @Override
  public void delete(IndexDescriptor index, Record tableRecord) {
    Record indexRecord = buildIndexRecord(index, tableRecord);

  }

  @Override
  public int findExact(IndexDescriptor index, Record indexRecord) {
    Record first = findFirst(index, indexRecord);
    if (first != null && RecordCompare.compare(indexRecord, first) == 0) {
      return first.getRowId();
    } else {
      return -1;
    }
  }

  @Override
  public Record findFirst(IndexDescriptor index, Record indexRecord) {
    BTreePage page = getRoot(index);
    Record bestRecord = null;

    while(true) {
      if (page.isLeafNode()) {
        int position = page.findPositionGreaterOrEqualTo(indexRecord);
        if (position < page.getRecordCount()) {
          // Record found in leaf
          return page.getRecord(position);
        } else {
          // Record not found in leaf, return best from interior nodes
          return bestRecord;
        }
      } else {
        int nextPageNumber;
        BTreeInteriorPage interiorPage = (BTreeInteriorPage) page;
        int position = page.findPositionGreaterOrEqualTo(indexRecord);
        if (position < page.getRecordCount()) {
          // Record found in interior, explore the left subtree
          bestRecord = page.getRecord(position);
          nextPageNumber = interiorPage.getLeftPage(position);
        } else {
          // Record not found in interior, explore the right subtree
          nextPageNumber = interiorPage.getRightPage();
        }

        // Move down to the next page
        page.unpin();
        page = getBTreePage(index, nextPageNumber);
      }
    }

  }

  @Override
  public Record findNext(IndexDescriptor index, Record previousIndexRecord) {
    // Find the next record by looking for the same record with a higher rowId
    Record searchFor = Record.copyOf(previousIndexRecord);
    int searchForRowId = previousIndexRecord.getInt(Column.ROW_ID_NAME) + 1;
    searchFor.set(Column.ROW_ID_NAME, searchForRowId);
    return findFirst(index, searchFor);
  }

  @Override
  public DbIterator scan(IndexDescriptor index, Record start, Record end) {
    return new BTreeIndexScan(this, index, start, end);
  }

  private Record buildIndexRecord(IndexDescriptor index, Record tableRecord) {
    // Build the index record by copying values from the table record

    Schema indexSchema = index.getSchema();
    Record indexRecord = new Record(indexSchema);
    for (int i = 0; i < indexSchema.getColumnCount(); i++) {
      String columnName = indexSchema.getColumnName(i);
      Object value = tableRecord.get(columnName);
      indexRecord.set(i, value);
    }

    return indexRecord;
  }
}
