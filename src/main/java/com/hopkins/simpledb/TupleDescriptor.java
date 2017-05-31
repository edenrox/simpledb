package com.hopkins.simpledb;

import java.util.List;

/**
 * Created by edenrox on 5/29/2017.
 */
public class TupleDescriptor {

  private List<ColumnDescriptor> columnDescriptorList;
  private int length;

  public TupleDescriptor(List<ColumnDescriptor> columnDescriptorList) {
    this.columnDescriptorList = columnDescriptorList;
    this.length = calculateLength();
  }

  private int calculateLength() {
    int length = 0;
    for (ColumnDescriptor descriptor : columnDescriptorList) {
      length += descriptor.getLength();
    }
    return length;
  }

  /** Returns the number of columns in this descriptor. */
  public int getSize() {
    return columnDescriptorList.size();
  }

  /** Return the size of a tuple with this descriptor in bytes. */
  public int getLength() {
    return length;
  }

  public int indexOf(String columnName) {
    for (int i = 0; i < columnDescriptorList.size(); i++) {
      if (columnDescriptorList.get(i).getName().equals(columnName)) {
        return i;
      }
    }
    return -1;
  }

  public String getFieldName(int index) {
    return columnDescriptorList.get(index).getName();
  }

  public ColumnType getFieldType(int index) {
    return columnDescriptorList.get(index).getType();
  }

  public int getFieldLength(int index) {
    return columnDescriptorList.get(index).getLength();
  }

  public int getFieldOffset(int index) {
    int offset = 0;
    for (int i = 0; i < index; i++) {
      offset += columnDescriptorList.get(i).getLength();
    }
    return offset;
  }

  public ColumnDescriptor getColumnDescriptor(int index) {
    return columnDescriptorList.get(index);
  }
}
