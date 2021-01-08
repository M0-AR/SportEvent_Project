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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        mEventViewModel.getAllEvents().observe(this, new Observer<RequestCall>() {
            @Override
            public void onChanged(RequestCall requestCall) {
                Log.d(TAG, "onChanged: events: " + requestCall.eventList.toString());
                mEventList = requestCall.eventList;
            }
        });

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                {
//
//                }
//            }
//        }, 2000);

    }

    @SuppressLint("FragmentLiveDataObserve")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Todo: it's working but when I go to another app like google Map The view will be not being saved
        if (view != null)
            return view;




        view =  inflater.inflate(R.layout.fragment_home, container, false);




        Log.d(TAG, "onCreateView: mEventList" + mEventList);
        ArrayList<Event> signUpEventList = SampleData.getSignUpEventList();



        Collections.shuffle(signUpEventList);
        sortEventsByClosestDateToToday(signUpEventList);
        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view0);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mEventAdapter = new EventAdapter(getContext(), LAYOUT.HOME_LIST);

        mEventAdapter.setMEventList(signUpEventList); // todo  signUpEventList
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
                Toast.makeText(getContext(), "EventFragment : " + event.getEventName(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreateView: mEventList: " + mEventList.get(0).toString());
                final NavController navController = Navigation.findNavController(view);
                navController.navigate( HomeFragmentDirections.actionHomeFragmentToEventDescriptionSignUpFragment(event));
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
