package com.example.sportevent.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.process.RequestCall;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.sportevent.utilities.Constants.DB;

public class EventRepository {
    private static final String TAG = "EventRepository";
    private Application application;
    public MutableLiveData<Event> mEvents;
    public MutableLiveData<RequestCall> mutableLiveData;
    final ArrayList<Event> eventList = new ArrayList<>();

    public EventRepository(Application application) {
        this.application = application;
        this.mEvents = new MutableLiveData<>();
        this.mutableLiveData = new MutableLiveData<>();
    }


    public void createEvent(Event event, int id) {
        DB.collection("events").document(String.valueOf(id)).set(event);
    }

    public MutableLiveData<RequestCall> getAllEvents() {
        final RequestCall requestCall = new RequestCall();
        readData(eventList -> {
            Log.d(TAG, "getAllEvents: FirebaseCallBack: " + eventList);
            requestCall.eventList = (ArrayList<Event>) eventList;
            mutableLiveData.postValue(requestCall);
        });
        return mutableLiveData;
    }

    private void readData(final FirebaseCallBack firebaseCallBack) {
        DB.collection("events")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                         //   Log.d(String.valueOf(application.getApplicationContext()), document.getId() + " => " + document.getData());
                            Event event = document.toObject(Event.class);
                         //   Log.d(TAG, "onComplete: getAllEvents -> Event: " + (event != null ? event.getEventName() : null));
                            if(event != null) {
                               String s = event.getEventDescription().replace( "/n", "\n");
                               event.setEventDescription(s);
                            }
                            eventList.add(event);
                        }
                        Log.d(TAG, "readData: eventList: " + eventList);
                        firebaseCallBack.onCallBack(eventList);
                    }else {
                        Log.w(String.valueOf(application.getApplicationContext()), "Error getting events.", task.getException());
                    }
                });
    }

    private interface FirebaseCallBack {
       void onCallBack(List<Event> eventList);
    }

}
