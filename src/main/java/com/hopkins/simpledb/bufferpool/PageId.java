package com.hopkins.simpledb.bufferpool;

/**
 * Created by ian_000 on 5/31/2017.
 */
public class PageId {
  private static final int TEMP_TABLE_ID = -1;

  /** A {@link PageId} used for temporary pages. */
  public static final PageId TEMP_PAGE_ID = new PageId(TEMP_TABLE_ID, -1);

  private final int tableId;
  private final int pageNumber;

  public PageId(int tableId, int pageNumber) {
    this.tableId = tableId;
    this.pageNumber = pageNumber;
  }

  public boolean isTemporaryPage() {
    return tableId == TEMP_TABLE_ID;
  }

  public int getTableId() {
    return tableId;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = result * 37 + tableId;
    result = result * 37 + pageNumber;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof PageId) {
      PageId that = (PageId) obj;
      return that.tableId == this.tableId
          && that.pageNumber == this.pageNumber;
    }
    return false;
  }

  @Override
  public String toString() {
    return "PageId {"
        + "tableId: " + tableId
        + ", pageNumber: " + pageNumber
        + "}";
  }
}
