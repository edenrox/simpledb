package com.hopkins.simpledb;

import com.hopkins.simpledb.data.Schema;

import java.nio.ByteBuffer;

/**
 * Created by edenrox on 5/29/2017.
 */
public class Table {
  private final String name;
  private final Schema schema;

  private byte[] buffer;
  private int size;

  public static Table createTable(String name, Schema schema) {
    int rowLength = schema.getLength();
    int initialBufferSize = rowLength * 16;
    byte[] initialBuffer = new byte[initialBufferSize];

    return new Table(name, schema, initialBuffer, 0);
  }

  private Table(String name, Schema schema, byte[] buffer, int size) {
    this.name = name;
    this.schema = schema;
    this.buffer = buffer;
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }

  public Schema getSchema() {
    return schema;
  }

  private ByteBuffer getBytesForRow(int index) {
    int length = schema.getLength();
    int offset = index * length;
    ByteBuffer byteBuffer = ByteBuffer.allocate(length);
    byteBuffer.put(buffer, offset, length);
    return byteBuffer;
  }

  public Tuple getRow(int index) {
    return new Tuple(index, schema, getBytesForRow(index));
  }

  public Tuple createTuple() {
    return new Tuple(-1, schema, ByteBuffer.allocate(schema.getLength()));
  }

  public void insert(Tuple tuple) {
    // TODO(ianhopkins): ensure there is enough space in the buffer

    int index = size;
    int length = schema.getLength();
    int destPosition = index * length;

    // Copy the tuple into the buffer
    System.arraycopy(tuple.getData(), 0, buffer, destPosition, length);
    size++;
  }

}
