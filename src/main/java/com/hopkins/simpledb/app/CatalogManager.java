package com.hopkins.simpledb.app;

import com.hopkins.simpledb.catalog.IndexDescriptor;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Schema;

import java.util.List;

/**
 * Created by ian_000 on 8/27/2017.
 */
public interface CatalogManager {

  boolean hasTable(String name);

  TableDescriptor getTable(String name);

  void createTable(String name, Schema schema);

  void dropTable(String name);

  boolean hasIndex(String name);

  IndexDescriptor getIndex(String name);

  void createIndex(String name, String tableName, Schema indexColumns);

  void dropIndex(String name);

  List<IndexDescriptor> findIndexes(String tableName);


//  void createView(String name, String sql);
//
//  void dropView(String name);
}
