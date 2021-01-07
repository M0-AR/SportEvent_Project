package com.example;

import android.app.Application;
import android.util.Log;

import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.viewModel.EventViewModel;

public class app extends Application {
    private static final String TAG = "app";
//    private EventViewModel mEventViewModel;
    @Override
    public void onCreate() {
        super.onCreate();
        // todo Not working
        Log.d(TAG, "onCreate: create events");
//        mEventViewModel = new EventViewModel(this);
//        for (int i = 0; i < 8; i++) {
//            mEventViewModel.createEvent(SampleData.signUpEventList.get(i));
//        }
    }

}
