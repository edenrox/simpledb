package com.hopkins.simpledb.app;

public interface Config {

  /** Returns the size of each database page measured in bytes. */
  int getPageSize();

  /** Returns the maximum size of the page cache measured in bytes. */
  int getCacheSize();

  /** Returns the name of the built-in catalog table. */
  String getCatalogTableName();
}
