package com.hopkins.simpledb.app;

import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Schema;

/**
 * Created by ian_000 on 8/27/2017.
 */
public interface CatalogManager {

  boolean hasTable(String name);

  TableDescriptor getTable(String name);

  void createTable(String name, Schema schema);

  void dropTable(String name);


//  boolean hasIndex(String name);
//
//  int getIndexRootPage(String name);
//
//  void createIndex(String name, String tableName);
//
//  void dropIndex(String name);
//
//
//  void createView(String name, String sql);
//
//  void dropView(String name);
}
