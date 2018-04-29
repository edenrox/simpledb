package com.hopkins.simpledb.btree;

import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.catalog.IndexDescriptor;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

public class BTreeLeafPageTest {
  private static final String TABLE_NAME = "table name";
  private static final String INDEX_NAME = "index name";
  private static final int ROOT_PAGE = 2;

  private Record record1;
  private Record record2;
  private Record record3;
  private Record record4;

  private IndexDescriptor indexDescriptor;
  private Schema indexSchema;
  private Page page;
  private BTreeLeafPage leafPage;

  @Before
  public void setup() {
    indexSchema = new Schema(Arrays.asList(
        Column.newStringColumn("name", 32),
        Column.ROW_ID));
    indexDescriptor = new IndexDescriptor(TABLE_NAME, INDEX_NAME, ROOT_PAGE, indexSchema);

    record1 = new Record(indexSchema);
    record1.setAll("Alice", 1);
    record2 = new Record(indexSchema);
    record2.setAll("Bob", 2);
    record3 = new Record(indexSchema);
    record3.setAll("Bob", 4);
    record4 = new Record(indexSchema);
    record4.setAll("Candy", 3);

    page = new Page(new byte[4096]);
    page.setPageNumber(ROOT_PAGE);
    leafPage = new BTreeLeafPage(indexDescriptor, page);
  }

  @Test
  public void isLeafNode_returnsTrue() {
    assertThat(leafPage.isLeafNode()).isTrue();
  }

  @Test
  public void isTreeRoot_returnsTrue() {
    assertThat(leafPage.isTreeRoot()).isTrue();
  }

  @Test
  public void getRecordCount_returnsZero() {
    assertThat(leafPage.getRecordCount()).isEqualTo(0);
  }

  @Test
  public void getRecordCount_afterInsert_isCorrect() {
    leafPage.insert(record1);
    leafPage.insert(record2);

    assertThat(leafPage.getRecordCount()).isEqualTo(2);
  }

  @Test
  public void remove_afterInsert_decrementsRecordCount() {
    leafPage.insert(record1);
    leafPage.remove(record1);

    assertThat(leafPage.getRecordCount()).isEqualTo(0);
  }

  @Test
  public void insert_multipleRecords_addedInKeyOrder() {
    leafPage.insert(record4);
    leafPage.insert(record2);
    leafPage.insert(record1);
    leafPage.insert(record3);

    assertThat(leafPage.getRecord(0)).isEqualTo(record1);
    assertThat(leafPage.getRecord(1)).isEqualTo(record2);
    assertThat(leafPage.getRecord(2)).isEqualTo(record3);
    assertThat(leafPage.getRecord(3)).isEqualTo(record4);
  }

  @Test
  public void delete_afterInsert_removesRecord() {
    leafPage.insert(record2);
    leafPage.insert(record1);
    leafPage.insert(record3);

    leafPage.remove(record2);

    assertThat(leafPage.getRecord(0)).isEqualTo(record1);
    assertThat(leafPage.getRecord(1)).isEqualTo(record3);
  }
}
