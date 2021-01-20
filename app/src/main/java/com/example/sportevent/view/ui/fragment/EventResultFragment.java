package com.example.sportevent.view.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sportevent.R;
import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.model.entities.Result;
import com.example.sportevent.utilities.Logic;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.viewModel.EventViewModel;
import com.example.sportevent.viewModel.ParticipantViewModel;

public class EventResultFragment extends Fragment implements  View.OnClickListener{
    private Button mShareResult;
    private Event mEvent;
    private Result mResult;
    private EventViewModel mEventViewModel;
    private ParticipantViewModel mParticipantViewModel;
    private TextView mTime, mDistance, mPlaceNumber, mMedal, mName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_event, container, false);

        mEvent = EventResultFragmentArgs.fromBundle(getArguments()).getEvent();
        mResult = EventResultFragmentArgs.fromBundle(getArguments()).getResult();

        mEventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        mParticipantViewModel = new ViewModelProvider(this).get(ParticipantViewModel.class);
        customizeData();

        mTime = view.findViewById(R.id.result_time);
        mTime.setText("Time:\t\t"+ mResult.getHours() + ":" + mResult.getMinutes() + ":" + mResult.getSeconds());
        mDistance = view.findViewById(R.id.result_distance);
        mDistance.setText("Distance:\t\t" + mResult.getDistance() + " km");
        mPlaceNumber = view.findViewById(R.id.result_place_number);
        mPlaceNumber.setText("Place number:\t\t" + mResult.getPlaceNumber());
        mMedal = view.findViewById(R.id.result_medal);
        mMedal.setText("Medal:\t\t" + mResult.getMedal());
        mName = view.findViewById(R.id.result_name);
        Participant participant = Logic.getParticipantByEmail(SampleData.participants, SampleData.currentUserEmail);
        mName.setText("Name:\t\t" + participant.getName());

        mShareResult = view.findViewById(R.id.share_result);
        mShareResult.setOnClickListener(this);


        return view;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void customizeData() {
        mEvent.getFinishedRaceParticipantsEmails().add(SampleData.currentUserEmail);
        // Remove from joinedEvent
        mEvent.getJoinedEventParticipantsEmails().remove(SampleData.currentUserEmail);

        SampleData.finishedEventList.add(mEvent);

        mEventViewModel.createEvent(mEvent, mEvent.getId());
        mEventViewModel.getAllEvents().observe( this, requestCall -> {
            SampleData.signUpEventList = requestCall.eventList;
        });

        // Add result to the user
        // TODO: 14/01/2021 Make the correct result
        mParticipantViewModel.createParticipantResult(SampleData.currentUserEmail, mEvent.getId(), mResult);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.share_result) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Sport Event: The result would be here next time.");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }
    }
}