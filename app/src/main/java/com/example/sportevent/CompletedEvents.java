package com.example.sportevent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompletedEvents extends Fragment implements View.OnClickListener{

    RecyclerView eventsRecycler;
    String[] s1;
    String[] s2;
    int[] images = {R.drawable.eventlistexample,
            R.drawable.eventlistexample,
            R.drawable.eventlistexample};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.completed_events, container, false);

        eventsRecycler = view.findViewById(R.id.eventsRecycler);

        s1 = getResources().getStringArray(R.array.event_title);
        s2 = getResources().getStringArray(R.array.event_description);

        EventsRecyclerAdapter adapter = new EventsRecyclerAdapter(s1, s2, images);
        eventsRecycler.setAdapter(adapter);
        eventsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onClick(View v) {

    }

}