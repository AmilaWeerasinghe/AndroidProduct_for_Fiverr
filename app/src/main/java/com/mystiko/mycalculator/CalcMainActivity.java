package com.mystiko.mycalculator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.util.List;

/**
 * Program: CalcMainActivity
 * Project: Calculator
 * Author: kamal hamoud
 * Date: 2016-01-07
 */
public class CalcMainActivity extends AppCompatActivity implements CalcMainActivityContract {

    private static final String LOG_TAG = CalcMainActivity.class.getSimpleName();

    TextView resultView;
    TextView expressionView;
    RecyclerView historyRecyclerView;
    static View parentView;

    private AdView mAdView;


    private AlphaAnimation buttonClicked = new AlphaAnimation(1F, 0.7F);
    private AlphaAnimation buttonLongClicked = new AlphaAnimation(1F, 0.4F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        expressionView = (TextView) findViewById(R.id.expression_output);
        resultView = (TextView) findViewById(R.id.result_output);
        parentView = findViewById(R.id.parent);
        historyRecyclerView = (RecyclerView) findViewById(R.id.history_col);
        final ButtonsHelper buttonsHelper = new ButtonsHelper(parentView, getApplicationContext()
                , this);
        ExpressionParser parser = new ExpressionParser(parentView, this);

        File dbFile = this.getDatabasePath("historyDatabase");
        Log.d(LOG_TAG, "dbFile exists =" + dbFile);

        // Database
        HistorySQLiteConnection db = HistorySQLiteConnection.getsInstance(this);


        List<HistoryObject> aList = db.getHistory();
        // see the history in log
//        for (HistoryObject item: aList) {
//            Log.d(LOG_TAG, item.Id + " " + item.expressionString + " " + item.resultString);
//        }

        // Create adapter passing in the list of HistoryObjects
        HistoryAdapter adapter = new HistoryAdapter(aList);
        // Attach adapter to historyRecyclerView
        historyRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*********************************************************
         * Operands Buttons onClick listeners
         */

        /**
         * btnPeriod
         */
        Button btnPeriod = (Button) findViewById(R.id.btnPeriod);
        btnPeriod.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.startAnimation(buttonLongClicked);
                buttonsHelper.btnNeg(v);
                return true;
            }
        });
        btnPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnPeriod(v);
            }

        });


        /**
         * btn0
         */
        Button btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn0(v);
            }
        });

        /**
         * btn1
         */
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn1(v);
            }
        });

        /**
         * btn2
         */
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn2(v);
            }
        });

        /**
         * btn3
         */
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn3(v);
            }
        });

        /**
         * btn4
         */
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn4(v);
            }
        });

        /**
         * btn5
         */
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn5(v);
            }
        });

        /**
         * btn6
         */
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn6(v);
            }
        });

        /**
         * btn7
         */
        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn7(v);
            }
        });

        /**
         * btn8
         */
        Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn8(v);
            }
        });

        /**
         * btn9
         */
        Button btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btn9(v);
            }
        });

        /*********************************************************
         * Operator Buttons onClick Listeners
         */

        /**
         * btnEq
         */
        Button btnEq = (Button) findViewById(R.id.btnEq);
        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnEq(v);
            }
        });

        /**
         * btnAdd
         */
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnAdd(v);
            }
        });

        /**
         * btnSub
         */
        Button btnSub = (Button) findViewById(R.id.btnSub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnSub(v);
            }
        });

        /**
         * btnMul
         */
        Button btnMul= (Button) findViewById(R.id.btnMul);
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnMul(v);
            }
        });

        /**
         * btnRemainder
         */
        Button btnRemainder = (Button) findViewById(R.id.btnRemainder);
        btnRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnRemainder(v);
            }
        });

        /**
         * btnDiv
         */
        Button btnDiv = (Button) findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnDiv(v);
            }
        });

        /*********************************************************
         *
         * Functions Buttons onClick Listener
         */

        /**
         * btnBrackets
         */
        Button btnBrackets = (Button) findViewById(R.id.btnBrackets);
        btnBrackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnBrackets(v);
            }
        });

        /**
         * btnDel
         */
        Button btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnDel(v);
            }
        });


        /**
         * btnAvg
         */
        Button btnAns = (Button) findViewById(R.id.btnAns);
        btnAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnAns(v);
            }
        });


        /**
         * btnSq
         */
        Button btnExponent = (Button) findViewById(R.id.btnExponent);
        btnExponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnExponent(v);
            }
        });

        /**
         * btnFF
         */
        Button btnLog = (Button) findViewById(R.id.btnLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnLog(v);
            }
        });

        /**
         * btnClear
         */
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnClear(v);
            }
        });

        /**
         * btnClearHistory
         */
        Button btnClearHistory = (Button) findViewById(R.id.btnClearHistory);
        btnClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClicked);
                buttonsHelper.btnClearHistory(v);
            }
        });

    }// end onCreate()

    /*********************************************************
     * View methods
     */

    /**
     * displayExpression()
     *  - displays the string passed in the expressionView
     *  @param aString passed to display to user
     */
    @Override
    public void displayExpression(String aString) {
        if (aString.compareTo("") == 0) {
            expressionView.setText("0");
        } else {
            expressionView.setText(aString);
        }
    }

    /**
     * displayResult()
     *  - displays the string passed in the resultView
     *  @param aString passed to display to user
     */
    @Override
    public void displayResult(String aString) {
        if (aString.compareTo("") == 0) {
            resultView.setText("0");
        } else {
            resultView.setText(aString);
        }
    }

    public RecyclerView getHistoryRecyclerView() {
        return historyRecyclerView;
    }
}
