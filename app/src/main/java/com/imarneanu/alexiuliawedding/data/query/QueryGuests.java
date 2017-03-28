package com.imarneanu.alexiuliawedding.data.query;

import com.imarneanu.alexiuliawedding.data.DatabaseContract;

/**
 * Created by iulia on 28/03/2017.
 */

public class QueryGuests {

    public static String[] PROJECTION_ID = {
            DatabaseContract.GuestEntry._ID,
            DatabaseContract.GuestEntry.COLUMN_NAME_COUNTER
    };

    public static String[] PROJECTION_SIMPLE = {
            DatabaseContract.GuestEntry._ID,
            DatabaseContract.GuestEntry.COLUMN_NAME_COUNTER,
            DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_ID,
            DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_NAME,
            DatabaseContract.GuestEntry.COLUMN_NAME_TIMESTAMP
    };

    public static int _ID = 0;
    public static int COUNTER = _ID + 1;

    public static int GUEST_ID = COUNTER + 1;
    public static int GUEST_NAME = GUEST_ID + 1;
    public static int TIMESTAMP = GUEST_NAME + 1;
}
