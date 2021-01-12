package com.example.sportevent.view.ui.fragment;


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
import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.PersonalEventData;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.utilities.Logic;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.view.adapters.ParticipantAdapter2;

import java.util.ArrayList;

public class ParticipantListFinishedFragment extends Fragment {
    private ParticipantAdapter2 mEventAdapter;
    private Event mEvent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_participant_finished, container, false);

        mEvent = EventDescriptionSignUpFragmentArgs.fromBundle(getArguments()).getEvent();

        ArrayList<Participant> participantList = Logic.getParticipantsWhoFinishedEvent(SampleData.participants, mEvent);


        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventAdapter = new ParticipantAdapter2(participantList);
        mRecyclerView.setAdapter(mEventAdapter);


        return view;
    }
}