package com.example.sportevent.utilities;

import android.content.Context;
import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.example.sportevent.data.model.entities.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheManager {
    public static final String TAG = "CacheManager";
    public static boolean DISK_CACHE_INITIALIZED = false;
    public static boolean DISK_CACHE_DIRTY = false;
    public static List<Event> MEMORY_LIST_CACHE = new ArrayList<>();

    public static void initializeCache(Context context) {
        try {
            Reservoir.init(context, 2048); // In bytes todo Try to extended if need it
            DISK_CACHE_INITIALIZED = true;
        } catch (IOException e) {
            Log.e(TAG, "initializeCache: Reservoir failed");
            DISK_CACHE_INITIALIZED = false;
        }
    }


}
