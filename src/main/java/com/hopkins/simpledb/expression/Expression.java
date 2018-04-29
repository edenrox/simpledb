package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

/** Represents an expression. */
public interface Expression {

  /** Returns the column name of the expression. */
  String getName();

  /** Returns the {@link ColumnType} of the expression. */
  ColumnType getType(Schema schema);

  /** Evaluate and return the value of the expression. */
  Object getValue(Record record);
}
