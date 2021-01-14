package com.example.sportevent.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.model.entities.Result;
import com.example.sportevent.data.model.process.RequestCall;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.sportevent.utilities.Constants.DB;

public class ParticipantRepository {
    public static final String TAG="ParticipantRepository" ;
    private final MutableLiveData<RequestCall> mutableLiveData;
    ArrayList<Participant> participantList = new ArrayList<>();
    HashMap<String,Result> participantResultList = new HashMap<>();

    // TODO: 12/01/2021 Not sure that we need application
    public ParticipantRepository(Application application) {
        this.mutableLiveData = new MutableLiveData<>();
    }

    public void insertParticipant(Participant participant, String participantEmail){
        DB.collection("Participants").document(participantEmail)
                .set(participant)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written! participant"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing document: participant", e));
    }

    public void insertParticipantResult(String participantEmail, int eventId, Result result) {
        DB.collection("Participants").document(participantEmail)
                .collection("result").document(String.valueOf(eventId))
                .set(result)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written! participant's result"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing document: participant's result", e));
    }


    public MutableLiveData<RequestCall> getAllParticipants() {
        final RequestCall requestCall = new RequestCall();
        readParticipantsData(participantList -> {
            Log.d(TAG, "getAllParticipants: FirebaseCallBack: " + participantList);
            requestCall.participantList = (ArrayList<Participant>) participantList;
            mutableLiveData.postValue(requestCall);
        });
        return mutableLiveData;
    }

    private void readParticipantsData(final FirebaseCallBackList firebaseCallBackList) {
        DB.collection("Participants")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            Participant participant = document.toObject(Participant.class);
                            participantList.add(participant);
                        }
                        Log.d(TAG, "getAllParticipants: participantList: " + participantList);
                        firebaseCallBackList.onCallBack(participantList);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }


    public MutableLiveData<RequestCall> getParticipantResult(String participantEmail) {
        final RequestCall requestCall = new RequestCall();
        readParticipantResultData(participantEmail, map -> {
            Log.d(TAG, "getAllParticipantsResult: FirebaseCallBack: " + map);
            requestCall.participantResultList = (HashMap<String, Result>) map;
            mutableLiveData.postValue(requestCall);
        });
        return mutableLiveData;
    }

    private void readParticipantResultData(String participantEmail, final FirebaseCallBackMap firebaseCallBackMap) {
        if (participantEmail.isEmpty()) return; // TODO: 14/01/2021 Delete this when the app is finish
        DB.collection("Participants").document(participantEmail)
                .collection("result")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            Result result = document.toObject(Result.class);
                            participantResultList.put(document.getId(), result);
                        }
                        Log.d(TAG, "getAllParticipants: participantList: " + participantList);
                        firebaseCallBackMap.onCallBack(participantResultList);
                    } else {
                        Log.d(TAG, "Error getting participantResultList documents: ", task.getException());
                    }
                });
    }


    private interface FirebaseCallBackList<T> {
        void onCallBack(List<T> list);
    }

    private interface FirebaseCallBackMap{
        void onCallBack(Map map);
    }
}
