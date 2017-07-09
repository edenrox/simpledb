package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.app.ServiceLocator;
import com.hopkins.simpledb.bufferpool.BufferPage;
import com.hopkins.simpledb.bufferpool.BufferPool;
import com.hopkins.simpledb.bufferpool.PageId;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.RecordId;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.heapfile.HeapFile;
import com.hopkins.simpledb.table.Catalog;
import com.hopkins.simpledb.table.Table;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by edenrox on 5/29/2017.
 */
public class HeapScan implements DbIterator {
  private final Table table;
  private final HeapFile heapFile;

  private int index;

  public HeapScan(Table table, ServiceLocator serviceLocator) {
    this.table = table;
    this.heapFile = new HeapFile(table, serviceLocator);
  }

  @Override
  public void open() {
    index = 0;
  }

  @Override
  public boolean hasNext() {
    return index < heapFile.getRecordCount();
  }

  @Override
  public Record next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    // Read the record from HeapFile
    Record record = heapFile.getRecord(index);
    index++;
    return record;
  }

  @Override
  public Schema getSchema() {
    return table.getSchema();
  }

  @Override
  public void reset() {
    index = 0;
  }

  @Override
  public void close() { }
}
