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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.sportevent.R;
import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.utilities.SampleData;

public class ClosestEvent extends Fragment implements View.OnClickListener{

    private Event mEvent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_closest_event, container, false);

        // TODO: 05/01/2021 Get the first element in the list which has the closest event
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
        NavController navController = Navigation.findNavController(v);;
        switch (v.getId()) {
            case R.id.participant_list:
                //  navController.navigate(R.id.action_eventDescriptionFragment1_to_participantListFragment1);
                break;
            case R.id.event_signup:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                        .setTitle("Join The Event")
                        .setMessage("Confirm for joining this event !")
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO: 05/01/2021 Show an animation
                                // TODO: 05/01/2021 Show toast message if the user is already sing up to the event OR find another solution
                                SampleData.joinedEventList.add(mEvent);
                                SampleData.signUpEventList.remove(mEvent); // TODO: 05/01/2021 the delete here should be from the data base

                            }
                        });
                builder.create().show();
                break;

            default:
                break;
        }
    }
}
