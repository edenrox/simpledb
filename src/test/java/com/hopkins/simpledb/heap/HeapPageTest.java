package com.hopkins.simpledb.heap;

import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

public class HeapPageTest {
  private static final int PAGE_SIZE = 1000;
  private static final int PAGE_NUMBER = 1;

  private Schema schema;
  private TableDescriptor table;
  private Page page;
  private HeapPage heapPage;

  @Before
  public void setup() {
    schema = new Schema(Arrays.asList(
        Column.ROW_ID,
        Column.newStringColumn("name", 10),
        Column.newIntColumn("age")
    ));
    table = new TableDescriptor("test", PAGE_NUMBER, schema);

    page = new Page(new byte[PAGE_SIZE]);
    page.setPageNumber(PAGE_NUMBER);
    HeapPage.initializePage(page, true);

    heapPage = new HeapPage(page, table);
  }

  @Test
  public void getAndIncRowId_marksPageDirty() {
    heapPage.getAndIncRowId();
    assertThat(page.isDirty()).isTrue();
  }

  @Test
  public void getAndIncRowId_afterInit_returnsOne() {
    assertThat(heapPage.getAndIncRowId()).isEqualTo(1);
  }

  @Test
  public void getAndIncRowId_incrementsRowId() {
    assertThat(heapPage.getAndIncRowId()).isEqualTo(1);
    assertThat(heapPage.getAndIncRowId()).isEqualTo(2);
    assertThat(heapPage.getAndIncRowId()).isEqualTo(3);
  }

  @Test
  public void getRecord_afterInsertRecord_returnsRecord() {
    Record record = getRecord("bob", 2);
    int rowId = record.getRowId();

    heapPage.insertRecord(record);

    Record recordFromPage = heapPage.getRecord(0);
    assertThat(recordFromPage.get(Column.ROW_ID_NAME)).isEqualTo(rowId);
    assertThat(recordFromPage.get("name")).isEqualTo("bob");
    assertThat(recordFromPage.get("age")).isEqualTo(2);
  }

  @Test
  public void containsRowId_returnsFalse() {
    assertThat(heapPage.containsRowId(50)).isFalse();
  }

  @Test
  public void containsRowId_afterInsertRecord_returnsTrue() {
    Record record = getRecord("bob", 2);
    int rowId = record.getRowId();

    heapPage.insertRecord(record);

    assertThat(heapPage.containsRowId(rowId)).isTrue();
  }

  @Test
  public void insertRecord_incrementsNumRecords() {
    assertThat(heapPage.getNumRecords()).isEqualTo(0);

    heapPage.insertRecord(getRecord("bob", 3));
    assertThat(heapPage.getNumRecords()).isEqualTo(1);

    heapPage.insertRecord(getRecord("lucy", 4));
    assertThat(heapPage.getNumRecords()).isEqualTo(2);
  }

  private Record getRecord(String name, int age) {
    int rowId = heapPage.getAndIncRowId();
    page.setIsDirty(false);

    Record record = new Record(schema);
    record.set(Column.ROW_ID_NAME, rowId);
    record.set("name", "bob");
    record.set("age", 2);

    return record;
  }
}
