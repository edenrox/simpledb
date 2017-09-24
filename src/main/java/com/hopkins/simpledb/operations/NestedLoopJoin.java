package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class NestedLoopJoin implements DbIterator {

  private final DbIterator outerSource;
  private final DbIterator innerSource;

  private Schema schema;
  private Record outerRecord;

  public NestedLoopJoin(DbIterator outerSource, DbIterator innerSource) {
    this.outerSource = outerSource;
    this.innerSource = innerSource;
  }

  @Override
  public void open() {
    outerSource.open();
    innerSource.open();

    Schema outerTupleDesc = outerSource.getSchema();
    Schema innerTupleDesc = innerSource.getSchema();
    List<Column> columnList =
        new ArrayList<>(outerTupleDesc.getColumnCount() + innerTupleDesc.getColumnCount());
    columnList.addAll(outerTupleDesc.getColumnDescriptors());
    columnList.addAll(innerTupleDesc.getColumnDescriptors());

    schema = new Schema(columnList);
  }

  @Override
  public boolean hasNext() {
    if (!outerSource.hasNext() && outerRecord == null) {
      // outerSource is empty
      return false;
    }
    if (outerRecord == null) {
      outerRecord = outerSource.next();
    }

    if (!innerSource.hasNext()) {
      if (!outerSource.hasNext()) {
        // we are at the end of both outerSource and innerSource
        return false;
      } else {
        outerRecord = outerSource.next();
        innerSource.reset();
        if (!innerSource.hasNext()) {
          // innerSource is empty
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public Record next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Record innerRecord = innerSource.next();
    int outerColumnCount = outerRecord.getSchema().getColumnCount();

    Record record = new Record(schema);
    for (int i = 0; i < schema.getColumnCount(); i++) {
      Object value = i < outerColumnCount
          ? outerRecord.get(i)
          : innerRecord.get(i - outerColumnCount);
      record.set(i, value);
    }
    return record;
  }

  @Override
  public Schema getSchema() {
    return schema;
  }

  @Override
  public void reset() {
    close();
    open();
  }

  @Override
  public void close() {
    outerSource.close();
    innerSource.close();
    outerRecord = null;
  }
}
