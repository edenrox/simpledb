package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by ian_000 on 7/7/2017.
 */
public class LiteralScanTest {
  private Record record;
  private LiteralScan provider;

  @Before
  public void setup() {
    Schema schema = new Schema(Arrays.asList(
        Column.newIntColumn("id"),
        Column.newStringColumn("name", 20),
        Column.newBoolColumn("is_active")
    ));
    record = new Record(schema);
    record.set("id", 1);
    record.set("name", "Pontiac");
    record.set("is_active", false);

    provider = new LiteralScan(record);
    provider.open();
  }

  @After
  public void shutdown() {
    provider.close();
  }



  @Test(expected = NullPointerException.class)
  public void initWithNull_throws() {
    new LiteralScan(null);
  }

  @Test
  public void hasNext_returnsTrue() {
    assertThat(provider.hasNext()).isTrue();
  }

  @Test
  public void hasNext_afterNext_returnsFalse() {
    provider.next();
    assertThat(provider.hasNext()).isFalse();
  }

  @Test
  public void getSchema_returnsRecordSchema() {
    assertThat(provider.getSchema()).isEqualTo(record.getSchema());
  }

  @Test
  public void next_returnsRecord() {
    assertThat(provider.next()).isEqualTo(record);
  }
}
