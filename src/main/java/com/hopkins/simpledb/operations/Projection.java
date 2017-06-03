package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.Tuple;
import com.hopkins.simpledb.data.Schema;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edenrox on 5/29/2017.
 */
public class Projection implements DbIterator {
  private final String[] columns;
  private final DbIterator source;

  private Schema schema;

  public Projection(DbIterator source, String... columns) {
    this.source = source;
    this.columns = columns;
  }

  public void open() {
    source.open();

    Schema sourceSchema = source.getSchema();
    List<Column> columnList = new ArrayList<>(columns.length);
    for (String columnName : columns) {
      int index = sourceSchema.indexOf(columnName);
      if (index >= 0) {
        columnList.add(sourceSchema.getColumn(index));
      }
    }
    schema = new Schema(columnList);
  }

  public boolean hasNext() {
    return source.hasNext();
  }

  public Tuple next() {
    Tuple sourceTuple = source.next();
    byte[] srcData = sourceTuple.getData();
    byte[] destData = new byte[schema.getLength()];
    Schema sourceSchema = source.getSchema();

    for (int i = 0; i < schema.getColumnCount(); i++) {
      String columnName = schema.getColumnName(i);
      int srcIndex = sourceSchema.indexOf(columnName);
      int srcOffset = sourceSchema.getFieldOffset(srcIndex);
      int srcLength = sourceSchema.getColumnLength(srcIndex);

      int destOffset = schema.getFieldOffset(i);

      System.arraycopy(srcData, srcOffset, destData, destOffset, srcLength);
    }

    return new Tuple(sourceTuple.getId(), schema, ByteBuffer.wrap(destData));
  }

  public Schema getSchema() {
    return schema;
  }

  public void reset() {
    source.reset();
  }

  public void close() {
    source.close();
  }
}
