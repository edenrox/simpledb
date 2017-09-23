package com.hopkins.simpledb.config;

import com.hopkins.simpledb.app.Config;

public final class ConfigImpl implements Config {

  @Override
  public int getPageSize() {
    // 4 KB pages
    return 4 * 1024;
  }

  @Override
  public String getCatalogTableName() {
    return "simpledb_catalog";
  }

  @Override
  public int getCacheSize() {
    // 4 MB cache (1024 pages)
    return 4 * 1024 * 1024;
  }
}
