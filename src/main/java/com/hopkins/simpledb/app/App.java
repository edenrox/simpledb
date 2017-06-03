package com.hopkins.simpledb.app;

import com.hopkins.simpledb.bufferpool.BufferPool;
import com.hopkins.simpledb.table.Catalog;
import com.hopkins.simpledb.table.CatalogImpl;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class App {

  public static void main(String[] args) {
    ServiceLocator locator = ServiceLocator.getInstance();
    locator
        .bind(BufferPool.class, BufferPool.newDefaultPool(locator))
        .bind(Catalog.class, new CatalogImpl())
        ;


  }
}
