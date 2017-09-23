package com.hopkins.simpledb.catalog;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Schema;

import java.util.Arrays;

public class CatalogTable {
  public static final int DATA_LENGTH = 300;

  public interface Columns {
    String ROW_ID = "_id";
    String TYPE = "type";
    String NAME = "name";
    String ROOT_PAGE = "root_page";
    String DATA = "data";
    String SQL = "sql";
  }

  private static final Schema schema =
      new Schema(Arrays.asList(
          Column.newIntColumn(Columns.ROW_ID),
          Column.newStringColumn(Columns.TYPE, 5),
          Column.newStringColumn(Columns.NAME, 60),
          Column.newIntColumn(Columns.ROOT_PAGE),
          Column.newBlobColumn(Columns.DATA, DATA_LENGTH),
          Column.newStringColumn(Columns.SQL, 300)
      ));

  public static Schema getSchema() {
    return schema;
  }

  public static int getRootPageNumber() {
    return 0;
  }
}
