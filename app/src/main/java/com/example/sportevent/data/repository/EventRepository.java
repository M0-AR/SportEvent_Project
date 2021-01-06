package com.example.sportevent.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.sportevent.data.model.entities.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.sportevent.utilities.Constants.DB;

public class EventRepository {
    private Application application;
    public MutableLiveData<Event> mEvents;

    public EventRepository(Application application) {
        this.application = application;
        this.mEvents = new MutableLiveData<>();
    }


    public void createEvent(Event event) {
        FirebaseFirestore.getInstance().collection("events")
                .add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(String.valueOf(application.getApplicationContext()), "DocumentSnapshot added with ID: " + documentReference.getId());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(String.valueOf(application.getApplicationContext()), "Error adding document", e);
                    }
                });
    }

    private MutableLiveData<Event> getAllEvents() {

        return null;
    }





}
