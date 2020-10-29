package com.example.sportevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Button eventList, joinedEvent, finishedEvent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        eventList = view.findViewById(R.id.event_list);
        joinedEvent = view.findViewById(R.id.joined_event);
        finishedEvent = view.findViewById(R.id.finished_event);

        eventList.setOnClickListener(this);
        joinedEvent.setOnClickListener(this);
        finishedEvent.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v == eventList) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_container, new SignupInfo())
                    .addToBackStack(null)
                    .commit();
        }

        if(v == finishedEvent) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_container, new CompletedParticipants())
                    .addToBackStack(null)
                    .commit();
        }


    }
}
