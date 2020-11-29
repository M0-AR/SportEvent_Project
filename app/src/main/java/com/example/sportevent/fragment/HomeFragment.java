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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportevent.R;
import com.example.sportevent.data.Event;
import com.example.sportevent.fragment.adapters.EventAdapter;
import com.example.sportevent.fragment.adapters.LAYOUT;
import com.example.sportevent.utilities.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.*;
public class HomeFragment extends Fragment {
    private EventAdapter mEventAdapter;
    View view = null;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Todo: it's working but when I go to another app like google Map The view will be not being saved
        if (view != null)
            return view;

        view =  inflater.inflate(R.layout.fragment_home, container, false);
        String name = "Software ";
        String description = "test tsets test tsetstest tsetstest tsetstest tsetstest tsetstest tsetstest tsets";
        ArrayList<Event> joinedEventList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            //todo we have to consider if the current date is close to the end of the month then we have to increment the month by 1
            joinedEventList.add(new Event(Constants.IMAGES[i], (int)(Math.random()*10+1) +" : "+ name, description,
                                                                                        new Date(2020 , 11 , 15 + i), // JoinStartDate
                                                                                        new Date(2020, 11, 25 + i) ,  // JoinEndDate
                                                                                        new Date(2021 , 11 , 15 + i), // RaceStartDate
                                                                                        new Date(2021, 11, 25 + i))); // JoinEndDate
        }
        Collections.shuffle(joinedEventList);
        sortEventsByClosestDateToToday(joinedEventList);
        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view0);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mEventAdapter = new EventAdapter(getContext(), LAYOUT.HOME_LIST);
       // mEventAdapter = new CreationOfEventAdapter(getContext());
        mEventAdapter.setMEventList(joinedEventList);
        mRecyclerView.setAdapter(mEventAdapter);
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
                navController.navigate(R.id.action_homeFragment_to_completedEventFragment);
            }
        });


        mEventAdapter.setOnEventClickListener(new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(Event event) {
                Toast.makeText(getContext(), "EventFragment : " + event.getEventName(), Toast.LENGTH_SHORT).show();
                final NavController navController = Navigation.findNavController(view);
                navController.navigate( HomeFragmentDirections.actionHomeFragmentToEventDescriptionFragment(event.getImageURL(), event.getEventName(), event.getEventDescription()));
            }
        });
    }

    public ArrayList<Event> sortEventsByClosestDateToToday(ArrayList<Event> events){
        ArrayList<Event> sortedEvents = events;
        for (int i = 0; i < events.size(); i++) {
            Event min = events.get(i);
            int index = i;
            for (int j = i + 1; j < events.size(); j++) {
                Event event = events.get(j);
                if (min.getJoinStartDate().after(event.getJoinStartDate())) { // "Date1 is after Date2"
                    min = event;
                    index = j;
                }
            }
            if (i != index) {
                events.set(index, events.get(i));
                events.set(i, min);
            }
        }
        return sortedEvents;
    }
}
