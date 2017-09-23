package com.hopkins.simpledb.app;

import java.io.Closeable;
import java.io.IOException;

public interface DiskFileManager extends Closeable {

  void init(String fileName) throws IOException;

  void writePage(int pageNumber, byte[] buffer) throws IOException;

  void readPage(int pageNumber, byte[] buffer) throws IOException;

  int getPageCount();

  void flush() throws IOException;
}
