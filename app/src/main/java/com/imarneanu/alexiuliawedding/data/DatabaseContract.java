package com.imarneanu.alexiuliawedding.data;

import android.provider.BaseColumns;

/**
 * Created by iulia on 24/03/2017.
 */

public final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseContract() {
    }

    /* Inner class that defines the table contents */
    public static class GuestEntry implements BaseColumns {
        public static final String TABLE_NAME = "guest";
        public static final String COLUMN_NAME_GUEST_ID = "guest_id";
        public static final String COLUMN_NAME_GUEST_NAME = "guest_name";
        public static final String COLUMN_NAME_PLUS_ONE_NAME = "plus_one_name";
        public static final String COLUMN_NAME_ACCOMMODATION_PERIOD = "accommodation_period";
        public static final String COLUMN_NAME_COMMENT = "comment";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_COUNTER = "counter";
    }
}
