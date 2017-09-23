package com.hopkins.simpledb.app;

public enum PageType {
  HEAP_PAGE(1),
  INDEX_BTREE_INTERNAL(2),
  INDEX_BTREE_LEAF(3),
  FREE(4);

  public static PageType fromValue(byte value) {
    switch (value) {
      case 1:
        return HEAP_PAGE;
      case 2:
        return INDEX_BTREE_INTERNAL;
      case 3:
        return INDEX_BTREE_LEAF;
      case 4:
        return FREE;
      default:
        throw new IllegalArgumentException("Unknown page type value: " + value);
    }
  }

  private final byte value;

  PageType(int value) {
    this.value = (byte) value;
  }

  public byte getValue() {
    return value;
  }
}
