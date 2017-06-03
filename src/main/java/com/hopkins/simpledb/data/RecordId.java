package com.hopkins.simpledb.data;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class RecordId {
  private final int pageNumber;
  private final int recordNumber;

  public RecordId(int pageNumber, int recordNumber) {
    this.pageNumber = pageNumber;
    this.recordNumber = recordNumber;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getRecordNumber() {
    return recordNumber;
  }
}
