package com.hopkins.simpledb;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.table.CatalogImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by edenrox on 5/29/2017.
 */
public class CatalogImplTest {

  private CatalogImpl catalog;
  private Schema schema;

  @Before
  public void setup() {
    catalog = new CatalogImpl();
    schema =
        new Schema(Arrays.asList(
            Column.newIntColumn("id"),
            Column.newStringColumn("name", 20),
            Column.newBoolColumn("is_hidden")
        ));
  }

  @Test
  public void createTable_addsTable() {
    String tableName = "makes";
    catalog.createTable(tableName, schema);

    assertThat(catalog.hasTable(tableName)).isTrue();
  }

  @Test
  public void dropTable_removesTable() {
    String tableName = "makes";
    catalog.createTable(tableName, schema);
    catalog.dropTable(tableName);

    assertThat(catalog.hasTable(tableName)).isFalse();
  }

}
