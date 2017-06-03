package com.hopkins.simpledb.bufferpool;

/**
 * Created by ian_000 on 5/31/2017.
 */
public class BufferPage {
  private final byte[] buffer;

  private PageId pageId;
  private boolean isDirty;

  public BufferPage(byte[] buffer) {
    this.buffer = buffer;
  }

  public PageId getPageId() {
    return pageId;
  }

  public void setPageId(PageId pageId) {
    this.pageId = pageId;
  }

  public boolean isDirty() {
    return isDirty;
  }

  public void setDirty(boolean isDirty) {
    this.isDirty = isDirty;
  }

  public byte[] getBuffer() {
    return buffer;
  }

}
