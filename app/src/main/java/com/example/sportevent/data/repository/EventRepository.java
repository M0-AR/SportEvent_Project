package com.example.sportevent.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.model.process.RequestCall;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import static com.example.sportevent.utilities.Constants.DB;

public class EventRepository {
    private Application application;
    public MutableLiveData<Event> mEvents;
    public MutableLiveData<RequestCall> mutableLiveData;

    public EventRepository(Application application) {
        this.application = application;
        this.mEvents = new MutableLiveData<>();
        this.mutableLiveData = new MutableLiveData<>();
    }


    public void createEvent(Event event, int id) {
        DB.collection("events").document(String.valueOf(id)).set(event);

//        DB.collection("events")
//                .add(event)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(String.valueOf(application.getApplicationContext()), "Event added with ID: " + documentReference.getId());
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(String.valueOf(application.getApplicationContext()), "Error adding Event", e);
//                    }
//                });
    }

    public MutableLiveData<RequestCall> getAllEvents() {
        final HashMap<String, Object> objectHashMap = new HashMap<>();
        final ArrayList<String> idList = new ArrayList<>();
        RequestCall requestCall = new RequestCall();


        DocumentReference docRef = DB.collection("events").document("LA");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                // Event event = documentSnapshot.toObject(Event.class);
//                 String eventDescription = documentSnapshot.getString("eventDescription");
//                 double id = documentSnapshot.getDouble("id");
//                 String imageURL = documentSnapshot.getString("imageURL");;
//                 String eventName  = documentSnapshot.getString("eventName");
//                 String location  = documentSnapshot.getString("location");
//               // String joinedEventParticipants  = documentSnapshot.get("joinedEventParticipants");
//                String finishedRaceParticipants  = documentSnapshot.getString("finishedRaceParticipants");
//                 ArrayList<String> joinedEventParticipants  = (ArrayList<String>) documentSnapshot.get("joinedEventParticipants");;
//                 //ArrayList<Participant> finishedRaceParticipants  = documentSnapshot.getString("finishedRaceParticipants");
//                 Date joinStartDate  = documentSnapshot.getDate("joinStartDate");
//                 Date joinEndDate  = documentSnapshot.getDate("joinEndDate");
//                 Date raceStartDate  = documentSnapshot.getDate("raceStartDate");
//                 Date raceEndDate  = documentSnapshot.getDate("raceEndDate");
                // todo !!!!!!!!!!!!!!!!!!! not working Event event = documentSnapshot.toObject(Event.class);
            }
        });

//        DB.collection("events")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//                                Log.d(String.valueOf(application.getApplicationContext()), document.getId() + " => " + document.getData());
//                                objectHashMap.put(document.getId(), document.getData());
//                                idList.add(document.getId());
//
//                                Event event = (Event) parseHashMapToObject(document.getData(), Event.class);
//
//                            }
//                        }else {
//                            Log.w(String.valueOf(application.getApplicationContext()), "Error getting events.", task.getException());
//                        }
//
//                    }
//                });

        requestCall.idList = idList;
        requestCall.hashMap = objectHashMap;
        mutableLiveData.postValue(requestCall);
        return mutableLiveData;
    }

    private Object parseHashMapToObject(Map map, Class cls) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(map);
        return gson.fromJson(jsonString, cls);
    }




//    static Object parseHashMapToObject(HashMap map, Class cls) {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.create();
//        String jsonString = gson.toJson(map);
//        return gson.fromJson(jsonString, cls);
//    }
}
