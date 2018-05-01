package com.hopkins.simpledb.catalog;

import com.hopkins.simpledb.data.*;

import java.util.Arrays;

/**
 * A table which stores the database schema.
 *
 * <p>The catalog table is a system table and cannot be modified directly; only via DDL statements.  Note: the catalog
 * table does NOT include an entry for the catalog table itself.
 *
 * <p>The catalog table is NOT indexed.  Since there will only be hundreds of tables in a given database, an index
 * wouldn't provide much benefit anyway.
 *
 * <p>The user can query the catalog table just like any other table.
 */
public final class CatalogTable {
  public static final int DATA_LENGTH = 300;

  public interface Columns {
    String ROW_ID = Column.ROW_ID_NAME;
    String TYPE = "type";
    String NAME = "name";
    String TABLE_NAME = "table_name";
    String ROOT_PAGE = "root_page";
    String DATA = "data";
    String SQL = "sql";
  }

  private static final Schema schema =
      new Schema(Arrays.asList(
          Column.ROW_ID,
          Column.newStringColumn(Columns.TYPE, 5),
          Column.newStringColumn(Columns.NAME, 60),
          Column.newStringColumn(Columns.TABLE_NAME, 60),
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

  public static Record createRecord(CatalogType type, String name, String tableName, int rootPageNumber, Schema schema) {
    Record record = new Record(getSchema());
    record.set(CatalogTable.Columns.TYPE, type.name());
    record.set(CatalogTable.Columns.NAME, name);
    record.set(CatalogTable.Columns.TABLE_NAME, tableName);
    record.set(CatalogTable.Columns.ROOT_PAGE, rootPageNumber);
    byte[] schemaBytes = new byte[CatalogTable.DATA_LENGTH];
    ByteWriter byteWriter = new ByteWriter(schemaBytes);
    SchemaIo.writeSchema(byteWriter, schema);
    record.set(CatalogTable.Columns.DATA, schemaBytes);

    return record;
  }
}
