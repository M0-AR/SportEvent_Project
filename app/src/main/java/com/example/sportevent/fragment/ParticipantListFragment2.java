package com.example.sportevent.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportevent.R;
import com.example.sportevent.data.PersonalEventData;
import com.example.sportevent.data.Participant;
import com.example.sportevent.fragment.adapters.ParticipantAdapter2;

import java.util.ArrayList;

public class ParticipantListFragment2 extends Fragment {
    private ParticipantAdapter2 mEventAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_participant2, container, false);


        ArrayList<Participant> participantList = new ArrayList<>();
        ArrayList<PersonalEventData> personalEventData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            participantList.add(new Participant(i, "MD", "@gmail.com: "+i, "DTU: "+i, "00 00 00 0"+i));
            personalEventData.add(new PersonalEventData(participantList.get(i).getEmail(), "Event: "+i, i));
        }

        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventAdapter = new ParticipantAdapter2(participantList, personalEventData);
        mRecyclerView.setAdapter(mEventAdapter);


        return view;
    }
}