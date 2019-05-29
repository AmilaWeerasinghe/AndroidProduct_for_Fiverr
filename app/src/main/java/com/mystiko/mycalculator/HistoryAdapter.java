package com.mystiko.mycalculator;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Program:
 * Project: ${Project_Name}
 * Author: kamalhamoud
 * Date: 2016-01-16
 */
public class HistoryAdapter extends
        RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder
                                    implements View.OnClickListener {

        public TextView expressionTextView;
        public TextView resultTextView;
        // private Context context;

        /**
         * ViewHolder constructor
         *  - Constructor that accepts the item row and does view lookups
         *      to find each subview
         * @param context context of the view
         * @param itemView view to grab the id's
         */
        public ViewHolder(Context context, View itemView) {
            // Stores the itemView in a public final member variable that can be used
            //  to access the context from any ViewHolder instance.
            super(itemView);
            expressionTextView = (TextView) itemView.findViewById(R.id.history_expression);
            resultTextView = (TextView) itemView.findViewById(R.id.history_result);
            // Store the context
            // this.context = context;
            // Attack a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            HistoryObject historyObject = history.get(position);
            ButtonsHelper.recallHistory(historyObject);

            Button btnAns = (Button) CalcMainActivity.parentView.findViewById(R.id.btnAns);
            btnAns.setBackgroundColor(Color.parseColor("#009688"));
            ButtonsHelper.getAnswer = false;
        }
    }

    // Storing a member variable for history
    private static List<HistoryObject> history;

    /**
     * HistoryAdapter()
     *  - pass history array into constructor
     * @param aHistory an object HistoryObject
     */
    public HistoryAdapter(List<HistoryObject> aHistory) {
        history = aHistory;
    }


    /**
     * onCreateViewHolder()
     * @param parent parent viewgroup
     * @param viewType the viewtype integer
     * @return returns the view holder object
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View historyView = inflater.inflate(R.layout.history_item_template, parent, false);

        // Return a new holder instance
        return new ViewHolder(context, historyView);
    }

    /**
     * onBindViewHolder()
     * @param holder view holder of the history
     * @param position position of the history object
     */
    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        HistoryObject historyObject = history.get(position);

        // Set item views based on the data model
        TextView expressionTextView = holder.expressionTextView;
        TextView resultTextView = holder.resultTextView;

        expressionTextView.setText(historyObject.expressionString);
        resultTextView.setText(historyObject.resultString);
    }

    /**
     * getItemCount()
     * @return size of the history database
     */
    @Override
    public int getItemCount() {
        return history.size();
    }


}
