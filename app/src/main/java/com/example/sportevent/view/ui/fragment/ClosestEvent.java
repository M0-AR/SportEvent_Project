package com.example.sportevent.view.ui.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.sportevent.R;
import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.utilities.Logic;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.viewModel.EventViewModel;

import java.util.Date;

public class ClosestEvent extends Fragment implements View.OnClickListener{
    public static final String TAG = "ClosestEvent";
    private Event mEvent;
    private EventViewModel mEventViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_closest_event, container, false);

        mEventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        mEvent = SampleData.signUpEventList.get(0);

        ImageView imageView = view.findViewById(R.id.imageView);
        Glide.with(this)
                .asBitmap()
                .load(mEvent.getImageURL())
                .into(imageView);

        TextView textView1 = view.findViewById(R.id.textView1);
        textView1.setText(mEvent.getEventName());

        TextView textView2 = view.findViewById(R.id.textView2);
        textView2.setText(mEvent.getEventDescription());
        textView2.setMovementMethod(new ScrollingMovementMethod());

        final Button participantList = view.findViewById(R.id.participant_list);
        participantList.setOnClickListener(this);
        final Button startEvent = view.findViewById(R.id.event_signup);
        startEvent.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(v);
        switch (v.getId()) {
            case R.id.participant_list:
                navController.navigate(ClosestEventDirections.actionClosestEventToParticipantListSignUpFragment(mEvent));
                break;
            case R.id.event_signup:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                        .setTitle("Join The Event")
                        .setMessage("Confirm for joining this event !")
                        .setNegativeButton("no", (dialog, which) -> {

                        }).setPositiveButton("yes", (dialog, which) -> {
                            Date currentDate = new Date();
                            if (Logic.isUserAlreadySignUpToEvent(mEvent, SampleData.currentUserEmail)) {
                                makeAlertDialog("OPS...","Already sign up");
                            } else if (currentDate.before(mEvent.getJoinStartDate())) {
                                makeAlertDialog("OPS...","Sign up will be on " + mEvent.getJoinStartDate());
                            } else if (currentDate.after(mEvent.getJoinEndDate())) {
                                makeAlertDialog("OPS...","Sign up has finished on " + mEvent.getJoinEndDate());
                            } else {
                                makeAlertDialog("HOORAY", "Congratulations you just sign up");
                                Toast.makeText(getContext(), "Congratulations you just sign up", Toast.LENGTH_LONG).show();
                                mEvent.getJoinedEventParticipantsEmails().add(SampleData.currentUserEmail);
                                mEventViewModel.createEvent(mEvent, mEvent.getId());
                                mEventViewModel.getAllEvents().observe( this, requestCall -> {
                                    SampleData.signUpEventList = requestCall.eventList;
                                });
                                SampleData.joinedEventList.add(mEvent);
                            }
                        });
                builder.create().show();
                break;
            default:
                break;
        }
    }

    private void makeAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", (dialog, which) -> {
                });
        builder.create().show();
    }
}
