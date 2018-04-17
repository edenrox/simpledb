package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.ColumnCompare;
import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.util.Preconditions;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Sort implements DbIterator {
  private final DbIterator source;
  private final List<SortColumn> sortColumnList;

  private List<Record> recordList;
  private int index;

  public Sort(DbIterator source, SortColumn... columns) {
    Preconditions.checkArgument(columns.length > 0);

    this.source = source;
    this.sortColumnList = Arrays.asList(columns);
  }

  @Override
  public void open() {
    source.open();
    recordList = DbIteratorUtil.readAll(source);
    // TODO(ianhopkins): this is an in-memory sort, it should be an external sort
    recordList.sort(this::compareRecords);
    index = 0;
  }

  private int compareRecords(Record a, Record b) {
    for (SortColumn sortColumn : sortColumnList) {
      int compare = compareColumns(a, b, sortColumn.getColumnName());
      if (compare != 0) {
        if (sortColumn.getDirection() == SortColumn.SortDirection.DESCENDING) {
          compare = -compare;
        }
        return compare;
      }
    }
    return 0;
  }

  private int compareColumns(Record a, Record b, String columnName) {
    Schema schema = a.getSchema();
    int columnIndex = schema.indexOf(columnName);
    ColumnType columnType = schema.getColumnType(columnIndex);
    Object valA = a.get(columnIndex);
    Object valB = b.get(columnIndex);

    return ColumnCompare.compare(columnType, valA, valB);
  }

  @Override
  public boolean hasNext() {
    return index < recordList.size();
  }

  @Override
  public Record next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    Record record = recordList.get(index);
    index++;
    return record;
  }

  @Override
  public Schema getSchema() {
    return source.getSchema();
  }

  @Override
  public void close() {
    source.close();
  }
}
