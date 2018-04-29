package com.hopkins.simpledb.btree;

import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.app.PageType;
import com.hopkins.simpledb.data.ByteWriter;
import com.hopkins.simpledb.data.Record;

public interface BTreePage {

  // BTree Page Layout
  // ================
  // ~~~~ Data offset ~~~~
  // byte pageType - the type
  // int numCells - the number of records on this page
  // Cell cell1
  // Cell cell2
  // [int rightPage] - [INTERIOR PAGES ONLY] page number of

  // ~~~~ Interior Cell  ~~~~
  // int leftPage
  // Record indexRecord

  // ~~~~ Leaf Cell ~~~~
  // Record indexRecord

  static void initializePage(Page page, boolean isLeaf) {
    PageType pageType = isLeaf ? PageType.INDEX_BTREE_LEAF : PageType.INDEX_BTREE_INTERNAL;
    ByteWriter byteWriter = new ByteWriter(page.getBuffer());
    // Page type
    byteWriter.writeByte(pageType.getValue());
    // Num records
    byteWriter.writeInt(0);
    page.setIsDirty(true);
  }

  boolean isTreeRoot();

  boolean isLeafNode();

  boolean isEmpty();

  int getPageNumber();

  int getRecordCapacity();

  int getRecordCount();

  boolean hasCapacity();

  Record getRecord(int position);

  int findPositionGreaterOrEqualTo(Record record);

  void unpin();
}
