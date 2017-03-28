package com.imarneanu.alexiuliawedding.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.imarneanu.alexiuliawedding.data.DatabaseOperations;
import com.imarneanu.alexiuliawedding.data.models.GuestModel;

import org.json.JSONObject;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class MessagingService extends FirebaseMessagingService {
    public MessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        String jsonString = "";
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            jsonString = new JSONObject(remoteMessage.getData()).toString();
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        // Use Gson to create GuestModel
        Gson gson = new GsonBuilder().create();
        GuestModel guest = gson.fromJson(jsonString, GuestModel.class);
        // Save guest to db if guest guestId recognized
        if (!guest.guestId.equals("undefined")) {
            DatabaseOperations.addGuest(getApplicationContext(), guest);
        }
    }
}
