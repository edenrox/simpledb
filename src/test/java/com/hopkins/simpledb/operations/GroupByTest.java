package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.RecordUtil;
import com.hopkins.simpledb.aggregations.AggregationType;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

public class GroupByTest {

  private RecordIterator iterator;
  private GroupBy groupBy;

  @Before
  public void setup() {
    Schema schema = new Schema(Arrays.asList(
        Column.newIntColumn("year"),
        Column.newStringColumn("name", 20),
        Column.newStringColumn("subject", 20),
        Column.newIntColumn("grade")));
    List<Record> recordList = new ArrayList<>();

    Record record = new Record(schema);
    record.setAll(1999, "Mark Twain", "English", 48);
    recordList.add(record);
    record = new Record(schema);
    record.setAll(1999, "Mark Twain", "Math", 82);
    recordList.add(record);
    record = new Record(schema);
    record.setAll(1999, "Mark Twain", "Biology", 71);
    recordList.add(record);
    record = new Record(schema);
    record.setAll(2000, "Mark Twain", "Biology", 40);
    recordList.add(record);

    record = new Record(schema);
    record.setAll(1999, "Sean Smith", "English", 90);
    recordList.add(record);
    record = new Record(schema);
    record.setAll(1999, "Sean Smith", "Math", 80);
    recordList.add(record);
    record = new Record(schema);
    record.setAll(1999, "Sean Smith", "Biology", 70);
    recordList.add(record);

    iterator = new RecordIterator(schema, recordList);
  }

  @Test
  public void testCount_withEmptyGroupColumns_returnsSingleRecordWithCount() {
    groupBy = new GroupBy(iterator, Collections.emptyList(), Collections.singletonMap("name", AggregationType.COUNT));

    List<Record> result = DbIteratorUtil.openReadAllClose(groupBy);

    assertThat(result).hasSize(1);
    assertThat(result.get(0).get(0)).isEqualTo(7);
  }

  @Test
  public void testAverage_withNameColumn_returnsAveragePerName() {
    Map<String, AggregationType> map = new HashMap<>();
    map.put("name", AggregationType.FIRST);
    map.put("grade", AggregationType.AVERAGE);
    groupBy = new GroupBy(iterator, Collections.singletonList("name"), map);

    List<Record> result = DbIteratorUtil.openReadAllClose(groupBy);

    assertThat(result).hasSize(2);
    assertThat(result.get(0).get(0)).isEqualTo("Mark Twain");
    assertThat(result.get(0).get(1)).isEqualTo(60.25);
    assertThat(result.get(1).get(0)).isEqualTo("Sean Smith");
    assertThat(result.get(1).get(1)).isEqualTo(80.0);
  }

  @Test
  public void testMin_withMultipleColumns_returnsMinPerGroup() {
    Map<String, AggregationType> map = new HashMap<>();
    map.put("year", AggregationType.FIRST);
    map.put("name", AggregationType.FIRST);
    map.put("grade", AggregationType.MIN);
    groupBy = new GroupBy(iterator, Arrays.asList("year", "name"), map);

    List<Record> result = DbIteratorUtil.openReadAllClose(groupBy);
    RecordUtil.printAll(result);

    assertThat(result).hasSize(3);
    assertThat(result.get(0).get(0)).isEqualTo(1999);
    assertThat(result.get(0).get(1)).isEqualTo("Mark Twain");
    assertThat(result.get(0).get(2)).isEqualTo(48);
    assertThat(result.get(1).get(0)).isEqualTo(2000);
    assertThat(result.get(1).get(1)).isEqualTo("Mark Twain");
    assertThat(result.get(1).get(2)).isEqualTo(40);
    assertThat(result.get(2).get(0)).isEqualTo(1999);
    assertThat(result.get(2).get(1)).isEqualTo("Sean Smith");
    assertThat(result.get(2).get(2)).isEqualTo(70);
  }
}
