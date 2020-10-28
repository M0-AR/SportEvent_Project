package com.example.sportevent;

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

public class EventDescriptionFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_description_event, container, false);

        // Todo: Maybe: size of image 0.3 and textView 2 is 0.7 try to hide action bar getActivity().getActionBar().hide();

        ImageView imageView = view.findViewById(R.id.imageView);
        // Todo: why the program suggest this assert getArguments() != null;
        imageView.setImageResource(getArguments().getInt("image"));

        TextView textView1 = view.findViewById(R.id.textView1);
        textView1.setText(getArguments().getString("eventName"));

        TextView textView2 = view.findViewById(R.id.textView2);
        textView2.setText(getArguments().getString("eventDescription"));
        textView2.setMovementMethod(new ScrollingMovementMethod());

        final Button participantList = view.findViewById(R.id.participant_list);
        participantList.setOnClickListener(this);
        final Button startEvent = view.findViewById(R.id.event_start);
        startEvent.setOnClickListener(this);

        return view;
    }


   /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Button participantList = view.findViewById(R.id.participant_list);
        final NavController navControllerStart = Navigation.findNavController(view);
        participantList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navControllerStart.navigate(R.id.action_homeFragment_to_joinedEventFragment);
            }
        });

        final Button startEvent = view.findViewById(R.id.event_start);
        final NavController navController = Navigation.findNavController(view);
        startEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_joinedEventFragment);
            }
        });

    }*/

    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(v);;
        switch (v.getId()) {
            case R.id.participant_list:
                navController.navigate(R.id.action_eventDescriptionFragment_to_participant);
                break;
            case R.id.event_start:
                navController.navigate(R.id.action_eventDescriptionFragment_to_startEvent);
                break;
            default:
                break;
        }
    }
}
