package com.mystiko.mycalculator;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Program: ButtonsHelper
 * Project: Calculator
 * Author: kamalhamoud
 * Date: 2016-01-07
 */
public class ButtonsHelper {

    static HistorySQLiteConnection db;
    private static final String LOG_TAG = CalcMainActivity.class.getSimpleName();
    protected List<HistoryObject> aList;
    static CalcMainActivityContract activityContract;

    public ButtonsHelper(View v, Context context, CalcMainActivityContract activty) {
        db = HistorySQLiteConnection.getsInstance(context);
        parentView = v;
        expressionView = (TextView) v.findViewById(R.id.expression_output);
        activityContract = activty;
        aList = db.getHistory();
    }

    protected static View parentView;
    protected static TextView expressionView;
    protected static String expressionString = "";

    /**
     * Flags
     */
    protected static boolean OpenBracketsFlag = false;
    protected static boolean lastBracketsFlag = false;
    protected static boolean lastOperatorFlag = false;
    protected static boolean lastOperandFlag = false;
    protected static boolean periodFlag = false;
    protected static boolean getAnswer = false;


    /*********************************************************
     * Operators
     */

    /**
     * btnDiv()
     *
     * @param view of the button
     */
    public void btnDiv(View view) {
        String operator = " / ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                getAnswer = true;
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }
        activityContract.displayExpression(expressionString);
    }// end btnFraction()

    /**
     * btnExponent()
     *
     * @param view of the button
     */
    public void btnExponent(View view) {
        String operator = " ^ ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }
        activityContract.displayExpression(expressionString);

    }

    /**
     * btnRemainder()
     *
     * @param view of the button
     */
    public void btnRemainder(View view) {
        String operator = " % ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        activityContract.displayExpression(expressionString);
    }// end btnDiv

    /**
     * btnMul()
     *
     * @param view of the button
     */
    public void btnMul(View view) {
        String operator = " x ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        activityContract.displayExpression(expressionString);
    }// end btnMul()

    /**
     * btnSub()
     *
     * @param view of the button
     */
    public void btnSub(View view) {
        String operator = " - ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        activityContract.displayExpression(expressionString);
    }// end btnSub

    /**
     * btnAdd()
     *
     * @param view of the button
     */
    public void btnAdd(View view) {
        String operator = " + ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (expressionString.equals("") && aList.size() > 0) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        activityContract.displayExpression(expressionString);
    }// end btnAdd()

    /**
     * btnEq()
     *
     * @param view of the button
     */
    public void btnEq(View view) {

        // Create historyObject which is returned by computeResult
        if ( expressionString.endsWith(" ") &&
                !expressionString.endsWith(" ) ") || expressionString.endsWith(".") ) {
            Snackbar.make(parentView, "End expression with a number.",
                    Snackbar.LENGTH_SHORT)
                    .setAction("DISMISS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
            return;
        }
        HistoryObject historyObject = ExpressionParser.computeResult(expressionString);

        // display resultString
        activityContract.displayResult(historyObject.resultString);
        // reset expression string
        activityContract.displayExpression(expressionString = "");
        // add history object to history database
        if (!historyObject.expressionString.equals("0")) {
            db.addHistory(historyObject);

            HistoryAdapter adapter = new HistoryAdapter(db.getHistory());
            activityContract.getHistoryRecyclerView().setAdapter(adapter);
            adapter.notifyItemInserted(aList.size() - 1);
            activityContract.getHistoryRecyclerView().scrollToPosition(0);
        }
        clearAllFlags();
    }


    /**
     * btnBrackets()
     *
     * @param view of the button
     */
    public void btnBrackets(View view) {
        String closeBrackets = " ) ", openBrackets = " ( ";

        if (!OpenBracketsFlag && !lastBracketsFlag &&
                lastOperatorFlag || expressionString.equals("")) {

            expressionString = expressionString.concat(openBrackets);
            setOpenBracketsFlag(true);
            setLastBracketsFlag(true);

        } else if (OpenBracketsFlag && !lastOperatorFlag) {

            expressionString = expressionString.concat(closeBrackets);
            setOpenBracketsFlag(false);
            setLastBracketsFlag(true);

        } else {

            Snackbar.make(parentView, "Input Operator before Bracket\n" +
                            "Input Number after Bracket",
                    Snackbar.LENGTH_SHORT)
                    .setAction("DISMISS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
        }
        activityContract.displayExpression(expressionString);
    }// end btnBrackets()

    /**
     * btnLog()
     *
     * @param view of the button
     */
    public void btnLog(View view) {
        String operator = " log ";

        if (lastOperatorFlag &&
                !lastBracketsFlag) {
                Snackbar.make(parentView, "Input Operator",
                        Snackbar.LENGTH_SHORT)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperandFlag(true);
        }

        activityContract.displayExpression(expressionString);
    }

    /*********************************************************
     * Operands
     */

    /**
     * btn9()
     *
     * @param view of the button
     */
    public void btn9(View view) {
        expressionString = expressionString.concat("9");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btn8()
     *
     * @param view of the button
     */
    public void btn8(View view) {
        expressionString = expressionString.concat("8");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btn7()
     *
     * @param view of the button
     */
    public void btn7(View view) {
        expressionString = expressionString.concat("7");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btn6()
     *
     * @param view of the button
     */
    public void btn6(View view) {
        expressionString = expressionString.concat("6");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btn5()
     *
     * @param view of the button
     */
    public void btn5(View view) {
        expressionString = expressionString.concat("5");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btn4()
     *
     * @param view of the button
     */
    public void btn4(View view) {
        expressionString = expressionString.concat("4");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btn3()
     *
     * @param view of the button
     */
    public void btn3(View view) {
        expressionString = expressionString.concat("3");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }


    /**
     * btn2()
     *
     * @param view of the button
     */
    public void btn2(View view) {
        expressionString = expressionString.concat("2");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btn1()
     *
     * @param view of the button
     */
    public void btn1(View view) {
        expressionString = expressionString.concat("1");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btn0()
     *
     * @param view of the button
     */
    public void btn0(View view) {
        expressionString = expressionString.concat("0");
        setLastOperandFlag(true);
        activityContract.displayExpression(expressionString);
    }

    /**
     * btnPeriod()
     *
     * @param view of the button
     */
    public void btnPeriod(View view) {

        if (periodFlag) {
            Snackbar.make(parentView, "Illegal to set another period",
                    Snackbar.LENGTH_SHORT)
                    .setAction("DISMISS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
        } else {
            expressionString = expressionString.concat(".");
            setPeriodFlag(true);
        }
        activityContract.displayExpression(expressionString);
    }

    /**
     * Negative button
     * @param v = current view
     */
    public void btnNeg(View v) {
        expressionString = expressionString.concat("-");
        activityContract.displayExpression(expressionString);
    }

    /*********************************************************
     * Functions
     */

    /**
     * btnDel()
     *
     * @param view of the button
     */
    public void btnDel(View view) {

        if (expressionString.isEmpty()) {
            activityContract.displayExpression("0");
        } else {

            if (expressionString.endsWith(" ")) {
                deleteTillOperand();

            } else {
                expressionString = expressionString.substring(0,
                        expressionString.length() - 1);
            }

            activityContract.displayExpression(expressionString);
        }
    }// end btnDel()


    /**
     * btnClear()
     *
     * @param view of the button
     */
    public void btnClear(View view) {
        activityContract.displayExpression(expressionString = "");
        activityContract.displayResult("");
        clearAllFlags();
        Snackbar.make(parentView, "Expression Cleared",
                Snackbar.LENGTH_SHORT)
                .setAction("DISMISS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }

    /**
     * btnClearAll()
     *
     * @param view of the button
     */
    public void btnClearHistory(View view) {
        activityContract.displayResult("0");
        activityContract.displayExpression(expressionString = "");

        if (db.getHistory().size() > 0) {
            db.deleteHistory();

            HistoryAdapter adapter = new HistoryAdapter(db.getHistory());
            activityContract.getHistoryRecyclerView().setAdapter(adapter);
            adapter.notifyDataSetChanged();
            activityContract.getHistoryRecyclerView().scrollToPosition(0);
        }

        clearAllFlags();
        Snackbar.make(parentView, "All Cleared",
                Snackbar.LENGTH_SHORT)
                .setAction("DISMISS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }

    /**
     * btnAns()
     *
     * @param view of the button
     */
    public void btnAns(View view) {
        view.setBackgroundColor(Color.parseColor("#607D8B"));

        Snackbar.make(parentView, "Select an Answer from history",
                Snackbar.LENGTH_SHORT)
                .setAction("DISMISS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
        getAnswer = true;
        activityContract.displayExpression(expressionString);
    }// end btnAvg()


    /*********************************************************
     * Switches
     */

    /**
     * setOpenBracketsFlag()
     *
     * @param bool setting of the openBracketsFlag
     */
    protected void setOpenBracketsFlag(boolean bool) {
        OpenBracketsFlag = bool;
    }

    /**
     * setLastBracketsFlag()
     *
     * @param bool setting of the setLastBracketsFlag
     */
    protected static void setLastBracketsFlag(boolean bool) {
        lastBracketsFlag = bool;
        lastOperandFlag = false;
        lastOperatorFlag = false;
    }

    /**
     * setLastOperatorFlag()
     *
     * @param bool setting of the setLastOperatorFlag
     */
    protected static void setLastOperatorFlag(boolean bool) {
        lastOperatorFlag = bool;
        lastBracketsFlag = false;
        lastOperandFlag = false;
        periodFlag = false;
    }

    /**
     * setLastOperandFlag()
     *
     * @param bool setting of the setLastOperandFlag
     */
    protected static void setLastOperandFlag(boolean bool) {
        lastOperandFlag = bool;
        lastBracketsFlag = false;
        lastOperatorFlag = false;
    }

    /**
     * setPeriodFlag
     *
     * @param bool setting of the setPeriodFlag
     */
    protected void setPeriodFlag(boolean bool) {
        periodFlag = bool;
        lastBracketsFlag = false;
        lastOperandFlag = false;
        lastOperatorFlag = false;
    }

    /**
     * clearAllFlags()
     */
    protected void clearAllFlags() {
        lastBracketsFlag = false;
        lastOperandFlag = false;
        lastOperatorFlag = false;
        lastBracketsFlag = false;
        periodFlag = false;
    }

    /**
     * Allows you to either use an expression or result from history in next equation
     *
     * @param aHistoryObject historyObject that contains the recalled history
     */
    static void recallHistory(HistoryObject aHistoryObject) {

        if (getAnswer && (lastOperatorFlag || expressionString.equals(""))) {

            if (aHistoryObject.resultString.equals("-Infinity") ||
                    aHistoryObject.resultString.equals("Infinity") ||
                    aHistoryObject.resultString.contains("E") ||
                    Double.parseDouble(aHistoryObject.resultString) == 0) {
                Snackbar.make(parentView, "Error - Invalid result from history",
                        Snackbar.LENGTH_SHORT)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
                return;
            }

            expressionString += aHistoryObject.resultString;
            getAnswer = false;

        } else if (lastOperatorFlag || expressionString.equals("")) {

            expressionString += aHistoryObject.expressionString;

        } else {

            Snackbar.make(parentView, "Enter an operator",
                    Snackbar.LENGTH_SHORT)
                    .setAction("DISMISS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
        }
//        Log.d(LOG_TAG, "expression: " + expressionString);
        activityContract.displayExpression(expressionString);
        setFlags(expressionString);
        setLastOperandFlag(true);
    }

    /**
     * sets the flags of the expressionString passed
     * @param expressionStr passed Expression String sent to the view
     */
    protected static void setFlags(String expressionStr) {
        if (expressionStr.contains("[\\(]")
                && !expressionStr.contains("[\\)]")) {
            OpenBracketsFlag = true;
        }
        if (expressionStr.endsWith("[ ][\\+-\\^%/x][ ]")) {
           setLastOperatorFlag(true);
        }
    }

    /**
     * grabs the top history item and loads the result into the expression view
     * @param operator sent to be concatenated to the recalled object
     */
    private void reloadPreviousResult(String operator){

        if (db.getHistory().isEmpty()) {
            Snackbar.make(parentView, "Error - History not available, Enter number",
                    Snackbar.LENGTH_SHORT)
                    .setAction("DISMISS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
            return;
        }
        List<HistoryObject> history = db.getHistory();
        HistoryObject aHistoryObject = history.get(0);

        if (aHistoryObject.resultString.equals("Infinity") ||
                aHistoryObject.resultString.contains("E") ||
                aHistoryObject.resultString.equals("-Infinity") ||
                Double.parseDouble(aHistoryObject.resultString) == 0) {
            Snackbar.make(parentView, "Error - Invalid result from history",
                    Snackbar.LENGTH_SHORT)
                    .setAction("DISMISS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
            return;
        }
        expressionString = (aHistoryObject.resultString + operator);

        activityContract.displayExpression(expressionString);
        setFlags(expressionString);
        setLastOperatorFlag(true);
    }

    /**
     * deletes starting from the end of expressionString string the first operator it encounters
     */
    private void deleteTillOperand() {
        int i = 0;
        String lastChar = expressionString.substring(expressionString.length() - 1, expressionString.length());

        while (!expressionString.isEmpty() && !ExpressionParser.isOperand(lastChar)) {
            if (lastChar.matches("\\)")) setOpenBracketsFlag(true);
            i++;
            if (!expressionString.isEmpty()) {
                expressionString = expressionString.substring(0, expressionString.length() - 1);
                if (!expressionString.isEmpty()) {
                    lastChar = expressionString.substring(expressionString.length() - 1,
                                    expressionString.length());
                }
            }
            if (i > 4) break;
        }

        if (expressionString.length() > 4 &&
                expressionString.substring(expressionString.substring(0,
                        expressionString.length()-1).lastIndexOf(" "),
                            expressionString.length()).matches("[\\(\\)log\\^\\+\\-%/x]+")){
            setLastOperatorFlag(true);
        } else {
            setLastOperandFlag(true);
        }
        Log.d(LOG_TAG, "In delete " + "expression String is: " + expressionString
                + "\nlast char is " + lastChar);
    }


}
