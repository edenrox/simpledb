package com.hopkins.simpledb.root;

import com.hopkins.simpledb.app.*;
import com.hopkins.simpledb.cache.CacheManagerImpl;
import com.hopkins.simpledb.catalog.CatalogManagerImpl;
import com.hopkins.simpledb.config.ConfigImpl;
import com.hopkins.simpledb.data.RecordIo;
import com.hopkins.simpledb.disk.DiskFileManagerImpl;
import com.hopkins.simpledb.freepages.FreePageManagerImpl;
import com.hopkins.simpledb.heap.HeapInitializer;
import com.hopkins.simpledb.heap.HeapManagerImpl;

import java.io.IOException;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class App {
  private static final String DEFAULT_FILE_NAME = "main.db";

  public static void main(String[] args) throws Exception {
    App app = new App();
    app.init(DEFAULT_FILE_NAME);
  }

  private final ServiceLocator locator;

  public App() {
    this.locator = ServiceLocator.getInstance();
  }

  public ServiceLocator getServiceLocator() {
    return locator;
  }

  public void init(String fileName) throws IOException {
    locator
        .bind(Config.class, new ConfigImpl())
        .bind(
            DiskFileManager.class,
            new DiskFileManagerImpl(locator.get(Config.class)))
        .bind(
            CacheManager.class,
            new CacheManagerImpl(locator.get(Config.class), locator.get(DiskFileManager.class)))
        .bind(
            HeapManager.class,
            new HeapManagerImpl(locator.get(CacheManager.class)))
        .bind(
            FreePageManager.class,
            new FreePageManagerImpl(locator.get(CacheManager.class)))
        .bind(
            CatalogManager.class,
            new CatalogManagerImpl(
                locator.get(Config.class),
                locator.get(FreePageManager.class),
                locator.get(HeapManager.class),
                locator.get(CacheManager.class)))
        .bind(RecordIo.class, new RecordIo())
    ;

    DiskFileManager diskFileManager = locator.get(DiskFileManager.class);
    diskFileManager.init(fileName);

    HeapInitializer heapInitializer =
        new HeapInitializer(locator.get(DiskFileManager.class), locator.get(CacheManager.class));
    heapInitializer.init();
  }
}
