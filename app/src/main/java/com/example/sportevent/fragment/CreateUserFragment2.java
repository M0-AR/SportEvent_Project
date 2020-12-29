package com.example.sportevent.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sportevent.R;
import com.example.sportevent.data.Participant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Any;

import java.util.HashMap;

public class CreateUserFragment2 extends Fragment implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button register;
    EditText Name,Email,Address,Phone;
    Participant participant;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_create_user_fragment2, container, false);

        Name = (EditText) view.findViewById(R.id.nameInput);
        Email = (EditText) view.findViewById(R.id.emailInput);
        Address = (EditText) view.findViewById(R.id.addressInput);
        Phone = (EditText) view.findViewById(R.id.phoneInput);

        register = (Button) view.findViewById(R.id.registerButton);

        register.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {



        NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.registerButton:
                {

                    participant.setName(Name.getText().toString());
                    participant.setEmail(Email.getText().toString());
                    participant.setAddress(Address.getText().toString());
                    participant.setPhoneNumber(Phone
                            .getText().toString());
                    HashMap data =new  HashMap<String , Any>();
                    data.put("name" , Name.getText().toString());
                    data.put("email" , Email.getText().toString());
                    data.put("address" , Address.getText().toString());
                    data.put("phoneNumber" , Phone.getText().toString());
                    db.collection("Participant")
                            .add(data)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("FIREBASE SUCCESS", "Docu" +
                                            "mentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("FIREBASE UNSUCCESS", "Error adding document", e);
                                }
                            });
                navController.navigate(R.id.action_createUserFragment2_to_homeFragment);
                break;
                }
        }
    }
}