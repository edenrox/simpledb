package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.ColumnType;
import com.hopkins.simpledb.data.Record;
import com.hopkins.simpledb.data.Schema;

public final class ArithmeticExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final ArithmeticOperation op;

    public ArithmeticExpression(Expression left, ArithmeticOperation op, Expression right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ColumnType getType(Schema schema) {
        return left.getType(schema);
    }

    @Override
    public Object getValue(Record record) {
        return isDouble(record) ? getDoubleValue(record) : getIntValue(record);
    }

    private boolean isDouble(Record record) {
        return left.getType(record.getSchema()) == ColumnType.DOUBLE
                || right.getType(record.getSchema()) == ColumnType.DOUBLE
                || op == ArithmeticOperation.DIVISION;
    }

    private double getDoubleValue(Record record) {
        double leftValue = (Double) left.getValue(record);
        double rightValue = (Double) right.getValue(record);
        switch (op) {
            case MODULO:
                return leftValue % rightValue;
            case ADDITION:
                return leftValue + rightValue;
            case SUBTRACTION:
                return leftValue - rightValue;
            case MULTIPLICATION:
                return leftValue * rightValue;
            case DIVISION:
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Unexpected operator: " + op);
        }
    }

    private int getIntValue(Record record) {
        int leftValue = (Integer) left.getValue(record);
        int rightValue = (Integer) right.getValue(record);
        switch (op) {
            case MODULO:
                return leftValue % rightValue;
            case ADDITION:
                return leftValue + rightValue;
            case SUBTRACTION:
                return leftValue - rightValue;
            case MULTIPLICATION:
                return leftValue * rightValue;
            case DIVISION:
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Unexpected operator: " + op);
        }
    }

    @Override
    public String toString() {
        return "ArithmeticExpr {"
                + left
                + " " + op.getOp() + " "
                + right
                + '}';
    }

    public enum ArithmeticOperation {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISION("/"),
        MODULO("%"),
        ;

        private final String op;

        ArithmeticOperation(String op) {
            this.op = op;
        }

        public String getOp() {
            return op;
        }
    }

}
