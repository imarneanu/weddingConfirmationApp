package com.imarneanu.alexiuliawedding.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.imarneanu.alexiuliawedding.MainActivity;
import com.imarneanu.alexiuliawedding.R;
import com.imarneanu.alexiuliawedding.data.DatabaseOperations;
import com.imarneanu.alexiuliawedding.data.models.GuestModel;

import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;

public class MessagingService extends FirebaseMessagingService {
    private static final String TAG = MessagingService.class.getSimpleName();

    public MessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
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
        int id = 0;
        if (!guest.guestId.equals("undefined")) {
            id = DatabaseOperations.addGuest(getApplicationContext(), guest);
        }
        guest._id = id;
        showNotification(guest);
    }

    private void showNotification(GuestModel guest) {
        String notificationText = "Page accessed: " + guest.timestamp;

        if (!TextUtils.isEmpty(guest.attend)) {
            notificationText = guest.guestName.concat(" is ").concat(guest.isAttending() ?
                    "coming" : "not coming").concat(" to the wedding");
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(!TextUtils.isEmpty(guest.guestName) ? guest.guestName : guest.guestId)
                        .setContentText(notificationText);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(guest._id, mBuilder.build());
    }
}
