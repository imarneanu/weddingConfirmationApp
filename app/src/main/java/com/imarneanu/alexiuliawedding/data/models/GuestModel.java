package com.imarneanu.alexiuliawedding.data.models;

/**
 * Created by iulia on 24/03/2017.
 */

import com.google.gson.annotations.SerializedName;

import com.imarneanu.alexiuliawedding.data.DatabaseContract;
import com.imarneanu.alexiuliawedding.data.query.QueryGuests;

import android.database.Cursor;

public class GuestModel {
    @SerializedName("guest")
    public String guestId;
    @SerializedName("guestName")
    public String guestName;
    @SerializedName("attend")
    public String attend;
    @SerializedName("plusOneName")
    public String plusOneName;
    @SerializedName("accommodationPeriod")
    public String accommodationPeriod;
    @SerializedName("comment")
    public String comment;
    @SerializedName("timestamp")
    public String timestamp;

    // database id
    public int _id;
    // counts how many times the guest has accessed his/hers page
    public int counter = 1;

    public static GuestModel fromCursor(Cursor cursor) {
        GuestModel guestModel = new GuestModel();
        guestModel._id = cursor.getInt(QueryGuests._ID);
        guestModel.counter = cursor.getInt(QueryGuests.COUNTER);

        if (cursor.getColumnIndex(DatabaseContract.GuestEntry.COLUMN_NAME_GUEST_ID) != -1) {
            guestModel.guestId = cursor.getString(QueryGuests.GUEST_ID);
            guestModel.guestName = cursor.getString(QueryGuests.GUEST_NAME);
            guestModel.attend = cursor.getString(QueryGuests.ATTEND);
            guestModel.plusOneName = cursor.getString(QueryGuests.PLUS_ONE_NAME);
            guestModel.accommodationPeriod = cursor.getString(QueryGuests.ACCOMMODATION_PERIOD);
            guestModel.comment = cursor.getString(QueryGuests.COMMENT);
            guestModel.timestamp = cursor.getString(QueryGuests.TIMESTAMP);
        }
        return guestModel;
    }
}
