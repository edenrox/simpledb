package com.hopkins.simpledb.heap;

import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.app.PageType;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.*;
import com.hopkins.simpledb.util.Preconditions;

public final class HeapPage {
  // Heap Page Layout
  // ================
  // ~~~~ Data offset ~~~~
  // byte pageType - the type
  // [int freePage] - [ROOT PAGE ONLY] the start of the free page list (-1 if empty)
  // [int nextRowId] - the next row Id for this table
  // int nextPage - the page number of the next page in the heap (-1 if no further pages)
  // int numRecords - the number of records on this page
  // Record record1
  // Record record2...

  public static final void initializePage(Page page, boolean isTableRoot) {
    ByteWriter byteWriter = new ByteWriter(page.getBuffer());
    byteWriter.writeByte(PageType.HEAP_PAGE.getValue());
    if (page.isCatalogRootPage()) {
      // freePage
      byteWriter.writeInt(-1);
    }
    if (isTableRoot) {
      // nextRowId
      byteWriter.writeInt(1);
    }
    // nextPage
    byteWriter.writeInt(-1);
    // numRecords
    byteWriter.writeInt(0);
    page.setIsDirty(true);
  }

  private static final int INDEX_NOT_FOUND = -1;

  private final Page page;
  private final TableDescriptor table;
  private final ByteReader byteReader;
  private final ByteWriter byteWriter;
  private final int dataOffset;

  public HeapPage(Page page, TableDescriptor table) {
    this.page = page;
    this.table = table;
    this.dataOffset = getDataOffset();
    this.byteReader = new ByteReader(page.getBuffer());
    this.byteWriter = new ByteWriter(page.getBuffer());
    Preconditions.checkArgument(page.getType() == PageType.HEAP_PAGE);
  }

  private int getDataOffset() {
    int offset = 1; // byte pageType
    if (page.isCatalogRootPage()) {
      offset += 4; // int freePage
    }
    if (isTableRoot()) {
      offset += 4; // int rowId;
    }
    return offset;
  }

  private boolean isTableRoot() {
    return page.getPageNumber() == table.getRootPageNumber();
  }

  public Page getPage() {
    return page;
  }

  public void unpin() {
    page.unpin();
  }

  public int getAndIncRowId() {
    Preconditions.checkState(isTableRoot());
    int rowIdOffset = dataOffset - 4;

    byteReader.setPosition(rowIdOffset);
    int rowId = byteReader.readInt();

    byteWriter.setPosition(rowIdOffset);
    byteWriter.writeInt(rowId + 1);
    page.setIsDirty(true);
    return rowId;
  }

  public int getNextPageNumber() {
    byteReader.setPosition(dataOffset);
    return byteReader.readInt();
  }

  public void setNextPageNumber(int nextPageNumber) {
    byteWriter.setPosition(dataOffset);
    byteWriter.writeInt(nextPageNumber);
    page.setIsDirty(true);
  }

  public int getNumRecords() {
    byteReader.setPosition(dataOffset + 4);
    return byteReader.readInt();
  }

  private void setNumRecords(int numRecords) {
    byteWriter.setPosition(dataOffset + 4);
    byteWriter.writeInt(numRecords);
  }

  private int getOffsetByIndex(int index) {
    return dataOffset + 9 + index * table.getSchema().getLength();
  }

  private int getIndexOf(int rowId) {
    for (int i = 0; i < getNumRecords(); i++) {
      int offset = getOffsetByIndex(i);
      // Record (int rowId, ...)
      byteReader.setPosition(offset);
      int recordRowId = byteReader.readInt();
      if (recordRowId == rowId) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  public Record getRecord(int index) {
    int offset = getOffsetByIndex(index);
    byteReader.setPosition(offset);
    return RecordIo.readRecord(byteReader, table.getSchema());
  }

  public void removeRecord(int rowId) {
    int index = getIndexOf(rowId);
    if (index == INDEX_NOT_FOUND) {
      return;
    }

    int numToShift = getNumRecords() - index - 1;
    if (numToShift > 0) {
      // shift further records on this page back one slot
      int destOffset = getOffsetByIndex(index);
      int srcOffset = getOffsetByIndex(index + 1);
      int length = numToShift * table.getSchema().getLength();
      System.arraycopy(page.getBuffer(), srcOffset, page.getBuffer(), destOffset, length);
    }

    setNumRecords(getNumRecords() - 1);
    page.setIsDirty(true);
  }

  public void replaceRecord(int rowId, Record newRecord) {
    int index = getIndexOf(rowId);
    if (index == INDEX_NOT_FOUND) {
      return;
    }
    int offset = getOffsetByIndex(index);

    newRecord.set(Column.ROW_ID_NAME, rowId);
    byteWriter.setPosition(offset);
    RecordIo.writeRecord(byteWriter, newRecord);
    page.setIsDirty(true);
  }

  public void insertRecord(Record newRecord) {
    int index = getNumRecords();
    int offset = getOffsetByIndex(index);

    byteWriter.setPosition(offset);
    RecordIo.writeRecord(byteWriter, newRecord);

    setNumRecords(index + 1);
    page.setIsDirty(true);
  }

  public boolean hasEmptySpace() {
    int numRecords = getNumRecords();
    int offset = getOffsetByIndex(numRecords + 2);
    return offset < page.getBuffer().length;
  }

  public boolean containsRowId(int rowId) {
    return getIndexOf(rowId) > -1;
  }

  @Override
  public String toString() {
    return "HeapPage {"
        + "pageNumber: " + page.getPageNumber()
        + ", nextPageNumber: " + getNextPageNumber()
        + ", numRecords: " + getNumRecords()
        + "}";
  }
}
