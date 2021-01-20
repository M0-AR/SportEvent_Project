package com.example.sportevent.utilities;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.anupcowkur.reservoir.ReservoirPutCallback;
import com.example.sportevent.data.model.entities.Event;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for storing data in cash memory in specific bytes specified by
 * the developer according to his future view and data that will be created in the future
 * like fex. creating a new event or operate new users to reduce the usage of the
 * main memory resources and pressure, and store the data locally at the same time
 * which provides speed access to the data and save the time.
 */

public class CacheManager {
    public static final String TAG = "CacheManager";
    public static boolean DISK_CACHE_INITIALIZED = false;
    public static boolean DISK_CACHE_DIRTY = false;
    public static List<Event> MEMORY_LIST_CACHE = new ArrayList<>();

    public static void initializeCache(Context context) {
        try {
            Reservoir.init(context, 20048); // In bytes
            DISK_CACHE_INITIALIZED = true;
        } catch (IOException e) {
            Log.e(TAG, "initializeCache: Reservoir failed");
            DISK_CACHE_INITIALIZED = false;
        }
    }

    public static void cacheEvents(List<Event> events) {
        if (DISK_CACHE_INITIALIZED) {
            Reservoir.putAsync(Constants.CACHE_KEY_EVENTS, events, new ReservoirPutCallback() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "onSuccess: cacheEvents()");
                    DISK_CACHE_DIRTY = false;
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e(TAG, "onFailure: Putting cache to disk" + e.toString());
                }
            });
        }
    }

    public static LiveData<List<Event>> getCollectionOfEvents() {
        final MutableLiveData<List<Event>> eventLiveData = new MutableLiveData<>();
        if (!DISK_CACHE_INITIALIZED) return null;
        Type resultType = new TypeToken<List<Event>>() {}.getType();
        Reservoir.getAsync(Constants.CACHE_KEY_EVENTS, resultType, new ReservoirGetCallback<List<Event>>() {
            @Override
            public void onSuccess(List<Event> eventList) {
                eventLiveData.setValue(eventList);
            }
            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: Async refresh from disk");
                eventLiveData.setValue(null);
            }
        });
        return eventLiveData;
    }

}
