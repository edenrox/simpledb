package com.hopkins.simpledb.predicates;

import com.hopkins.simpledb.data.Record;

public class AndPredicate implements Predicate {
  private final Predicate left;
  private final Predicate right;

  public AndPredicate(Predicate left, Predicate right) {
    this.left = left;
    this.right = right;
  }


  @Override
  public boolean matches(Record record) {
    return left.matches(record) && right.matches(record);
  }
}
