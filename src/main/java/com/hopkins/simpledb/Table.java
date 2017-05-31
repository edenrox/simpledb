package com.hopkins.simpledb;

import java.nio.ByteBuffer;

/**
 * Created by edenrox on 5/29/2017.
 */
public class Table {
  private final String name;
  private final TupleDescriptor tupleDescriptor;

  private byte[] buffer;
  private int size;

  public static Table createTable(String name, TupleDescriptor tupleDescriptor) {
    int rowLength = tupleDescriptor.getLength();
    int initialBufferSize = rowLength * 16;
    byte[] initialBuffer = new byte[initialBufferSize];

    return new Table(name, tupleDescriptor, initialBuffer, 0);
  }

  private Table(String name, TupleDescriptor tupleDescriptor, byte[] buffer, int size) {
    this.name = name;
    this.tupleDescriptor = tupleDescriptor;
    this.buffer = buffer;
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }

  public TupleDescriptor getTupleDescriptor() {
    return tupleDescriptor;
  }

  private ByteBuffer getBytesForRow(int index) {
    int length = tupleDescriptor.getLength();
    int offset = index * length;
    ByteBuffer byteBuffer = ByteBuffer.allocate(length);
    byteBuffer.put(buffer, offset, length);
    return byteBuffer;
  }

  public Tuple getRow(int index) {
    return new Tuple(index, tupleDescriptor, getBytesForRow(index));
  }

  public Tuple createTuple() {
    return new Tuple(-1, tupleDescriptor, ByteBuffer.allocate(tupleDescriptor.getLength()));
  }

  public void insert(Tuple tuple) {
    // TODO(ianhopkins): ensure there is enough space in the buffer

    int index = size;
    int length = tupleDescriptor.getLength();
    int destPosition = index * length;

    // Copy the tuple into the buffer
    System.arraycopy(tuple.getData(), 0, buffer, destPosition, length);
    size++;
  }

}
