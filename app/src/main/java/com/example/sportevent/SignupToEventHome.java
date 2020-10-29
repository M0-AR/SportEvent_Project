package com.example.sportevent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SignupToEventHome extends Fragment {

    private signupToEventsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_to_event_home, container, false);

        ArrayList<SignupToEvenItem> signupToEvenItemArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            signupToEvenItemArrayList.add(new SignupToEvenItem(R.drawable.ic_launcher_foreground, "Title", "Description"));
        }

        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerViewSignup);
        mRecyclerView.setHasFixedSize(true);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new signupToEventsAdapter(signupToEvenItemArrayList);
        mRecyclerView.setAdapter(adapter);


        return view;


    }
}