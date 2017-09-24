package com.hopkins.simpledb.disk;

import com.hopkins.simpledb.app.Config;
import com.hopkins.simpledb.app.DiskFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public final class DiskFileManagerImpl implements DiskFileManager {
  private final Config config;

  private File file;
  private FileChannel fileChannel;
  private int pageCount;

  public DiskFileManagerImpl(Config config) {
    this.config = config;
  }

  @Override
  public void init(String fileName) throws IOException {
    file = new File(fileName);
    if (!file.exists()) {
      if (!file.createNewFile()) {
        throw new IOException("could not create file: " + fileName);
      }
    }
    pageCount = (int) (file.length() / config.getPageSize());
    fileChannel = FileChannel.open(
        file.toPath(), StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE);
  }

  @Override
  public void writePage(int pageNumber, byte[] buffer) throws IOException {
    if (pageNumber < 0) {
      throw new IndexOutOfBoundsException();
    }
    long position = config.getPageSize() * pageNumber;
    ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
    fileChannel.write(byteBuffer, position);
    if (pageNumber >= pageCount) {
      pageCount = pageNumber + 1;
    }
  }

  @Override
  public void readPage(int pageNumber, byte[] buffer) throws IOException {
    if (pageNumber < 0 || pageNumber >= pageCount) {
      throw new IndexOutOfBoundsException("pageNumber: " + pageNumber + " pageCount: " + pageCount);
    }
    long position = config.getPageSize() * pageNumber;
    ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
    fileChannel.read(byteBuffer, position);
  }

  @Override
  public int getPageCount() {
    return pageCount;
  }

  @Override
  public void flush() throws IOException {
    fileChannel.force(true);
  }

  @Override
  public void close() throws IOException {
    if (fileChannel != null) {
      fileChannel.close();
    }
  }
}
