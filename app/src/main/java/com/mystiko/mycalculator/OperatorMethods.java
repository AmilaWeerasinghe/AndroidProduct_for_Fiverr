package com.mystiko.mycalculator;

/**
 * Created by navneetgarg on 2016-01-30.
 */
import java.util.HashMap;
public class OperatorMethods {
    private static OperatorMethods instance;
    private static HashMap<String, CalcFn> table;

    private OperatorMethods() {
        table = new HashMap<String, CalcFn>();
        table.put("+", new CalcFn(2) {
            public double method(double a, double b) {
                return a + b;
            }
        });

        table.put("-", new CalcFn(2) {
            public double method(double a, double b) {
                return a - b;
            }
        });

        table.put("x", new CalcFn(1) {
            public double method(double a, double b) {
                return a * b;
            }
        });

        table.put("/", new CalcFn(1) {
            public double method(double a, double b) {
                return a / b;
            }
        });

        table.put("%", new CalcFn(1) {
            public double method(double a, double b) {
                return a % b;
            }
        });

        table.put("^", new CalcFn(0) {
            public double method(double a, double b) {
                return Math.pow(a, b);
            }
        });

        table.put("log", new CalcFn(0) {
            public double method(double a, double b) {
                return Math.log10(a)/ Math.log10(b);
            }
        });
    }

    protected double exec(String s, double a, double b) {
        return table.get(s).method(a, b);
    }

    protected int priority(String s) {
        return table.get(s).priority;
    }

    public static OperatorMethods getInstance() {
        if (instance == null) {
            instance = new OperatorMethods();
        }

        return instance;
    }

    public abstract class CalcFn {
        int priority;
        public CalcFn(int p) { priority = p; }

        public abstract double method(double a, double b);
    }
}

