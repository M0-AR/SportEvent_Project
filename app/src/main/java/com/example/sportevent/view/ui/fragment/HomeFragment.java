package com.example.sportevent.view.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportevent.R;
import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.process.RequestCall;
import com.example.sportevent.utilities.CacheManager;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.view.adapters.EventAdapter;
import com.example.sportevent.view.adapters.LAYOUT;
import com.example.sportevent.viewModel.EventViewModel;

import java.util.ArrayList;
import java.util.*;
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private EventViewModel mEventViewModel;
    private EventAdapter mEventAdapter;
    private ArrayList<Event> mEventList;
    View view = null;


    @SuppressLint("FragmentLiveDataObserve")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Todo: it's working but when I go to another app like google Map The view will be not being saved

        if (view != null)
            return view;

        view =  inflater.inflate(R.layout.fragment_home, container, false);

        mEventList = SampleData.getSignUpEventList();
        Log.d(TAG, "onCreateView: mEventList" + mEventList);

        // TODO: 08/01/2021
       // Collections.shuffle(signUpEventList);
        // sortEventsByClosestDateToToday(signUpEventList);
        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view0);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mEventAdapter = new EventAdapter(getContext(), LAYOUT.HOME_LIST);

        mEventAdapter.setMEventList(mEventList); // todo  signUpEventList
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
                navController.navigate( HomeFragmentDirections.actionHomeFragmentToJoinedEventFragment());
            }
        });

        finishedEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate( HomeFragmentDirections.actionHomeFragmentToCompletedEventFragment());
            }
        });


        mEventAdapter.setOnEventClickListener(new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(Event event) {
                Log.d(TAG, "onCreateView: mEventList: setOnEventClickListener: " + event.toString());
                final NavController navController = Navigation.findNavController(view);
                navController.navigate( HomeFragmentDirections.actionHomeFragmentToEventDescriptionSignUpFragment(event));
            }
        });
    }

    private void bindData() {
        Objects.requireNonNull(CacheManager.getCollectionOfEvents()).observe(this, (List<Event> events) -> {
            if (events == null || events.size() == 0 || CacheManager.DISK_CACHE_DIRTY) {
               // getEvents();
            } else {

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
                if (min.getJoinStartDate() == null || event.getJoinStartDate() == null) continue;
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
