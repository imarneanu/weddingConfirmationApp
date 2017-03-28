package com.imarneanu.alexiuliawedding.services;

import com.google.firebase.iid.FirebaseInstanceId;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;

/**
 * Created by iulia on 24/03/2017.
 */

public class DeleteTokenService extends IntentService {
    public static final String TAG = DeleteTokenService.class.getSimpleName();

    public DeleteTokenService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
            Log.d(TAG, "Getting new token");
            FirebaseInstanceId.getInstance().getToken();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
