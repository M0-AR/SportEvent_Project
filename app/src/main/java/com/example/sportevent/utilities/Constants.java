package com.example.sportevent.utilities;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Constants {
    public static final FirebaseFirestore DB = FirebaseFirestore.getInstance();

    public static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    public static final String CACHE_KEY_EVENTS = "CACHE_KEY_EVENTS";

    public static final int SIX_HOURS = 2160;
}
