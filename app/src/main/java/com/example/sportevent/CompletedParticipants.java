package com.example.sportevent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompletedParticipants extends Fragment implements View.OnClickListener {


    RecyclerView participantsRecycler;
    String s1[], s2[], s3[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.completed_participants, container, false);

        participantsRecycler = view.findViewById(R.id.participantRecycler);

        s1 = getResources().getStringArray(R.array.participants);
        s2 = getResources().getStringArray(R.array.partici_nr);
        s3 = getResources().getStringArray(R.array.partici_time);

        CompletedParticipantsRecyclerAdapter adapter = new CompletedParticipantsRecyclerAdapter(s1, s2, s3);

        participantsRecycler.setAdapter(adapter);
        participantsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onClick(View v) {


    }
}