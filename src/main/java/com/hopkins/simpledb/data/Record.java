package com.hopkins.simpledb.data;

import com.hopkins.simpledb.util.Preconditions;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class Record {

  public static Record copyOf(Record record) {
    Schema schema = record.getSchema();
    Record copy = new Record(schema);
    for (int i = 0; i < schema.getColumnCount(); i++) {
      copy.set(i, record.get(i));
    }
    return copy;
  }

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

  public int getRowId() {
    return getInt(Column.ROW_ID_NAME);
  }

  public Object get(int index) {
    Preconditions.checkArgument(index >= 0);
    return values.get(index);
  }

  public Object get(String columnName) {
    int index = schema.indexOf(columnName);
    Preconditions.checkArgument(index >= 0, "Unknown column: " + columnName);
    return get(index);
  }

  public int getInt(String columnName) {
    return (Integer) get(columnName);
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

  public void setAll(Object... values) {
    Preconditions.checkArgument(values.length == schema.getColumnCount());
    for (int i = 0; i < values.length; i++) {
      set(i, values[i]);
    }
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
      case BLOB:
        Preconditions.checkArgument(value instanceof byte[]);
        break;
      default:
        throw new IllegalArgumentException("Unexpected type for column: " + column);
    }
  }

  @Override
  public String toString() {
    return "Record " + values.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Record) {
      Record that = (Record) o;
      return Objects.equals(schema, that.schema)
          && Objects.equals(values, that.values);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(schema, values);
  }
}
