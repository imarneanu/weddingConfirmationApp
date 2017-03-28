package com.imarneanu.alexiuliawedding.data;

import com.imarneanu.alexiuliawedding.data.models.GuestModel;
import com.imarneanu.alexiuliawedding.data.query.QueryGuests;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
}
