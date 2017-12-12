package com.hopkins.simpledb.operations;

import com.hopkins.simpledb.aggregations.Aggregation;
import com.hopkins.simpledb.aggregations.AggregationFactory;
import com.hopkins.simpledb.aggregations.AggregationType;
import com.hopkins.simpledb.data.Column;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;
import com.hopkins.simpledb.util.Preconditions;

import java.util.*;

public class GroupBy implements DbIterator {
  private final DbIterator source;
  private final Schema schema;
  private final List<String> groupByColumns;
  private final Map<String, Aggregation> aggregationMap;

  private Record peek;

  /**
   * Instantiate a {@link DbIterator} which performs a GROUP BY and aggregations.  Result records
   * contain only columns in the {@code aggregationTypeMap}.  For GROUP BY columns, use the
   * {@link AggregationType#FIRST}.
   * 
   * <p>Assumes the source iterator is ordered by the {@code groupByColumns}.
   */
  public GroupBy(DbIterator source, List<String> groupByColumns, Map<String, AggregationType> aggregationTypeMap) {
    Preconditions.checkArgument(aggregationTypeMap.size() <= source.getSchema().getColumnCount());
    this.source = source;
    this.groupByColumns = groupByColumns;
    this.aggregationMap = buildAggregationMap(aggregationTypeMap);
    this.schema = buildSchema();
  }

  private Map<String, Aggregation> buildAggregationMap(Map<String, AggregationType> aggregationTypeMap) {
    Schema sourceSchema = source.getSchema();
    Map<String, Aggregation> map = new HashMap<>(aggregationTypeMap.size());
    for (String columnName : aggregationTypeMap.keySet()) {
      Column sourceColumn = sourceSchema.getColumn(sourceSchema.indexOf(columnName));
      Aggregation aggregation = AggregationFactory.getAggregation(
          aggregationTypeMap.get(columnName), sourceColumn, " ");
      map.put(columnName, aggregation);
    }
    return Collections.unmodifiableMap(map);
  }

  private Schema buildSchema() {
    Schema sourceSchema = source.getSchema();
    List<Column> columnList = new ArrayList<>();
    for (int i = 0; i < sourceSchema.getColumnCount(); i++) {
      String columnName = sourceSchema.getColumnName(i);
      if (aggregationMap.containsKey(columnName)) {
        columnList.add(aggregationMap.get(columnName).getOutputColumn());
      }
    }
    return new Schema(columnList);
  }

  @Override
  public void open() {
    source.open();
    peek = null;
  }

  @Override
  public boolean hasNext() {
    return peek != null || source.hasNext();
  }

  @Override
  public Record next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    // Peek the first record
    if (peek == null) {
      peek = source.next();
    }

    Schema sourceSchema = source.getSchema();
    Record first = peek;

    // Run all matching records through the aggregates
    while (allGroupByColumnsEqual(first, peek)) {
      for (int i = 0; i < sourceSchema.getColumnCount(); i++) {
        String sourceColumn = sourceSchema.getColumnName(i);
        if (aggregationMap.containsKey(sourceColumn)) {
          Aggregation aggregation = aggregationMap.get(sourceColumn);
          Object value = peek.get(sourceColumn);
          aggregation.addValue(value);
        }
      }
      if (source.hasNext()) {
        peek = source.next();
      } else {
        peek = null;
        break;
      }
    }

    // Extract the aggregate values into the record
    Record aggregate = new Record(schema);
    for (int i = 0; i < sourceSchema.getColumnCount(); i++) {
      String sourceColumn = sourceSchema.getColumnName(i);
      if (aggregationMap.containsKey(sourceColumn)) {
        Aggregation aggregation = aggregationMap.get(sourceColumn);
        String outputColumn = aggregation.getOutputColumn().getName();
        aggregate.set(outputColumn, aggregation.getAggregate());
        aggregation.reset();
      }
    }
    return aggregate;
  }

  private boolean allGroupByColumnsEqual(Record a, Record b) {
    if (a == b) {
      return true;
    }
    for (String columnName : groupByColumns) {
      if (!a.get(columnName).equals(b.get(columnName))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Schema getSchema() {
    return schema;
  }

  @Override
  public void close() {
    source.close();
  }
}
