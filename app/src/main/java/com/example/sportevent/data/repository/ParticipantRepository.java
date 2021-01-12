package com.example.sportevent.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sportevent.data.model.entities.Participant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.example.sportevent.utilities.Constants.DB;

public class ParticipantRepository {

    public static final String TAG="ParticipantRepository" ;

    public ParticipantRepository(Application application) {

    }

    public void insertParticipant(Participant participant){
        DB.collection("Participants").document("LA")
                .set(participant)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }
}
