package com.hopkins.simpledb.app;

import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.operations.DbIterator;

public interface HeapManager {

  DbIterator getScan(TableDescriptor table);

  void insert(TableDescriptor table, Record record);

  void delete(TableDescriptor table, int rowId);

  void update(TableDescriptor table, int rowId, Record record);
}
