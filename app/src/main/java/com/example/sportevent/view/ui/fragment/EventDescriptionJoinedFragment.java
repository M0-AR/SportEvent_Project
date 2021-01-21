package com.example.sportevent.view.ui.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.sportevent.R;
import com.example.sportevent.data.model.entities.Event;

import java.util.Date;

public class EventDescriptionJoinedFragment extends Fragment implements View.OnClickListener{
    private Event mEvent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_description_event, container, false);

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
        textView2.setOnLongClickListener(v -> {
            Intent googleMaps= new Intent(Intent.ACTION_VIEW, Uri.parse(mEvent.getLocation()));
            startActivity(googleMaps);
            return false;
        });

        final Button participantList = view.findViewById(R.id.participant_list);
        participantList.setOnClickListener(this);
        final Button startEvent = view.findViewById(R.id.event_start_description);
        startEvent.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(v);;
        switch (v.getId()) {
            case R.id.participant_list:
                navController.navigate(EventDescriptionJoinedFragmentDirections.actionEventDescriptionFragmentToParticipant(mEvent));
                break;
            case R.id.event_start_description:
                    Date currentDate = new Date();
                    if(currentDate.after(mEvent.getRaceStartDate()) && currentDate.before(mEvent.getRaceEndDate())) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        }
                        navController.navigate(EventDescriptionJoinedFragmentDirections.actionEventDescriptionFragmentToStartEvent(mEvent));
                    } else {
                        if (currentDate.before(mEvent.getRaceStartDate())) {
                            makeAlertDialog("This event will start on " + mEvent.getRaceStartDate());
                        } else {
                            makeAlertDialog("This event has finished on " + mEvent.getRaceEndDate());
                        }
                    }
                break;
            default:
                break;
        }
    }

    private void makeAlertDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("OPS...")
                .setMessage(s)
                .setPositiveButton("Ok", (dialog, which) -> {
                });
        builder.create().show();
    }

}
