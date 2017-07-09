package com.hopkins.simpledb.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by ian_000 on 7/8/2017.
 */
public class RecordTest {

  private Schema schema;
  private Record record;

  @Before
  public void setup() {
    schema = new Schema(Arrays.asList(
        Column.newIntColumn("id"),
        Column.newStringColumn("name", 20)));
    record = new Record(schema);
  }

  @Test(expected = NullPointerException.class)
  public void set_withNull_throws() {
    record.set(0, null /* value */);
  }

  @Test(expected = IllegalArgumentException.class)
  public void set_withWrongType_throws() {
    record.set(0, "test");
  }

  @Test
  public void get_afterSet_returnsValue() {
    record.set(0, 12);
    record.set(1, "test");
    assertThat(record.get(0)).isEqualTo(12);
    assertThat(record.get(1)).isEqualTo("test");
  }

  @Test
  public void get_returnsDefaultValue() {
    assertThat(record.get(0)).isEqualTo(0);
    assertThat(record.get(1)).isEqualTo("");
  }
}
