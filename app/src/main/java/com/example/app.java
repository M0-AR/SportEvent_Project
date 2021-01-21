package com.example;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sportevent.data.model.process.RequestCall;
import com.example.sportevent.utilities.CacheManager;
import com.example.sportevent.viewModel.EventViewModel;
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
