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
import com.example.sportevent.view.adapters.EventAdapter;
import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.view.adapters.LAYOUT;
import com.example.sportevent.utilities.Constants;

// Todo check https://stackoverflow.com/questions/26621060/display-a-recyclerview-in-fragment
// to use Runnable class
public class EventListFragment extends Fragment {
   private EventAdapter mEventAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_event, container, false);

        //need to check user choice to know which fragment to focus on, crashes the app.. idk
        //int prevID = Navigation.findNavController(view).getPreviousBackStackEntry().getDestination().getId();

        String name = "Software Engineer job description guide";
        String description = "The business environment relies heavily on software for many functions - from automated traffic control systems to complex manufacturing processes, and Software Engineers are pivotal in the development of software that provides real solutions. A Software Engineer needs to address the entire software development lifecycle - to analyse the needs, and then design, test and develop software in order to meet those needs.\n" +
                "\n" +
                "Software Engineer duties and responsibilities of the job\n" +
                "Software Engineers document this process through the use of diagrams and flowcharts, developing computer instructions through the use of algorithms. Given the breadth of the role, a Software Engineer must have a thorough understanding of computer systems, in order to recognise any hardware limitations that could impact software design. A typical Software Engineer job description includes:\n" +
                "\n" +
                "Improving system quality by identifying issues and common patterns, and developing standard operating procedures\n" +
                "Enhancing applications by identifying opportunities for improvement, making recommendations and designing and implementing systems\n" +
                "Maintaining and improving existing codebases and peer review code changes\n" +
                "Liaising with colleagues to implement technical designs\n" +
                "Investigating and using new technologies where relevant\n" +
                "Providing written knowledge transfer material\n" +
                "Software Engineer job qualifications and requirements\n" +
                "A degree in Software Engineering, Computer Science, Mathematics or related fields is essential. Some companies may require expertise in particular high-level programming languages such as C++, Java or Scala.\n" +
                "\n" +
                "As technology develops at an ever increasing pace, it is critical for Software Engineers to stay up to date with the latest developments in hardware, systems and coding.\n" +
                "\n" +
                "As well as formal qualifications, a Software Engineer job description should emphasise an importance towards:\n" +
                "\n" +
                " - A passion for solving problems and providing workable solutions\n" +
                " - Knowledge of algorithms and data structures\n" +
                " - Strong analytical and reasoning skills with an ability to visualise processes and outcomes\n" +
                " - Proficiency in troubleshooting software issues and debugging a large codebase\n" +
                " - Outstanding all-round communication skills and ability to work collaboratively\n" +
                "Robert Half is one of the leading technology recruitment agencies in Australia. Are you looking for an Software Engineer role? View our latest Software Engineer jobs here.\n" +
                "\n" +
                "Looking for an Software Engineer job or IT and Technology specific salary information? Head over to our Software Engineer Salary Guide for insights and trends.";

        ArrayList<Event> joinedEventList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            joinedEventList.add(new Event(Constants.IMAGES[i], i +" : "+ name, description, new Date(), new Date(), new Date(), new Date() ));
        }

        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventAdapter = new EventAdapter(getContext(), LAYOUT.JOINED_EVENT_LIST);
        mEventAdapter.setMEventList(joinedEventList);

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
                navController.navigate( EventListFragmentDirections.actionJoinedEventFragmentToEventDescriptionFragment(event.getImageURL(), event.getEventName(), event.getEventDescription()));
            }
        });

    }

}
