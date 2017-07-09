package com.hopkins.simpledb.heapfile;

import com.hopkins.simpledb.app.Config;
import com.hopkins.simpledb.app.ServiceLocator;
import com.hopkins.simpledb.bufferpool.BufferPage;
import com.hopkins.simpledb.bufferpool.BufferPool;
import com.hopkins.simpledb.bufferpool.DiskManager;
import com.hopkins.simpledb.bufferpool.PageId;
import com.hopkins.simpledb.data.*;
import com.hopkins.simpledb.table.Table;

import java.io.File;
import java.io.IOException;

/**
 * Created by ian_000 on 7/3/2017.
 */
public class HeapFile {

  public static PageId getHeaderPageId(Table table) {
    return new PageId(table.getId(), 0);
  }

  public static HeapFile createNew(Table table, ServiceLocator serviceLocator) {
    BufferPool bufferPool = serviceLocator.get(BufferPool.class);
    File file = DiskManager.getTableFile(table.getId());
    try {
      // Create a file with 2 pages (header page + empty heap page)
      file.createNewFile();
      DiskManager.setFileLength(table.getId(), Config.PAGE_SIZE * 2);

      // Get the header page
      PageId headerPageId = getHeaderPageId(table);
      BufferPage bufferPage = bufferPool.getPage(headerPageId);

      // Write the metadata to the header page
      ByteWriter byteWriter = new ByteWriter(bufferPage.getBuffer());

      // Num rows
      byteWriter.writeInt(0);

      // Schema
      SchemaIo.writeSchema(byteWriter, table.getSchema());

      // Table name
      byteWriter.writeVarLengthString(table.getName());

      // Flush header changes to disk
      bufferPool.flushPage(headerPageId);

      return new HeapFile(table, serviceLocator);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private final Table table;
  private final BufferPool bufferPool;
  private final RecordIo recordIo;
  private final int recordsPerPage;

  public HeapFile(Table table, ServiceLocator serviceLocator) {
    this.table = table;
    this.bufferPool = serviceLocator.get(BufferPool.class);
    this.recordIo = serviceLocator.get(RecordIo.class);
    this.recordsPerPage = Config.PAGE_SIZE / table.getSchema().getLength();
  }

  public int getRecordCount() {
    BufferPage page = bufferPool.getPage(getHeaderPageId(table));
    ByteReader byteReader = new ByteReader(page.getBuffer());
    return byteReader.readInt();
  }

  public void setRecordCount(int count) {
    BufferPage page = bufferPool.getPage(getHeaderPageId(table));
    ByteWriter byteWriter = new ByteWriter(page.getBuffer());
    byteWriter.writeInt(count);
    page.setDirty(true);
  }

  public int getPageCount() {
    return (int) (DiskManager.getFileSize(table.getId()) / Config.PAGE_SIZE);
  }

  private int getPageNumber(int recordIndex) {
    return 1 + recordIndex / recordsPerPage;
  }

  private PageId getPageId(int recordIndex) {
    return new PageId(table.getId(), getPageNumber(recordIndex));
  }

  private int getPositionOnPage(int recordIndex) {
    return table.getSchema().getLength() * (recordIndex % recordsPerPage);
  }

  public Record getRecord(int index) {
    PageId pageId = getPageId(index);
    BufferPage page = bufferPool.getPage(pageId);

    ByteReader byteReader = new ByteReader(page.getBuffer());
    byteReader.setPosition(getPositionOnPage(index));
    return recordIo.readRecord(byteReader, table.getSchema());
  }

  public void updateRecord(int index, Record record) {
    PageId pageId = getPageId(index);
    BufferPage page = bufferPool.getPage(pageId);

    ByteWriter writer = new ByteWriter(page.getBuffer());
    writer.setPosition(getPositionOnPage(index));
    recordIo.writeRecord(writer, record);

    recordIo.writeRecord(writer, record);
    page.setDirty(true);
  }

  public void addRecord(Record record) {
    // Increment record count
    int recordCount = getRecordCount();
    recordCount++;
    setRecordCount(recordCount);

    // Add a new, empty page if required


    // Put the record on the last page
    int index = recordCount - 1;
    PageId pageId = getPageId(index);
    BufferPage lastPage = bufferPool.getPage(pageId);
    ByteWriter byteWriter = new ByteWriter(lastPage.getBuffer());

    byteWriter.setPosition(getPositionOnPage(index));
    recordIo.writeRecord(byteWriter, record);
    lastPage.setDirty(true);
  }

  public void removeRecord(int index) {
    // Decrement record count
    int recordCount = getRecordCount();
    recordCount--;
    setRecordCount(recordCount);


  }
}
