package com.hopkins.simpledb.cache;

import com.hopkins.simpledb.app.Config;
import com.hopkins.simpledb.app.DiskFileManager;
import com.hopkins.simpledb.app.Page;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class CacheManagerImplTest {
  private static final int PAGE_SIZE = 100;
  private static final int CACHE_SIZE = 1000;

  @Mock private Config config;
  @Mock private DiskFileManager diskFileManager;
  private CacheManagerImpl cacheManager;


  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    when(config.getPageSize()).thenReturn(PAGE_SIZE);
    when(config.getCacheSize()).thenReturn(CACHE_SIZE);

    cacheManager = new CacheManagerImpl(config, diskFileManager);
  }

  @Test
  public void getPage_setsPageNumber() {
    int pageNumber = 12;
    Page page = cacheManager.getPage(pageNumber);

    assertThat(page.getPageNumber()).isEqualTo(pageNumber);
  }

  @Test
  public void getPage_incrementsPinCount() {
    int pageNumber = 7;
    Page page = cacheManager.getPage(pageNumber);
    cacheManager.getPage(pageNumber);
    cacheManager.getPage(pageNumber);

    assertThat(page.getPinCount()).isEqualTo(3);
  }

  @Test
  public void getPage_readsPage() throws IOException {
    int pageNumber = 13;
    cacheManager.getPage(pageNumber);

    verify(diskFileManager).readPage(eq(pageNumber), any(byte[].class));
  }

  @Test
  public void getPage_readsPage_onlyOnce() throws IOException {
    int pageNumber = 15;
    cacheManager.getPage(pageNumber);
    cacheManager.getPage(pageNumber);
    cacheManager.getPage(pageNumber);

    verify(diskFileManager, times(1)).readPage(eq(pageNumber), any(byte[].class));
  }

  @Test
  public void getPage_withMultiplePages() {
    Page page1 = cacheManager.getPage(1);
    Page page2 = cacheManager.getPage(2);

    assertThat(page1).isNotSameAs(page2);

    assertThat(page1.getPageNumber()).isEqualTo(1);
    assertThat(page2.getPageNumber()).isEqualTo(2);

    assertThat(page1.getBuffer()).isNotSameAs(page2.getBuffer());
  }

  @Test
  public void getPage_whenAllPagesPinned_throws() {
    int numCachePages = 10;

    for (int i = 0; i < numCachePages; i++) {
      cacheManager.getPage(i);
    }

    try {
      cacheManager.getPage(numCachePages);
      fail();
    } catch(CacheFullException ex) {
      // expected
    }
  }

  @Test
  public void getPage_whenNotPinned_evicts() {
    int numCachePages = 10;
    // Fill the cache and pin all the pages
    for (int i = 0; i < numCachePages; i++) {
      cacheManager.getPage(i);
    }

    // Get first page and double un-pin so it can be evicted
    Page page = cacheManager.getPage(0);
    assertThat(page.getPinCount()).isEqualTo(2);
    page.unpin();
    page.unpin();

    Page newPage = cacheManager.getPage(numCachePages);

    assertThat(newPage.getPageNumber()).isEqualTo(numCachePages);
    assertThat(newPage.getBuffer()).isSameAs(page.getBuffer());
  }
}
