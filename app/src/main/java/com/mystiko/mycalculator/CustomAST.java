package com.mystiko.mycalculator;

/**
 * Created by navneetgarg on 2016-02-01.
 */
import java.text.BreakIterator;
import java.util.Stack;

public class CustomAST {
    public static enum Parentheses { LEFT, RIGHT }

    public static enum BinaryOperator {
        ADD('+', 1) {
            public BigRational eval(BigRational leftValue, BigRational rightValue) {  return leftValue.add(rightValue);  }
        },
        SUB('-', 1) {
            public BigRational eval(BigRational leftValue, BigRational rightValue) {  return leftValue.subtract(rightValue);  }
        },
        MUL('x', 2) {
            public BigRational eval(BigRational leftValue, BigRational rightValue) {  return leftValue.multiply(rightValue);  }
        },
        DIV('/', 2) {
            public BigRational eval(BigRational leftValue, BigRational rightValue) {  return leftValue.divide(rightValue);  }
        },
        MOD('%', 2) {
            public BigRational eval(BigRational leftValue, BigRational rightValue) {  return leftValue.mod(rightValue); }
        },
        EXP('^', 3) {
            public BigRational eval(BigRational leftValue, BigRational rightValue) {  return leftValue.pow(rightValue.intValue()); }
        },
        LOG('$', 3) {
            public BigRational eval(BigRational leftValue, BigRational rightValue) {  return leftValue.log(rightValue); }
        };

        public final char symbol;
        public final int precedence;

        BinaryOperator(char symbol, int precedence) {
            this.symbol = symbol;
            this.precedence = precedence;
        }

        public abstract BigRational eval(BigRational leftValue, BigRational rightValue);
    }

    public static class BinaryExpression {
        public Object leftOperand = null;
        public BinaryOperator operator = null;
        public Object rightOperand = null;

        public BinaryExpression(Object leftOperand, BinaryOperator operator, Object rightOperand) {
            this.leftOperand = leftOperand;
            this.operator = operator;
            this.rightOperand = rightOperand;
        }

        public BigRational eval() {
            BigRational leftValue = (leftOperand instanceof BinaryExpression) ? ((BinaryExpression)leftOperand).eval() : (BigRational)leftOperand;
            BigRational rightValue = (rightOperand instanceof BinaryExpression) ? ((BinaryExpression)rightOperand).eval() : (BigRational)rightOperand;
            return operator.eval(leftValue, rightValue);
        }

        public String toString()
        {  return "(" + leftOperand + " " + operator.symbol + " " + rightOperand + ")";  }
    }

    public static void createNewOperand(BinaryOperator operator, Stack<Object> operands) {
        Object rightOperand = operands.pop();
        operands.push(new BinaryExpression(operands.pop(), operator, rightOperand));
        return;
    }

    public static Object createExpression(String inputString)
    {
        int curIndex = 0;
        boolean afterOperand = false;
        Stack<Object> operands = new Stack<>();
        Stack<Object> operators = new Stack<>();
        inputStringLoop:
        while (curIndex < inputString.length()) {
            int startIndex = curIndex;
            char c = inputString.charAt(curIndex++);
            if (Character.isWhitespace(c))
                continue;
            if (afterOperand) {
                if (c == ')') {
                    Object operator = null;
                    while (!operators.isEmpty() && ((operator = operators.pop()) != Parentheses.LEFT))
                        createNewOperand((BinaryOperator)operator, operands);
                    continue;
                }
                afterOperand = false;
                for (BinaryOperator operator : BinaryOperator.values()) {
                    if (c == operator.symbol) {
                        while (!operators.isEmpty() && (operators.peek() != Parentheses.LEFT) && (((BinaryOperator)operators.peek()).precedence >= operator.precedence))
                            createNewOperand((BinaryOperator)operators.pop(), operands);
                        operators.push(operator);
                        continue inputStringLoop;
                    }
                }
                throw new IllegalArgumentException();
            }
            if (c == '(') {
                operators.push(Parentheses.LEFT);
                continue;
            }
            afterOperand = true;
            while (curIndex < inputString.length()) {
                c = inputString.charAt(curIndex);
                if (((c < '0') || (c > '9')) && (c != '.'))
                    break;
                curIndex++;
            }
            operands.push(BigRational.valueOf(inputString.substring(startIndex, curIndex)));
        }

        while (!operators.isEmpty()) {
            Object operator = operators.pop();
            if (operator == Parentheses.LEFT)
                throw new IllegalArgumentException();
            createNewOperand((BinaryOperator)operator, operands);
        }
        Object expression = operands.pop();
        if (!operands.isEmpty())
            throw new IllegalArgumentException();
        return expression;
    }

    public static String evaluate(String input) {
        Object result = createExpression(input);
        return ((BinaryExpression)result).eval().toString();
    }

}
