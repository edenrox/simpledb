package com.hopkins.simpledb.data;

import com.hopkins.simpledb.util.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ian_000 on 6/3/2017.
 */
public class RecordList {
  private final Schema schema;
  private final List<Record> recordList;

  private RecordList(Builder builder) {
    this.schema = builder.schema;
    this.recordList = Collections.unmodifiableList(new ArrayList<>(builder.recordList));
  }

  public Schema getSchema() {
    return schema;
  }

  public List<Record> getRecordList() {
    return recordList;
  }

  public static class Builder {
    private Schema schema;
    private List<Record> recordList = new ArrayList<>();

    public Builder setSchema(Schema schema) {
      Preconditions.checkState(this.schema == null);
      this.schema = schema;
      return this;
    }

    public Builder addRecord(Record record) {
      Preconditions.checkArgument(schema.equals(record.getSchema()));
      this.recordList.add(record);
      return this;
    }

    public RecordList build() {
      return new RecordList(this);
    }
  }
}
