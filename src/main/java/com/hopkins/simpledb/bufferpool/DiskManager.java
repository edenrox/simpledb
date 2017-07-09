package com.hopkins.simpledb.bufferpool;

import com.hopkins.simpledb.app.Config;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by ian_000 on 7/7/2017.
 */
public class DiskManager {
  private static final String MODE_READ_WRITE_SYNC = "rws";

  public static File getTableFile(int tableId) {
    return new File(tableId + ".table");
  }

  private static long getOffset(int pageNumber) {
    return ((long) Config.PAGE_SIZE) * pageNumber;
  }

  public static long getFileSize(int tableId) {
    return getTableFile(tableId).length();
  }

  public static void deleteFile(int tableId) {
    getTableFile(tableId).delete();
  }

  public static void setFileLength(int tableId, long newLength) {
    try {
      RandomAccessFile file = new RandomAccessFile(getTableFile(tableId), MODE_READ_WRITE_SYNC);
      long offset = file.length();
      file.setLength(newLength);
      if (offset < newLength) {
        byte[] emptyBuffer = new byte[Config.PAGE_SIZE];
        while (offset < newLength) {
          file.seek(offset);
          file.write(emptyBuffer);
          offset += emptyBuffer.length;
        }
      }
      file.close();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static void readPage(PageId pageId, byte[] buffer) {
    try {
      RandomAccessFile file = new RandomAccessFile(getTableFile(pageId.getTableId()), MODE_READ_WRITE_SYNC);
      file.seek(getOffset(pageId.getPageNumber()));
      file.read(buffer);
      file.close();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static void writePage(PageId pageId, byte[] buffer) {
    try {
      RandomAccessFile file = new RandomAccessFile(getTableFile(pageId.getTableId()), MODE_READ_WRITE_SYNC);
      file.seek(getOffset(pageId.getPageNumber()));
      file.write(buffer);
      file.close();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
