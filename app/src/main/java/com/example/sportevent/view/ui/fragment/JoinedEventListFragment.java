package com.example.sportevent.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportevent.R;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.view.adapters.EventAdapter;
import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.view.adapters.LAYOUT;
import com.example.sportevent.utilities.Constants;

// Todo check https://stackoverflow.com/questions/26621060/display-a-recyclerview-in-fragment
// to use Runnable class
public class JoinedEventListFragment extends Fragment {
   private EventAdapter mEventAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_event, container, false);


        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventAdapter = new EventAdapter(getContext(), LAYOUT.JOINED_EVENT_LIST);
        mEventAdapter.setMEventList(SampleData.getJoinedEventList());

        mRecyclerView.setAdapter(mEventAdapter);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEventAdapter.setOnEventClickListener(new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(Event event) {
                Toast.makeText(getContext(), "EventFragment : " + event.getEventName(), Toast.LENGTH_SHORT).show();
                final NavController navController = Navigation.findNavController(view);
                navController.navigate( JoinedEventListFragmentDirections.actionJoinedEventFragmentToEventDescriptionFragment(event));
            }
        });

    }

}
