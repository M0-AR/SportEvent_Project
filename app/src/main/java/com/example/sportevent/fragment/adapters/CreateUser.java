package com.example.sportevent.fragment.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.sportevent.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Any;

import java.util.HashMap;

public class CreateUser extends Activity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView Navn = (TextView)findViewById(R.id.navnInput);
    private TextView Email = (TextView)findViewById(R.id.emailInput);
    private TextView Address = (TextView)findViewById(R.id.addressInput);
    private TextView Phone = (TextView)findViewById(R.id.phoneInput);

    public void addUser(View view) {
         HashMap data =new  HashMap<String , Any>();
         data.put("name" , Navn.getText().toString());
        data.put("email" , Email.getText().toString());
        data.put("address" , Address.getText().toString());
        data.put("phoneNumber" , Phone.getText().toString());
        db.collection("Participant").add(data);

    }
}

