package com.hopkins.simpledb.table;

import com.hopkins.simpledb.app.ServiceLocator;
import com.hopkins.simpledb.bufferpool.DiskManager;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.heapfile.HeapFile;
import com.hopkins.simpledb.util.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edenrox on 5/29/2017.
 */
public class CatalogImpl implements Catalog {
  private final ServiceLocator serviceLocator;
  private final List<Table> tableList;

  public CatalogImpl(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
    this.tableList = new ArrayList<>();
  }

  @Override
  public Table getTable(int tableId) {
    return tableList.get(tableId);
  }

  @Override
  public Table getTable(String tableName) {
    int index = indexOf(tableName);
    if (index == -1) {
      return null;
    }
    return getTable(index);
  }

  @Override
  public Table createTable(String name, Schema schema) {
    Preconditions.checkState(!hasTable(name));

    Table table = new Table(tableList.size(), name, schema);
    tableList.add(table);
    HeapFile.createNew(table, serviceLocator);
    return table;
  }

  @Override
  public boolean hasTable(String tableName) {
     return indexOf(tableName) != -1;
  }

  @Override
  public void dropTable(String tableName) {
    int index = indexOf(tableName);
    if (index >= 0) {
      tableList.set(index, null);
      DiskManager.deleteFile(index);
    }
  }

  private int indexOf(String tableName) {
    for (int i = 0; i < tableList.size(); i++) {
      Table table = tableList.get(i);
      if (table != null && table.getName().equals(tableName)) {
        return i;
      }
    }
    return -1;
  }
}
