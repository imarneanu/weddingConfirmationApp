package com.imarneanu.alexiuliawedding.services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.util.Log;

import java.io.IOException;

import static android.R.id.message;

/**
 * Created by iulia on 24/03/2017.
 */

public class InstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = InstanceIdService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        // Send token to Firebase db
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                "{\"token\":\"" + refreshedToken + "\"}");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://iuliaalexwedding.firebaseio.com/token.json")
                .put(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d(TAG, "message: " + response.body().string());
            }
        });
    }
}
