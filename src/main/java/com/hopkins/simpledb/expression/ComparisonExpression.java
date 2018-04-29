package com.hopkins.simpledb.expression;

import com.hopkins.simpledb.data.*;

/**
 * An {@link Expression} which can compare two values.
 *
 * <p>Handles "[expr] [op] [expr]" where op = &lt;,&lt;=,=,&gt;,&gt;=,!=
 */
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

  /** Returns {@code true} if the two types are not equal, meaning a cast is required. */
  private boolean isCastRequired(ColumnType type1, ColumnType type2) {
    return type1 != type2;
  }

  /** Returns {@code true} if type1 is wider than type2, meaning type2 should be cast to type1. */
  private boolean isWider(ColumnType type1, ColumnType type2) {
    return getTypeWidth(type1) > getTypeWidth(type2);
  }

  private int getTypeWidth(ColumnType type) {
    switch (type) {
      case BOOL:
        return 0;
      case INTEGER:
        return 1;
      case DOUBLE:
        return 2;
      case STRING:
        return 3;
      case BLOB:
        return 4;
      default:
        throw new IllegalArgumentException();
    }
  }

  /** Cast the specified sourceType/value to the destType. */
  private Object cast(ColumnType sourceType, Object value, ColumnType destType) {
    switch (sourceType) {
      case BOOL:
        boolean bValue = (Boolean) value;
        switch (destType) {
          case INTEGER:
            return bValue ? 1 : 0;
          case DOUBLE:
            return bValue ? 1.0 : 0.0;
          case STRING:
            return String.valueOf(bValue);
          case BLOB:
            return String.valueOf(bValue).getBytes();
          default:
            throw new IllegalArgumentException();
        }
      case INTEGER:
        int iValue = (Integer) value;
        switch (destType) {
          case DOUBLE:
            return (double) iValue;
          case STRING:
            return String.valueOf(iValue);
          case BLOB:
            return String.valueOf(iValue).getBytes();
          default:
            throw new IllegalArgumentException();
        }
      case DOUBLE:
        double dValue = (Double) value;
        switch (destType) {
          case STRING:
            return String.valueOf(dValue);
          case BLOB:
            return String.valueOf(dValue).getBytes();
          default:
            throw new IllegalArgumentException();
        }
      case STRING:
        String sValue = (String) value;
        return sValue.getBytes();
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public Object getValue(Record record) {
    Object lValue = left.getValue(record);
    Object rValue = right.getValue(record);
    ColumnType lType = left.getType(record.getSchema());
    ColumnType rType = right.getType(record.getSchema());

    // Cast the value if required so we can compare
    if (isCastRequired(lType, rType)) {
      if (isWider(lType, rType)) {
        rValue = cast(rType, rValue, lType);
        rType = lType;
      } else {
        lValue = cast(lType, lValue, rType);
        lType = rType;
      }
    }

    // Do the equals comparison
    if (op == ComparisonOperator.EQUAL || op == ComparisonOperator.NOT_EQUAL) {
      boolean isEqual = lValue.equals(rValue);
      if (op == ComparisonOperator.EQUAL) {
        return isEqual;
      } else { // op == ComparisonOperator.NOT_EQUAL
        return !isEqual;
      }
    }

    int compare =
        ColumnCompare.compare(lType, lValue, rValue);
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
    LESS_THAN_OR_EQUAL("<="),;

    private final String op;

    ComparisonOperator(String op) {
      this.op = op;
    }

    public String getOp() {
      return op;
    }
  }

}
