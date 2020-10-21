package com.example.sportevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupParticipants extends AppCompatActivity implements View.OnClickListener {

    RecyclerView participantsRecycler;
    String s1[], s2[];
    Button backButt, homeButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_participants);

        participantsRecycler = findViewById(R.id.participantRecycler);

        s1 = getResources().getStringArray(R.array.participants);
        s2 = getResources().getStringArray(R.array.partici_nr);

        SignupParticipantsRecyclerAdapter adapter = new SignupParticipantsRecyclerAdapter(this, s1, s2);

        participantsRecycler.setAdapter(adapter);
        participantsRecycler.setLayoutManager(new LinearLayoutManager(this));

        backButt = findViewById(R.id.backButton);
        homeButt = findViewById(R.id.homeButton);

        backButt.setOnClickListener(this);
        homeButt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == backButt) {

            Intent intent = new Intent(this, SignupInfo.class);
            startActivity(intent);
        }

    }
}