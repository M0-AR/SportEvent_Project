package com.example.sportevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

// Todo check https://stackoverflow.com/questions/26621060/display-a-recyclerview-in-fragment
// to use Runnable class
public class EventListFragment extends Fragment {
   private EventAdapter mEventAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_event, container, false);

        ArrayList<Event> joinedEventList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            joinedEventList.add(new Event(R.drawable.image_01, "Event Name: " + i, "Description"));
        }

        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventAdapter = new EventAdapter(joinedEventList);
        mRecyclerView.setAdapter(mEventAdapter);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEventAdapter.setOnEventClickListener(new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(Event event) {
                Toast.makeText(getContext(), "EventFragment : " + event.getTextView1(), Toast.LENGTH_SHORT).show();
                final NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_joinedEventFragment_to_eventDescriptionFragment);
            }
        });

    }


}
