package com.hopkins.simpledb.predicates;

import com.hopkins.simpledb.data.Record;

/**
 * Created by ian_000 on 7/2/2017.
 */
public class EqualsPredicate implements Predicate {
  private final String column1Name;
  private final String column2Name;

  public EqualsPredicate(String column1Name, String column2Name) {
    this.column1Name = column1Name;
    this.column2Name = column2Name;
  }

  @Override
  public boolean matches(Record record) {
    Object value = record.get(column1Name);
    return value != null && value.equals(record.get(column2Name));
  }

  @Override
  public String toString() {
    return "EqualsPredicate{" + column1Name + " = " + column2Name + "}";
  }
}
