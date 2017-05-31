package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.ColumnDescriptor;
import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.TupleDescriptor;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edenrox on 5/29/2017.
 */
public class Projection implements DbIterator {
  private final String[] columns;
  private final DbIterator source;

  private TupleDescriptor tupleDescriptor;

  public Projection(DbIterator source, String... columns) {
    this.source = source;
    this.columns = columns;
  }

  public void open() {
    source.open();

    TupleDescriptor sourceTupleDescriptor = source.getTupleDescriptor();
    List<ColumnDescriptor> columnDescriptorList = new ArrayList<>(columns.length);
    for (String columnName : columns) {
      int index = sourceTupleDescriptor.indexOf(columnName);
      if (index >= 0) {
        columnDescriptorList.add(sourceTupleDescriptor.getColumnDescriptor(index));
      }
    }
    tupleDescriptor = new TupleDescriptor(columnDescriptorList);
  }

  public boolean hasNext() {
    return source.hasNext();
  }

  public Tuple next() {
    Tuple sourceTuple = source.next();
    byte[] srcData = sourceTuple.getData();
    byte[] destData = new byte[tupleDescriptor.getLength()];
    TupleDescriptor sourceTupleDescriptor = source.getTupleDescriptor();

    for (int i = 0; i < tupleDescriptor.getSize(); i++) {
      String columnName = tupleDescriptor.getFieldName(i);
      int srcIndex = sourceTupleDescriptor.indexOf(columnName);
      int srcOffset = sourceTupleDescriptor.getFieldOffset(srcIndex);
      int srcLength = sourceTupleDescriptor.getFieldLength(srcIndex);

      int destOffset = tupleDescriptor.getFieldOffset(i);

      System.arraycopy(srcData, srcOffset, destData, destOffset, srcLength);
    }

    return new Tuple(sourceTuple.getId(), tupleDescriptor, ByteBuffer.wrap(destData));
  }

  public TupleDescriptor getTupleDescriptor() {
    return tupleDescriptor;
  }

  public void reset() {
    source.reset();
  }

  public void close() {
    source.close();
  }
}
