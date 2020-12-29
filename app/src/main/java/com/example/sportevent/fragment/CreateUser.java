package com.example.sportevent.fragment;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.sportevent.R;
import com.example.sportevent.data.Participant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Any;

import java.util.HashMap;

public class CreateUser extends Activity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView Navn = (TextView)findViewById(R.id.nameInput);
    private TextView Email = (TextView)findViewById(R.id.emailInput);
    private TextView Address = (TextView)findViewById(R.id.addressInput);
    private TextView Phone = (TextView)findViewById(R.id.phoneInput);

    public void addUser(View view) {
               HashMap data =new  HashMap<String , Any>();
         data.put("name" , Navn.getText().toString());
        data.put("email" , Email.getText().toString());
        data.put("address" , Address.getText().toString());
        data.put("phoneNumber" , Phone.getText().toString());
        db.collection("Participants" +
                "").add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("FIRESTORE", "onSuccess:FIRESTORE ");
                        System.out.println("FIRESTORE success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("FIRESTORE", "ERROR" , e);
                System.out.println("FIRESTORE unsuccess");

            }
        });


    }
}

