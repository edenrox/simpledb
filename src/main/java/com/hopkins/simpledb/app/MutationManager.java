package com.hopkins.simpledb.app;

import com.hopkins.simpledb.catalog.TableDescriptor;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.expression.Expression;

import java.util.Map;

public interface MutationManager {

  int insert(String tableName, Object... values);

  int insert(TableDescriptor table, Record record);

  int update(String tableName, Expression expression, Map<String, Object> values);

  int delete(String tableName, Expression expression);
}
