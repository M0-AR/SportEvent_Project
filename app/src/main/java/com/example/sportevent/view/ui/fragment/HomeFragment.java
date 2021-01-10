package com.example.sportevent.view.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.example.sportevent.utilities.Logic;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.view.adapters.EventAdapter;
import com.example.sportevent.view.adapters.LAYOUT;
import com.example.sportevent.viewModel.EventViewModel;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private EventAdapter mEventAdapter;
    private ArrayList<Event> mEventList;
    private static String mUserEmail;
    private View view = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Todo: it's working but when I go to another app like google Map The view will be not being saved
        if (view != null)
            return view;
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        if (mUserEmail == null) {
            mUserEmail = HomeFragmentArgs.fromBundle(getArguments()).getEmail();
            Log.d(TAG, "onCreateView: User's email: " + mUserEmail);
            initJoinedAndFinishedEventForUser();
        }

        mEventList = SampleData.getSignUpEventList();
        Log.d(TAG, "onCreateView: mEventList" + mEventList);

        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view0);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mEventAdapter = new EventAdapter(getContext(), LAYOUT.HOME_LIST);

        mEventAdapter.setMEventList(mEventList);
        mRecyclerView.setAdapter(mEventAdapter);
        return view;
    }

    private void initJoinedAndFinishedEventForUser() {
         Executor bgThread = Executors.newSingleThreadExecutor(); // Handle for a background
         Handler uiThread = new Handler(Looper.getMainLooper());  // Handle for a foreground
        bgThread.execute(() -> {
            SampleData.joinedEventList = Logic.getJoinedEventForUserByEmail(mEventList, mUserEmail);
            SampleData.finishedEventList = Logic.getFinishedEventForUserByEmail(mEventList, mUserEmail);
        });
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button joinedEventButton = view.findViewById(R.id.joined_event);
        final Button finishedEventButton = view.findViewById(R.id.finished_event);

        final NavController navController = Navigation.findNavController(view);

        joinedEventButton.setOnClickListener(v -> navController.navigate( HomeFragmentDirections.actionHomeFragmentToJoinedEventFragment()));

        finishedEventButton.setOnClickListener(v -> navController.navigate( HomeFragmentDirections.actionHomeFragmentToCompletedEventFragment()));

        mEventAdapter.setOnEventClickListener(event -> {
            Log.d(TAG, "onCreateView: mEventList: setOnEventClickListener: " + event.toString());
            navController.navigate( HomeFragmentDirections.actionHomeFragmentToEventDescriptionSignUpFragment(event));
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
