package com.hopkins.simpledb.app;

public final class Page {
  public static final int CATALOG_ROOT_PAGE_NUMBER = 0;

  private final byte[] buffer;
  private int pageNumber;
  private boolean isDirty;
  private int pinCount;
  private boolean isRecentlyUsed;

  public Page(byte[] buffer) {
    this.pageNumber = -1;
    this.buffer = buffer;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public PageType getType() {
    return PageType.fromValue(buffer[0]);
  }

  public byte[] getBuffer() {
    return buffer;
  }

  public boolean isCatalogRootPage() {
    return pageNumber == CATALOG_ROOT_PAGE_NUMBER;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public boolean isDirty() {
    return isDirty;
  }

  public void setIsDirty(boolean isDirty) {
    this.isDirty = isDirty;
  }

  public int getPinCount() {
    return pinCount;
  }

  public void pin() {
    pinCount++;
  }

  public void unpin() {
    pinCount--;
  }

  public boolean isRecentlyUsed() {
    return isRecentlyUsed;
  }

  public void setRecentlyUsed(boolean isRecentlyUsed) {
    this.isRecentlyUsed = isRecentlyUsed;
  }

  @Override
  public String toString() {
    return "Page {"
        + "id: " + pageNumber
        + ", type: " + getType()
        + ", isDirty: " + isDirty
        + ", isRecentlyUsed: " + isRecentlyUsed
        + ", pinCount: " + pinCount
        + "}";
  }
}
