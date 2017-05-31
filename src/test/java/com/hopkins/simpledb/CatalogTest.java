package com.hopkins.simpledb;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by edenrox on 5/29/2017.
 */
public class CatalogTest {

  private Catalog catalog;
  private TupleDescriptor tupleDescriptor;

  @Before
  public void setup() {
    catalog = new Catalog();
    tupleDescriptor =
        new TupleDescriptor(Arrays.asList(
            ColumnDescriptor.newIntColumn("id"),
            ColumnDescriptor.newStringColumn("name", 20),
            ColumnDescriptor.newBoolColumn("is_hidden")
        ));
  }

  @Test
  public void createTable_addsTable() {
    String tableName = "makes";
    catalog.createTable(tableName, tupleDescriptor);

    assertThat(catalog.hasTable(tableName)).isTrue();
  }

  @Test
  public void dropTable_removesTable() {
    String tableName = "makes";
    catalog.createTable(tableName, tupleDescriptor);
    catalog.dropTable(tableName);

    assertThat(catalog.hasTable(tableName)).isFalse();
  }

}
