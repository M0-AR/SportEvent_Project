package com.example.sportevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompletedInfo extends AppCompatActivity implements View.OnClickListener {

    Button backButt, homeButt, deltagereButt;
    TextView eventName, eventInfo, eventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_info);

        backButt = findViewById(R.id.backButton);
        homeButt = findViewById(R.id.homeButton);
        deltagereButt = findViewById(R.id.deltagereButton);
        eventName = findViewById(R.id.eventName);
        eventInfo = findViewById(R.id.eventInfo);
        eventDescription = findViewById(R.id.eventDescript);

        backButt.setOnClickListener(this);
        homeButt.setOnClickListener(this);
        deltagereButt.setOnClickListener(this);
        eventName.setOnClickListener(this);
        eventInfo.setOnClickListener(this);
        eventDescription.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == deltagereButt) {
            Intent intent = new Intent(this, CompletedParticipants.class);
            startActivity(intent);
        }
        if (v == backButt) {
            Intent intent = new Intent(this, CompletedEvents.class);
            startActivity(intent);
        }
    }
}