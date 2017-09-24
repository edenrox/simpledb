package com.hopkins.simpledb.freepages;

import com.hopkins.simpledb.app.FreePageManager;
import com.hopkins.simpledb.app.Page;
import com.hopkins.simpledb.app.PageType;
import com.hopkins.simpledb.data.ByteReader;
import com.hopkins.simpledb.data.ByteWriter;
import com.hopkins.simpledb.util.Preconditions;

public class FreePage {
  // Heap Page Layout
  // ================
  // ~~~~ Data offset ~~~~
  // byte pageType - the type
  // int freePage - the next free page in the free page list (-1 if this is the last free page)

  public static final FreePage initializePage(Page page) {
    // RootPage cannot be a free page
    Preconditions.checkArgument(page.getPageNumber() != 0);

    ByteWriter byteWriter = new ByteWriter(page.getBuffer());
    byteWriter.writeByte(PageType.FREE.getValue());
    byteWriter.writeInt(FreePageManager.NO_FREE_PAGE_INDEX);
    page.setIsDirty(true);

    return new FreePage(page);
  }

  private final Page page;
  private final ByteReader byteReader;
  private final ByteWriter byteWriter;

  public FreePage(Page page) {
    this.page = page;
    this.byteReader = new ByteReader(page.getBuffer());
    this.byteWriter = new ByteWriter(page.getBuffer());
  }

  public boolean isCatalogRoot() {
    return page.getPageNumber() == 0;
  }

  public int getPageNumber() {
    return page.getPageNumber();
  }

  public int getNextFreePage() {
    byteReader.setPosition(1);
    return byteReader.readInt();
  }

  public void setNextFreePage(int nextFreePage) {
    byteWriter.setPosition(1);
    byteWriter.writeInt(nextFreePage);
    page.setIsDirty(true);
  }

  public void unpin() {
    page.unpin();
  }

  @Override
  public String toString() {
    return "FreePage {"
        + "pageNumber: " + page.getPageNumber()
        + ", next: " + getNextFreePage()
        + '}';
  }
}
