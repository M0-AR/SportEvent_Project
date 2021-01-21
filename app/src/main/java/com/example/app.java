package com.example;

import android.app.Application;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

public class app extends Application {
    private static final String TAG = "app";
    @Override
    public void onCreate() {
        Log.d(TAG, "Initialized cache");
//        if (!CacheManager.DISK_CACHE_INITIALIZED) {
//            CacheManager.initializeCache(this);
//        }
        Log.d(TAG, "Make reference to Firestore");
        FirebaseFirestore.getInstance();
        super.onCreate();
    }

}
