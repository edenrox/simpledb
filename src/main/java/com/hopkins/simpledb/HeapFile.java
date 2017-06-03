package com.hopkins.simpledb;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by ian_000 on 5/31/2017.
 */
public class HeapFile implements AutoCloseable {
  private static final String MODE_READ_WRITE_SYNC = "rws";

  private final RandomAccessFile file;

  public HeapFile(File fileToOpen) throws IOException {
    file = new RandomAccessFile(fileToOpen, MODE_READ_WRITE_SYNC);
  }

  public void readPage(int pageNumber, byte[] buffer) throws IOException {
    int pageSize = buffer.length;

    file.seek(pageNumber * pageSize);
    file.read(buffer);
  }

  public void writePage(int pageNumber, byte[] buffer) throws IOException {
    int pageSize = buffer.length;

    file.seek(pageNumber * pageSize);
    file.write(buffer);
  }

  @Override
  public void close() throws IOException {
    file.close();
  }

  public void delete() throws IOException {

  }
}
