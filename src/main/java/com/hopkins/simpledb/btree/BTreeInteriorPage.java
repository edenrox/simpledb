package com.hopkins.simpledb.btree;

import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.catalog.IndexDescriptor;
import com.hopkins.simpledb.data.ByteReader;
import com.hopkins.simpledb.data.ByteWriter;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.RecordIo;
import com.hopkins.simpledb.util.Preconditions;

public class BTreeInteriorPage implements BTreePage {

  private final IndexDescriptor index;
  private final Page page;
  private final ByteReader byteReader;
  private final ByteWriter byteWriter;

  public BTreeInteriorPage(IndexDescriptor index, Page page) {
    this.index = index;
    this.page = page;
    this.byteReader = new ByteReader(page.getBuffer());
    this.byteWriter = new ByteWriter(page.getBuffer());
  }

  @Override
  public boolean isTreeRoot() {
    return page.getPageNumber() == index.getRootPageNumber();
  }

  @Override
  public boolean isLeafNode() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return getRecordCount() == 0;
  }

  @Override
  public int getPageNumber() {
    return page.getPageNumber();
  }

  @Override
  public int getRecordCapacity() {
    int recordSpace = page.getBuffer().length - 9;
    return recordSpace / getCellWidth();
  }

  @Override
  public int getRecordCount() {
    byteReader.setPosition(1);
    return byteReader.readInt();
  }

  private void setRecordCount(int numRecords) {
    byteWriter.setPosition(1);
    byteWriter.writeInt(numRecords);
  }

  @Override
  public boolean hasCapacity() {
    return getRecordCount() < getRecordCapacity();
  }

  @Override
  public void unpin() {
    page.unpin();
  }

  @Override
  public Record getRecord(int position) {
    byteReader.setPosition(getCellOffset(position));
    return RecordIo.readRecord(byteReader, index.getSchema());
  }

  public void insert(BTreePage leftPage, Record middle, BTreePage rightPage) {
    Preconditions.checkState(hasCapacity());
    int numRecords = getRecordCount();
    if (numRecords == 0 || getRightPage() == leftPage.getPageNumber()) {
      // New cell goes at the end of the list
      writeCell(numRecords, leftPage.getPageNumber(), middle);
      setRightPage(rightPage.getPageNumber());
      setRecordCount(numRecords + 1);
      return;
    }

    // New cell goes into the list
    int index = indexOf(leftPage);
    Preconditions.checkState(index >= 0);

    // Shift cells to make room for the new cell
    shiftCells(index);

    // Write the new cell
    writeCell(index, leftPage.getPageNumber(), middle);

    setRecordCount(numRecords + 1);
  }

  private void shiftCells(int position) {
    int numRecords = getRecordCount();

    int srcPos = getCellOffset(position);
    int dstPos = getCellOffset(position + 1);
    int length = getCellWidth() * (numRecords - position);

    System.arraycopy(page.getBuffer(), srcPos, page.getBuffer(), dstPos, length);
  }

  private void writeCell(int position, int leftPageNumber, Record record) {
    setLeftPage(position, leftPageNumber);
    byteWriter.setPosition(getCellOffset(position));
    RecordIo.writeRecord(byteWriter, record);
  }

  private int indexOf(BTreePage page) {
    int pageNumber = page.getPageNumber();
    for (int i = 0; i < getRecordCount(); i++) {
      if (getLeftPage(i) == pageNumber) {
        return i;
      }
    }
    return -1;
  }

  public void remove(int position) {
    int numRecords = getRecordCount();
    Preconditions.checkBounds(position, numRecords);

    // Move cells back to fill over the removed record
    if (position < numRecords - 1) {
      int srcPos = getCellOffset(position + 1);
      int length = getCellWidth() * (numRecords - (position + 1));
      int dstPos = getCellOffset(position);
      System.arraycopy(page.getBuffer(), srcPos, page.getBuffer(), dstPos, length);
    }

    // Decrement the number of records (also marks the page as dirty)
    setRecordCount(numRecords - 1);
  }

  public int getLeftPage(int position) {
    byteReader.setPosition(getCellOffset(position + 1) - 4);
    return byteReader.readInt();
  }

  public void setLeftPage(int position, int leftPage) {
    byteWriter.setPosition(getCellOffset(position + 1) - 4);
    byteWriter.writeInt(leftPage);
  }

  public void setRightPage(int rightPage) {
    byteWriter.setPosition(page.getBuffer().length - 4);
    byteWriter.writeInt(rightPage);
    page.setIsDirty(true);
  }

  public int getRightPage() {
    byteReader.setPosition(page.getBuffer().length - 4);
    return byteReader.readInt();
  }

  private int getCellOffset(int position) {
    return 5 + getCellWidth() * position;
  }

  private int getCellWidth() {
    return index.getSchema().getLength() + 4;
  }

  @Override
  public int findPositionGreaterOrEqualTo(Record record) {
    return 0;
  }
}
