package com.example.sportevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupInfo extends AppCompatActivity implements View.OnClickListener {

    Button backButt, homeButt, deltagereButt, tilmeldButt;
    TextView eventName, eventInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_info);

        backButt = findViewById(R.id.backButton);
        homeButt = findViewById(R.id.homeButton);
        deltagereButt = findViewById(R.id.deltagereButton);
        tilmeldButt = findViewById(R.id.tilmeldButton);
        eventName = findViewById(R.id.eventName);
        eventInfo = findViewById(R.id.eventInfo);

        backButt.setOnClickListener(this);
        homeButt.setOnClickListener(this);
        deltagereButt.setOnClickListener(this);
        tilmeldButt.setOnClickListener(this);
        eventName.setOnClickListener(this);
        eventInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == deltagereButt) {

            Intent intent = new Intent(this, SignupParticipants.class);
            startActivity(intent);
        }

    }
}