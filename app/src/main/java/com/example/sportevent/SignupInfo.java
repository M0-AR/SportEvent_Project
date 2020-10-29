package com.example.sportevent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SignupInfo extends Fragment implements View.OnClickListener{

    Button deltagereButt, tilmeldButt;
    TextView eventName, eventInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.signup_info, container, false);

        deltagereButt = view.findViewById(R.id.deltagereButton);
        tilmeldButt = view.findViewById(R.id.tilmeldButton);
        eventName = view.findViewById(R.id.eventName);
        eventInfo = view.findViewById(R.id.eventInfo);

        deltagereButt.setOnClickListener(this);
        tilmeldButt.setOnClickListener(this);
        eventName.setOnClickListener(this);
        eventInfo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v == deltagereButt) {

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_container, new SignupParticipants())
                    .addToBackStack(null)
                    .commit();
        }
    }
}