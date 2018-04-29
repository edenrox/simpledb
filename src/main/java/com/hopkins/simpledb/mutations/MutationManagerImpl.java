package com.hopkins.simpledb.mutations;

import com.hopkins.simpledb.app.BTreeManager;
import com.hopkins.simpledb.app.CatalogManager;
import com.hopkins.simpledb.app.HeapManager;
import com.hopkins.simpledb.app.MutationManager;
import com.hopkins.simpledb.catalog.IndexDescriptor;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.expression.Expression;
import com.hopkins.simpledb.operations.DbIterator;
import com.hopkins.simpledb.operations.Selection;

import java.util.List;
import java.util.Map;

public class MutationManagerImpl implements MutationManager {

  private CatalogManager catalogManager;
  private HeapManager heapManager;
  private BTreeManager bTreeManager;

  public void init(CatalogManager catalogManager, HeapManager heapManager, BTreeManager bTreeManager) {
    this.catalogManager = catalogManager;
    this.heapManager = heapManager;
    this.bTreeManager = bTreeManager;
  }

  @Override
  public int insert(String tableName, Object... values) {
    // Find the table and indexes
    TableDescriptor table = catalogManager.getTable(tableName);

    // Build the table record to insert
    Record record = new Record(table.getSchema());
    record.setAll(values);

    return insert(table, record);
  }

  @Override
  public int insert(TableDescriptor table, Record tableRecord) {
    List<IndexDescriptor> indexList = catalogManager.findIndexes(table.getName());

    // insert into the table
    heapManager.insert(table, tableRecord);

    // insert into the indexes
    for (IndexDescriptor index : indexList) {
      bTreeManager.insert(index, tableRecord);
    }

    return tableRecord.getInt(Column.ROW_ID_NAME);
  }

  @Override
  public int update(String tableName, Expression expression, Map<String, Object> values) {
    // Find the table and indexes
    TableDescriptor table = catalogManager.getTable(tableName);
    List<IndexDescriptor> indexList = catalogManager.findIndexes(tableName);

    DbIterator scan = heapManager.getScan(table);
    DbIterator filter = new Selection(scan, expression);

    int numUpdated = 0;
    try {
      filter.open();
      while (filter.hasNext()) {
        Record oldRecord = filter.next();
        int rowId = oldRecord.getRowId();

        // update in the table
        Record newRecord = buildNewRecord(oldRecord, values);
        heapManager.update(table, rowId, newRecord);
        numUpdated++;

        // update in the indexes
        for (IndexDescriptor index : indexList) {
          if (isIndexAffectedByUpdate(index, values)) {
            bTreeManager.update(index, oldRecord, newRecord);
          }
        }
      }
    } finally {
      filter.close();
    }
    return numUpdated;
  }

  private Record buildNewRecord(Record oldRecord, Map<String, Object> values) {
    Schema schema = oldRecord.getSchema();
    Record newRecord = new Record(schema);
    for (int i = 0; i < schema.getColumnCount(); i++) {
      String columnName = schema.getColumnName(i);
      Object value = oldRecord.get(i);
      if (values.containsKey(columnName)) {
        value = values.get(columnName);
      }
      newRecord.set(i, value);
    }
    return newRecord;
  }

  @Override
  public int delete(String tableName, Expression expression) {
    // Find the table and indexes
    TableDescriptor table = catalogManager.getTable(tableName);
    List<IndexDescriptor> indexList = catalogManager.findIndexes(tableName);

    DbIterator scan = heapManager.getScan(table);
    DbIterator filter = new Selection(scan, expression);

    int numDeleted = 0;
    try {
      filter.open();
      while (filter.hasNext()) {
        Record record = filter.next();

        // delete from the table
        int rowId = record.getRowId();
        heapManager.delete(table, rowId);
        numDeleted++;

        // delete from the indexes
        for (IndexDescriptor index : indexList) {
          bTreeManager.delete(index, record);
        }
      }
    } finally {
      filter.close();
    }
    return numDeleted;
  }

  private boolean isIndexAffectedByUpdate(IndexDescriptor index, Map<String, Object> values) {
    Schema schema = index.getSchema();
    for (int i = 0; i < schema.getColumnCount(); i++) {
      String columnName = schema.getColumnName(i);
      if (values.containsKey(columnName)) {
        return true;
      }
    }
    return false;
  }
}
