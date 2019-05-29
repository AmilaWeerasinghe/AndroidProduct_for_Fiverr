package com.mystiko.mycalculator;

import android.support.v7.widget.RecyclerView;

/**
 * Project: Calculator
 * Author: kamalhamoud
 * Date: 2016-12-03
 */

public interface CalcMainActivityContract {

    void displayExpression(String aString);
    void displayResult(String aString);
    RecyclerView getHistoryRecyclerView();
}
