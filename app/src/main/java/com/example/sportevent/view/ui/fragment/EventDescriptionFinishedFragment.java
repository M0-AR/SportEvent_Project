package com.example.sportevent.view.ui.fragment;

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

public class EventDescriptionFinishedFragment extends Fragment implements View.OnClickListener{
    private Event mEvent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_description_event2, container, false);

        mEvent = EventDescriptionSignUpFragmentArgs.fromBundle(getArguments()).getEvent();

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

        final Button result = view.findViewById(R.id.event_result_description);
        result.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(v);;
        if (v.getId() == R.id.participant_list) {
            navController.navigate(EventDescriptionFinishedFragmentDirections.actionEventDescriptionFragment2ToParticipantListFragment2(mEvent));
        } else if (v.getId() == R.id.event_result_description) {
            // TODO: 14/01/2021 on back pressed
            navController.navigate(EventDescriptionFinishedFragmentDirections.actionEventDescriptionFragment2ToEventResultFragment(mEvent));
        }
    }
}
