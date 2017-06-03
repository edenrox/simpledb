package com.hopkins.simpledb.data;

import com.hopkins.simpledb.util.Preconditions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class Record {
  private final Schema schema;
  private final List<Object> values;

  public Record(Schema schema) {
    this.schema = schema;
    this.values = Collections.unmodifiableList(Arrays.asList(new Object[schema.getColumnCount()]));
  }

  public Schema getSchema() {
    return schema;
  }

  public Object get(int index) {
    return values.get(index);
  }

  public Object get(String columnName) {
    int index = schema.indexOf(columnName);
    Preconditions.checkArgument(index >= 0);
    return values.get(index);
  }

  public void set(int index, Object value) {
    values.set(index, value);
  }

  public void set(String columnName, Object value) {
    int index = schema.indexOf(columnName);
    Preconditions.checkArgument(index >= 0);
    values.set(index, value);
  }

  @Override
  public String toString() {
    return "Record [" + values.toString() + "]";
  }
}
