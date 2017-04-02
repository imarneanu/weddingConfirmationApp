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
            DatabaseContract.GuestEntry.COLUMN_NAME_ATTEND,
            DatabaseContract.GuestEntry.COLUMN_NAME_PLUS_ONE_NAME,
            DatabaseContract.GuestEntry.COLUMN_NAME_ATTEND_CHURCH,
            DatabaseContract.GuestEntry.COLUMN_NAME_ATTEND_ONLY_CHURCH,
            DatabaseContract.GuestEntry.COLUMN_NAME_ACCOMMODATION_START_DATE,
            DatabaseContract.GuestEntry.COLUMN_NAME_ACCOMMODATION_END_DATE,
            DatabaseContract.GuestEntry.COLUMN_NAME_COMMENT,
            DatabaseContract.GuestEntry.COLUMN_NAME_TIMESTAMP
    };

    public static int _ID = 0;
    public static int COUNTER = _ID + 1;

    public static int GUEST_ID = COUNTER + 1;
    public static int GUEST_NAME = GUEST_ID + 1;
    public static int ATTEND = GUEST_NAME + 1;
    public static int PLUS_ONE_NAME = ATTEND + 1;
    public static int ATTEND_CHURCH = PLUS_ONE_NAME + 1;
    public static int ATTEND_ONLY_CHURCH = ATTEND_CHURCH + 1;
    public static int ACCOMMODATION_START_DATE = ATTEND_ONLY_CHURCH + 1;
    public static int ACCOMMODATION_END_DATE = ACCOMMODATION_START_DATE + 1;
    public static int COMMENT = ACCOMMODATION_END_DATE + 1;
    public static int TIMESTAMP = COMMENT + 1;
}
