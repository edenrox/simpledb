package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.data.Schema;

import java.nio.ByteBuffer;
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
  private Tuple outerTuple;

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
        new ArrayList<>(outerTupleDesc.getLength() + innerTupleDesc.getLength());
    columnList.addAll(outerTupleDesc.getColumnDescriptors());
    columnList.addAll(innerTupleDesc.getColumnDescriptors());

    schema = new Schema(columnList);
  }

  @Override
  public boolean hasNext() {
    if (!outerSource.hasNext() && outerTuple == null) {
      // outerSource is empty
      return false;
    }
    if (outerTuple == null) {
      outerTuple = outerSource.next();
    }

    if (!innerSource.hasNext()) {
      if (!outerSource.hasNext()) {
        // we are at the end of both outerSource and innerSource
        return false;
      } else {
        outerTuple = outerSource.next();
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
  public Tuple next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Tuple innerTuple = innerSource.next();

    byte[] dest = new byte[schema.getLength()];
    byte[] outerData = outerTuple.getData();
    byte[] innerData = innerTuple.getData();
    System.arraycopy(outerData, 0, dest, 0, outerData.length);
    System.arraycopy(innerData, 0, dest, outerData.length, innerData.length);

    return new Tuple(-1 /* id */, schema, ByteBuffer.wrap(dest));
  }

  @Override
  public Schema getSchema() {
    return schema;
  }

  @Override
  public void reset() {
    outerSource.reset();
    innerSource.reset();
  }

  @Override
  public void close() {
    outerSource.close();
    innerSource.close();
  }
}
