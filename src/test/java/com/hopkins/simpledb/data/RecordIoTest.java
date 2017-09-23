package com.hopkins.simpledb.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by ian_000 on 7/8/2017.
 */
public class RecordIoTest {
  private byte[] buffer = new byte[1024];

  private ByteReader byteReader;
  private ByteWriter byteWriter;

  private Schema schema;
  private RecordIo recordIo;

  @Before
  public void setup() {
    schema = new Schema(Arrays.asList(
        Column.ROW_ID,
        Column.newStringColumn("name", 10),
        Column.newBoolColumn("is_active")
    ));

    byteReader = new ByteReader(buffer);
    byteWriter = new ByteWriter(buffer);

    recordIo = new RecordIo();
  }

  @Test
  public void writeRecord_movesPosition() {
    // Setup
    Record toWrite = new Record(schema);
    toWrite.set(0, 12);
    toWrite.set(1, "Pontiac");
    toWrite.set(2, false);
    System.err.println(toWrite);

    // Execute
    recordIo.writeRecord(byteWriter, toWrite);

    // Verify
    assertThat(byteWriter.getPosition()).isGreaterThan(0);
  }

  @Test
  public void readRecord_afterWriteRecord_returnsRecord() {
    // Setup
    Record toWrite = new Record(schema);
    toWrite.set(0, 12);
    toWrite.set(1, "Chevrolet");
    toWrite.set(2, true);
    recordIo.writeRecord(byteWriter, toWrite);

    // Execute
    Record fromReader = recordIo.readRecord(byteReader, schema);

    // Verify
    assertThat(fromReader.get(0)).isEqualTo(12);
    assertThat(fromReader.get(1)).isEqualTo("Chevrolet");
    assertThat(fromReader.get(2)).isEqualTo(true);
  }
}
