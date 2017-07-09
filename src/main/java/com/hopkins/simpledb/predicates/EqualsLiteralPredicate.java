package com.hopkins.simpledb.predicates;

import com.hopkins.simpledb.data.Record;

/**
 * Created by ian_000 on 7/3/2017.
 */
public class EqualsLiteralPredicate implements Predicate {
  private final String columnName;
  private final Object literalValue;

  public EqualsLiteralPredicate(String columnName, Object literalValue) {
    this.columnName = columnName;
    this.literalValue = literalValue;
  }

  @Override
  public boolean matches(Record record) {
    Object value = record.get(columnName);
    return value != null && value.equals(literalValue);
  }
}
