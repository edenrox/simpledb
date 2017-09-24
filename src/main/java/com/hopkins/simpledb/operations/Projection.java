package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

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

  public Record next() {
    Record sourceRecord = source.next();
    Schema sourceSchema = sourceRecord.getSchema();

    Record destRecord = new Record(schema);
    for (int i = 0; i < schema.getColumnCount(); i++) {
      String columnName = schema.getColumnName(i);
      int srcIndex = sourceSchema.indexOf(columnName);
      destRecord.set(i, sourceRecord.get(srcIndex));
    }
    return destRecord;
  }

  public Schema getSchema() {
    return schema;
  }

  public void reset() {
    close();
    open();
  }

  public void close() {
    source.close();
  }
}
