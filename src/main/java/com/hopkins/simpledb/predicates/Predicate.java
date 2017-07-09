package com.hopkins.simpledb.predicates;

import com.hopkins.simpledb.data.Record;

/**
 * Created by ian_000 on 7/2/2017.
 */
public interface Predicate {
  boolean matches(Record record);
}
