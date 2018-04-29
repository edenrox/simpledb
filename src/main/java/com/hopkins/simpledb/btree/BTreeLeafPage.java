package com.hopkins.simpledb.btree;

import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.catalog.IndexDescriptor;
import com.hopkins.simpledb.data.*;
import com.hopkins.simpledb.util.Preconditions;

/**
 * Represents a leaf node in an index b-tree.
 *
 * <p>See {@link BTreePage} for layout details.
 */
public final class BTreeLeafPage implements BTreePage {
  private final IndexDescriptor index;
  private final Page page;
  private final ByteReader byteReader;
  private final ByteWriter byteWriter;

  BTreeLeafPage(IndexDescriptor index, Page page) {
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
    return true;
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
    return (page.getBuffer().length - 5) / getCellWidth();
  }

  private int getCellWidth() {
    return index.getSchema().getLength();
  }

  @Override
  public int getRecordCount() {
    byteReader.setPosition(1);
    return byteReader.readInt();
  }

  private void setRecordCount(int numRecords) {
    byteWriter.setPosition(1);
    byteWriter.writeInt(numRecords);
    page.setIsDirty(true);
  }

  public boolean hasCapacity() {
    return getRecordCount() < getRecordCapacity();
  }

  @Override
  public void unpin() {
    page.unpin();
  }

  public void insert(Record record) {
    Preconditions.checkState(hasCapacity());

    int position = findPositionGreaterOrEqualTo(record);
    insertAt(position, record);
  }

  private void insertAt(int position, Record record) {
    int numRecords = getRecordCount();
    Preconditions.checkBounds(position, numRecords + 1);

    // Move cells forward to make room for record at position
    if (position < numRecords) {
      int srcPos = getCellOffset(position);
      int dstPos = getCellOffset(position + 1);
      int length = getCellWidth() * (numRecords - position);

      System.arraycopy(page.getBuffer(), srcPos, page.getBuffer(), dstPos, length);
    }

    // Write the record at position
    byteWriter.setPosition(getCellOffset(position));
    RecordIo.writeRecord(byteWriter, record);

    // Increment the number of records (also marks the page as dirty)
    setRecordCount(numRecords + 1);
  }

  public void remove(Record record) {
    int position = findPositionGreaterOrEqualTo(record);
    Preconditions.checkArgument(RecordCompare.compare(record, getRecord(position)) == 0);
    removeAt(position);
  }

  private void removeAt(int position) {
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

  public int findPositionGreaterOrEqualTo(Record record) {
    Preconditions.checkNotNull(record);
    int numRecords = getRecordCount();
    for (int i = 0; i < numRecords; i++) {
      Record item = getRecord(i);
      if (RecordCompare.compare(record, item) <= 0) {
        return i;
      }
    }
    return numRecords;
  }

  private int getCellOffset(int position) {
    return 5 + getCellWidth() * position;
  }

  public Record getRecord(int position) {
    byteReader.setPosition(getCellOffset(position));
    return RecordIo.readRecord(byteReader, index.getSchema());
  }
}
