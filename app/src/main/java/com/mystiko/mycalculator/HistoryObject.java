package com.mystiko.mycalculator;

/**
 * Program: expressionObject
 * Project: Android Calculator
 * Author: kamalhamoud
 * Date: 2016-01-15
 */
public class HistoryObject {
    public Long Id;
    public String expressionString;
    public String resultString;

    /**
     * constructs a historyObject
     * @param anExpressionString to be stored in object
     * @param aResultString to be stored in object
     */
    public HistoryObject(Long anId,String anExpressionString, String aResultString){
        Id = anId;
        expressionString = anExpressionString;
        resultString = aResultString;
    }

    /**
     * constructs a historyObject
     * @param expressionString to be stored in object
     * @param resultString to be stored in object
     */
    public HistoryObject(String expressionString, String resultString) {
        this.expressionString = expressionString;
        this.resultString = resultString;
    }

    /**
     * - constructs a historyObject
     */
    public HistoryObject() {
    }


}
