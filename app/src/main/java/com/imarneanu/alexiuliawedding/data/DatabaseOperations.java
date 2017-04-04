package com.imarneanu.alexiuliawedding.data;

import com.imarneanu.alexiuliawedding.data.models.GuestModel;
import com.imarneanu.alexiuliawedding.data.query.QueryGuests;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by imarneanu on 28/03/2017.
 */

public class DatabaseOperations {

    public static int addGuest(Context context, GuestModel guest) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        if (guest.guestId.equals("standard")) {
            return insertGuest(dbHelper, guest);
        }

        GuestModel dbGuest = isGuestInDatabase(dbHelper, guest);
        if (dbGuest != null) {
            guest._id = dbGuest._id;
            guest.counter = dbGuest.counter;
            if (TextUtils.isEmpty(guest.attend)) {
                guest.counter++;
            }
            return updateGuest(dbHelper, guest);
        }
        return insertGuest(dbHelper, guest);
    }

    private static int insertGuest(DatabaseHelper dbHelper, GuestModel guest) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = createGuestValues(guest);
        return (int) db.insert(DatabaseContract.GuestEntry.TABLE_NAME, null, values);
    }

    private static ContentValues createGuestValues(GuestModel guest) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_ID, guest.guestId);
        if (!TextUtils.isEmpty(guest.guestName)) {
            values.put(DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_NAME, guest.guestName);
        }
        if (guest.attend != null) {
            values.put(DatabaseContract.GuestEntry.COLUMN_NAME_ATTEND, guest.attend);
            values.put(DatabaseContract.GuestEntry.COLUMN_NAME_PLUS_ONE_NAME, guest.plusOneName);
            values.put(DatabaseContract.GuestEntry.COLUMN_NAME_ATTEND_CHURCH, guest.attendChurch);
            values.put(DatabaseContract.GuestEntry.COLUMN_NAME_ATTEND_ONLY_CHURCH, guest.attendOnlyChurch);
            values.put(DatabaseContract.GuestEntry.COLUMN_NAME_ACCOMMODATION_START_DATE, guest.accommodationStartDate);
            values.put(DatabaseContract.GuestEntry.COLUMN_NAME_ACCOMMODATION_END_DATE, guest.accommodationEndDate);
            values.put(DatabaseContract.GuestEntry.COLUMN_NAME_COMMENT, guest.comment);
        }
        values.put(DatabaseContract.GuestEntry.COLUMN_NAME_TIMESTAMP, guest.timestamp);
        values.put(DatabaseContract.GuestEntry.COLUMN_NAME_COUNTER, guest.counter);
        return values;
    }

    private static int updateGuest(DatabaseHelper dbHelper, GuestModel guest) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = DatabaseContract.GuestEntry._ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(guest._id)};

        ContentValues values = createGuestValues(guest);
        db.update(DatabaseContract.GuestEntry.TABLE_NAME, values, selection, selectionArgs);
        return guest._id;
    }

    private static GuestModel isGuestInDatabase(DatabaseHelper dbHelper, GuestModel guest) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_ID + " = ?";
        String[] selectionArgs = new String[]{guest.guestId};
        Cursor cursor = db.query(DatabaseContract.GuestEntry.TABLE_NAME,
                QueryGuests.PROJECTION_ID,
                selection,
                selectionArgs,
                null,
                null,
                null);

        GuestModel guestModel = null;
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                guestModel = GuestModel.fromCursor(cursor);
            }
            cursor.close();
        }
        return guestModel;
    }

    public static ArrayList<GuestModel> getGuests(Context context, String selection,
                                                  String[] selectionArgs, String sortOrder) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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
}
