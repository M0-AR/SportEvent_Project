package com.example.sportevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class JoinedEventFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.joined_event_fragment, container, false);

        ArrayList<JoinedEventItem> joinedEventList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            joinedEventList.add(new JoinedEventItem(R.drawable.ic_launcher_foreground, "Event Name: " + i, "Description"));
        }

        // 1. Get a reference to recyclerView
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        // 2. Set layoutManger
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 3. Create an adapter
        RecyclerView.Adapter mAdapter = new JoinedEventAdapter(joinedEventList);
        // 4. Set adapter
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
