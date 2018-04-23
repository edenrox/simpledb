package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

public interface Expression {

    String getName();

    ColumnType getType(Schema schema);

    Object getValue(Record record);
}
