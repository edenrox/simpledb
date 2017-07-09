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
    this.values = Arrays.asList(new Object[schema.getColumnCount()]);
    for (int i = 0; i < schema.getColumnCount(); i++) {
      this.values.set(i, schema.getColumn(i).getType().getDefaultValue());
    }
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
    Preconditions.checkNotNull(value);
    checkValueMatchesColumnType(schema.getColumn(index), value);
    values.set(index, value);
  }

  public void set(String columnName, Object value) {
    int index = schema.indexOf(columnName);
    Preconditions.checkArgument(index >= 0);
    set(index, value);
  }

  private void checkValueMatchesColumnType(Column column, Object value) {
    switch (column.getType()) {
      case INTEGER:
        Preconditions.checkArgument(value instanceof Integer);
        break;
      case DOUBLE:
        Preconditions.checkArgument(value instanceof Double);
        break;
      case BOOL:
        Preconditions.checkArgument(value instanceof Boolean);
        break;
      case STRING:
        Preconditions.checkArgument(value instanceof String);
        break;
      default:
        throw new IllegalArgumentException("Unexpected type for column: " + column);
    }
  }

  @Override
  public String toString() {
    return "Record [" + values.toString() + "]";
  }
}
