package com.example.sportevent.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.model.entities.Result;
import com.example.sportevent.data.model.process.RequestCall;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.sportevent.utilities.Constants.DB;

public class ParticipantRepository {
    public static final String TAG="ParticipantRepository" ;
    private final MutableLiveData<RequestCall> mutableLiveData;
    ArrayList<Participant> participantList = new ArrayList<>();

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
        readData(participantList -> {
            Log.d(TAG, "getAllEvents: FirebaseCallBack: " + participantList);
            requestCall.participantList = (ArrayList<Participant>) participantList;
            mutableLiveData.postValue(requestCall);
        });
        return mutableLiveData;
    }

    private void readData(final FirebaseCallBack firebaseCallBack) {
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
                        firebaseCallBack.onCallBack(participantList);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    private interface FirebaseCallBack {
        void onCallBack(List<Participant> participantList);
    }
}
