package com.imarneanu.alexiuliawedding.data;

import com.imarneanu.alexiuliawedding.data.models.GuestModel;
import com.imarneanu.alexiuliawedding.data.query.QueryGuests;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by iulia on 28/03/2017.
 */

public class DatabaseOperations {

    public static ArrayList<GuestModel> getGuests(Context context, String selection,
                                                  String[] selectionArgs, String sortOrder) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        ArrayList<GuestModel> guests = new ArrayList<>();
        Cursor cursor = db.query(DatabaseContract.GuestEntry.TABLE_NAME,
                QueryGuests.PROJECTION_SIMPLE,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                guests.add(GuestModel.fromCursor(cursor));
            }
            cursor.close();
        }
        return guests;
    }

    public static long insertGuest(Context context, GuestModel guest) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = createGuestValues(guest);

        // Insert the new row, returning the primary key value of the new row
        return db.insert(DatabaseContract.GuestEntry.TABLE_NAME, null, values);
    }

    private static ContentValues createGuestValues(GuestModel guest) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_ID, guest.id);
        values.put(DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_NAME, guest.name);
        values.put(DatabaseContract.GuestEntry.COLUMN_NAME_TIMESTAMP, guest.timestamp);
        return values;
    }
}
