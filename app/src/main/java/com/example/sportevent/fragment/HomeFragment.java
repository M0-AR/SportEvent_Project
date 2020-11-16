package com.example.sportevent.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportevent.R;
import com.example.sportevent.data.Event;
import com.example.sportevent.fragment.adapters.CreationOfEventAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private CreationOfEventAdapter mCreationOfEventAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        String name = "Software ";
        String description = "test tsets";
        ArrayList<Event> joinedEventList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            joinedEventList.add(new Event(R.drawable.image_01, i +" : "+ name, description));
        }

        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view0);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mCreationOfEventAdapter = new CreationOfEventAdapter(joinedEventList);
        mRecyclerView.setAdapter(mCreationOfEventAdapter);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button joinedEventButton = view.findViewById(R.id.joined_event);
        final Button finishedEventButton = view.findViewById(R.id.finished_event);

        final NavController navController = Navigation.findNavController(view);

        joinedEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_joinedEventFragment);
            }
        });

        finishedEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_participantListFragment2);
            }
        });


        mCreationOfEventAdapter.setOnEventClickListener(new CreationOfEventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(Event event) {
                Toast.makeText(getContext(), "EventFragment : " + event.getEventName(), Toast.LENGTH_SHORT).show();
                final NavController navController = Navigation.findNavController(view);
                Bundle bundle = new Bundle();
                bundle.putInt("image", event.getImageResource());
                bundle.putString("eventName", event.getEventName());
                bundle.putString("eventDescription", event.getEventDescription());
                navController.navigate(R.id.action_joinedEventFragment_to_eventDescriptionFragment, bundle);
            }
        });

    }



}
