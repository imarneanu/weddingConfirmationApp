package com.imarneanu.alexiuliawedding.data.models;

/**
 * Created by iulia on 24/03/2017.
 */

import com.google.gson.annotations.SerializedName;

import com.imarneanu.alexiuliawedding.data.query.QueryGuests;

import android.database.Cursor;

public class GuestModel {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("timestamp")
    public String timestamp;

    public static GuestModel fromCursor(Cursor cursor) {
        GuestModel guestModel = new GuestModel();
        guestModel.id = cursor.getString(QueryGuests.GUEST_ID);
        guestModel.name = cursor.getString(QueryGuests.GUEST_NAME);
        guestModel.timestamp = cursor.getString(QueryGuests.TIMESTAMP);
        return guestModel;
    }
}
