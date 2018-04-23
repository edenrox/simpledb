package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.*;

public final class ComparisonExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final ComparisonOperator op;

    public ComparisonExpression(Expression left, ComparisonOperator op, Expression right) {
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
        boolean isEqual = left.getValue(record).equals(right.getValue(record));
        if (op == ComparisonOperator.EQUAL) {
            return isEqual;
        } else if (op == ComparisonOperator.NOT_EQUAL) {
            return !isEqual;
        }

        int compare =
                ColumnCompare.compare(left.getType(record.getSchema()), left.getValue(record), right.getValue(record));
        switch (op) {
            case LESS_THAN:
                return compare < 0;
            case LESS_THAN_OR_EQUAL:
                return compare <= 0;
            case GREATER_THAN:
                return compare > 0;
            case GREATER_THAN_OR_EQUAL:
                return compare >= 0;
            default:
                throw new IllegalArgumentException("Unexpected operation: " + op);
        }
    }

    @Override
    public String toString() {
        return "ComparisonExpr {"
                + left
                + " " + op.getOp() + " "
                + right
                + '}';
    }

    public enum ComparisonOperator {
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        NOT_EQUAL("!="),
        EQUAL("="),
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        ;

        private final String op;

        ComparisonOperator(String op) {
            this.op = op;
        }

        public String getOp() {
            return op;
        }
    }

}
