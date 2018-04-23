package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

public final class BooleanExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final BooleanOperator op;

    public BooleanExpression(Expression left, BooleanOperator op, Expression right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public ColumnType getType(Schema schema) {
        return ColumnType.BOOL;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getValue(Record record) {
        boolean leftValue = (Boolean) left.getValue(record);
        switch (op) {
            case OR:
                return leftValue || ((Boolean) right.getValue(record));
            case AND:
                return leftValue && ((Boolean) right.getValue(record));
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    @Override
    public String toString() {
        return "BooleanExpr {"
                + left
                + " " + op + " "
                + right
                + '}';
    }

    public enum BooleanOperator {
        AND,
        OR
    }
}
