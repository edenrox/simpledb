package com.hopkins.simpledb.app;

import com.hopkins.simpledb.catalog.IndexDescriptor;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.operations.DbIterator;
import com.sun.istack.internal.Nullable;

public interface BTreeManager {

  void populateIndex(IndexDescriptor index, TableDescriptor table);

  void insert(IndexDescriptor index, Record tableRecord);

  void update(IndexDescriptor index, Record oldTableRecord, Record newTableRecord);

  void delete(IndexDescriptor index, Record tableRecord);

  @Nullable
  int findExact(IndexDescriptor index, Record indexRecord);

  @Nullable
  Record findFirst(IndexDescriptor index, Record indexRecord);

  @Nullable
  Record findNext(IndexDescriptor index, Record lastRecord);

  DbIterator scan(IndexDescriptor index, Record start, Record end);
}
