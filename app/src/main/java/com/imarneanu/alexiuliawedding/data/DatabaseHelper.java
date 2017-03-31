package com.imarneanu.alexiuliawedding.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iulia on 24/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AlexIuliaWedding.db";

    private static final String SQL_CREATE_GUESTS =
            "CREATE TABLE " + DatabaseContract.GuestEntry.TABLE_NAME + " (" +
                    DatabaseContract.GuestEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_ID + " TEXT," +
                    DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_NAME + " TEXT," +
                    DatabaseContract.GuestEntry.COLUMN_NAME_PLUS_ONE_NAME + " TEXT," +
                    DatabaseContract.GuestEntry.COLUMN_NAME_ACCOMMODATION_PERIOD + " TEXT," +
                    DatabaseContract.GuestEntry.COLUMN_NAME_COMMENT + " TEXT," +
                    DatabaseContract.GuestEntry.COLUMN_NAME_TIMESTAMP + " TEXT," +
                    DatabaseContract.GuestEntry.COLUMN_NAME_COUNTER + " TEXT)";

    private static final String SQL_DELETE_GUESTS =
            "DROP TABLE IF EXISTS " + DatabaseContract.GuestEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GUESTS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_GUESTS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}