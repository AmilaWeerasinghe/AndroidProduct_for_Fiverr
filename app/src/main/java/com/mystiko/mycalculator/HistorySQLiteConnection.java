package com.mystiko.mycalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Program:
 * Project: ${Project_Name}
 * Author: kamalhamoud
 * Date: 2016-01-16
 */
public class HistorySQLiteConnection extends SQLiteOpenHelper {
    // log TAG
    private static final String LOG_TAG = HistorySQLiteConnection.class.getSimpleName();
    // Single Instance
    private static HistorySQLiteConnection sInstance;

    // Database Info
    private static final String DATABASE_NAME = "historyDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    private static final String HISTORY_TABLE = "history";

    // History table columns
    private static final String HISTORY_ID = "id";
    private static final String HISTORY_EXPRESSION = "expression";
    private static final String HISTORY_RESULT = "result";

    /**
     * constructor for HistorySQLiteConnection
     * - private to prevent direct instantiation
     * - use getInstance() instance
     *
     * @param context
     */
    private HistorySQLiteConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate for SQLiteDatabase, creates table
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HISTORY_TABLE = "CREATE TABLE IF NOT EXISTS " + HISTORY_TABLE +
                "(" +
                HISTORY_ID + " INTEGER PRIMARY KEY," +
                HISTORY_EXPRESSION + " TEXT," +
                HISTORY_RESULT + " TEXT" + ")";

        db.execSQL(CREATE_HISTORY_TABLE);
    }

    /**
     * Checks version number and updates if its not the same as current version number
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + HISTORY_TABLE);
        }
        onCreate(db);
    }

    /**
     * makes sure only 1 instace of the database is on device
     *
     * @param context
     * @return
     */
    public static synchronized HistorySQLiteConnection getsInstance(Context context) {
        // Using application context, ensuring no data leak on Activity's context.
        if (sInstance == null) {
            sInstance = new HistorySQLiteConnection(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * adds history object into the db
     *
     * @param historyObject
     */
    public void addHistory(HistoryObject historyObject) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(HISTORY_EXPRESSION, historyObject.expressionString);
            values.put(HISTORY_RESULT, historyObject.resultString);

            //Log.d(LOG_TAG, historyObject.expressionString + historyObject.resultString);

            db.insertOrThrow(HISTORY_TABLE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(LOG_TAG, "Error updating history" + e);
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * deletes all of the objects in the database
     */
    public void deleteHistory() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(HISTORY_TABLE, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(LOG_TAG, "Error while deleting history");
        } finally {
            db.endTransaction();
        }
    }

    /**
     * creates a list of the objects in the database
     *
     * @return list of HistoryObjects
     */
    public List<HistoryObject> getHistory() {
        List<HistoryObject> history = new ArrayList<>();

        String HISTORY_SELECT_QUERY = "SELECT * FROM " + HISTORY_TABLE +
                " ORDER BY " + HISTORY_ID + " DESC";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(HISTORY_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    HistoryObject newHistoryObject = new HistoryObject();
                    newHistoryObject.Id = cursor.getLong(cursor.getColumnIndex(HISTORY_ID));
                    newHistoryObject.expressionString = cursor.getString(cursor.getColumnIndex(HISTORY_EXPRESSION));
                    newHistoryObject.resultString = cursor.getString(cursor.getColumnIndex(HISTORY_RESULT));

                    history.add(newHistoryObject);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(LOG_TAG, "Error getting history");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return history;
    }
}