package com.example.sportevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class CompletedEvents extends AppCompatActivity implements View.OnClickListener {

    RecyclerView eventsRecycler;
    String[] s1;
    String[] s2;
    int[] images = {R.drawable.eventlistexample,
                    R.drawable.eventlistexample,
                    R.drawable.eventlistexample};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_events);

        eventsRecycler = findViewById(R.id.eventsRecycler);

        s1 = getResources().getStringArray(R.array.event_title);
        s2 = getResources().getStringArray(R.array.event_description);

        EventsRecyclerAdapter adapter = new EventsRecyclerAdapter(this, s1, s2, images);
        eventsRecycler.setAdapter(adapter);
        eventsRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View v) {


    }
}



