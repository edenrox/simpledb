package com.hopkins.simpledb.disk;

import com.hopkins.simpledb.app.Config;
import com.hopkins.simpledb.app.DiskFileManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

public class DiskFileManagerImplTest {
  private static final String TEST_FILE_NAME = "test.db";
  private static final int PAGE_SIZE = 100;
  private static final int CACHE_SIZE = 1000;

  @Mock private Config config;
  private DiskFileManagerImpl diskFileManager;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    when(config.getPageSize()).thenReturn(PAGE_SIZE);
    when(config.getCacheSize()).thenReturn(CACHE_SIZE);

    diskFileManager = new DiskFileManagerImpl(config);
  }

  @After
  public void teardown() throws IOException {
    diskFileManager.close();
    File file = new File(TEST_FILE_NAME);
    file.delete();
  }

  @Test
  public void init_createsFile() throws IOException {
    diskFileManager.init(TEST_FILE_NAME);

    File file = new File(TEST_FILE_NAME);
    assertThat(file.exists()).isTrue();
  }

  @Test
  public void getPageCount_afterInit_returnsZero() throws IOException {
    diskFileManager.init(TEST_FILE_NAME);

    assertThat(diskFileManager.getPageCount()).isEqualTo(0);
  }

  @Test
  public void getPageCount_afterWrite_returnsPageCount() throws IOException {
    diskFileManager.init(TEST_FILE_NAME);
    diskFileManager.writePage(0, new byte[PAGE_SIZE]);

    assertThat(diskFileManager.getPageCount()).isEqualTo(1);
  }

  @Test
  public void writePage_increasesFileSize() throws IOException {
    diskFileManager.init(TEST_FILE_NAME);
    diskFileManager.writePage(0, new byte[PAGE_SIZE]);
    diskFileManager.flush();

    File file = new File(TEST_FILE_NAME);
    assertThat(file.length()).isEqualTo(PAGE_SIZE);
  }

  @Test
  public void readPage_afterWritePage_returnsData() throws IOException {
    // Setup the data
    byte[] dataToWrite = new byte[PAGE_SIZE];
    dataToWrite[0] = 4;
    dataToWrite[1] = 5;
    dataToWrite[2] = 5;
    dataToWrite[3] = 3;

    // Write the data
    diskFileManager.init(TEST_FILE_NAME);
    diskFileManager.writePage(0, dataToWrite);

    // Read the data
    byte[] dataToRead = new byte[PAGE_SIZE];
    diskFileManager.readPage(0, dataToRead);

    assertThat(dataToRead).isEqualTo(dataToWrite);
  }
}
