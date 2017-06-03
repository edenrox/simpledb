package com.hopkins.simpledb.table;

import com.hopkins.simpledb.bufferpool.BufferPage;
import com.hopkins.simpledb.bufferpool.BufferPool;
import com.hopkins.simpledb.bufferpool.PageId;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.RecordId;
import com.hopkins.simpledb.data.RecordIo;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.util.Preconditions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class TableStorage {
  private final BufferPool bufferPool;
  private final RecordIo recordIo;
  private final Table table;

  public Record getRecord(RecordId recordId) {
    Preconditions.checkArgument(recordId.getPageNumber() > 0);

    PageId pageId = new PageId(table.getId(), recordId.getPageNumber());
    BufferPage page = bufferPool.getPage(pageId);

    Schema schema = table.getSchema();
    int offset = schema.getLength() * recordId.getRecordNumber();
    DataInputStream inputStream =
        new DataInputStream(new ByteArrayInputStream(page.getBuffer(), offset, schema.getLength()));
    Record record = recordIo.readRecord(inputStream, table.getSchema());
    return record;
  }

  public void updateRecord(RecordId recordId, Record record) {
    Preconditions.checkArgument(recordId.getPageNumber() > 0);

    PageId pageId = new PageId(table.getId(), recordId.getPageNumber());
    BufferPage page = bufferPool.getPage(pageId);

    // TODO(ianhopkins): we should write to the page
    DataOutputStream outputStream = new DataOutputStream(new ByteArrayOutputStream());
    recordIo.writeRecord(outputStream, record);
  }
}
