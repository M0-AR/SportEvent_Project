package com.example.sportevent.view.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.utilities.Constants;
import com.example.sportevent.R;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.viewModel.EventViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventStartFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener{

    private MapView mMapView;
    private Button startEvent;
    private Event mEvent;
    private EventViewModel mEventViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_event, container, false);

        mEvent = EventDescriptionSignUpFragmentArgs.fromBundle(getArguments()).getEvent();
        mEventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        startEvent = view.findViewById(R.id.start_event);
        startEvent.setOnClickListener(this);

        mMapView = view.findViewById(R.id.start_map);
        initGoogleMap(savedInstanceState);

        return view;
    }



    private void initGoogleMap(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(Constants.MAP_VIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(v);
        String buttonText = startEvent.getText()+"";
        if (buttonText.equalsIgnoreCase("Start Event")) {
            startEvent.setText("Finish Event");
            // Todo Try to find better solution
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/Copenhagen/Spain/@47.7297451,-4.5837011,5z/data=!3m1!4b1!4m14!4m13!1m5!1m1!1s0x4652533c5c803d23:0x4dd7edde69467b8!2m2!1d12.5683372!2d55.6760968!1m5!1m1!1s0xc42e3783261bc8b:0xa6ec2c940768a3ec!2m2!1d-3.74922!2d40.463667!3e1"));
            startActivity(i);
        }
        else {
            Executor bgThread = Executors.newSingleThreadExecutor(); // Handle for a background
            Handler uiThread = new Handler(Looper.getMainLooper());  // Handle for a foreground
            bgThread.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
            // TODO: 10/01/2021 Bug: we should close the app or navigate to another app to make
            // TODO: 10/01/2021 Check first if the user close to finish point(around 15 meters far) in race and make it automatic
            // Add to finish event
//            mEvent.getFinishedRaceParticipantsEmails().add(SampleData.currentUserEmail);
//            // Remove from joinedEvent
//            mEvent.getJoinedEventParticipantsEmails().remove(SampleData.currentUserEmail);
//
//            SampleData.addFinishedEventList(mEvent);
//
//            mEventViewModel.createEvent(mEvent, mEvent.getId());
//            mEventViewModel.getAllEvents().observe( this, requestCall -> {
//                SampleData.initFireStoreEventsData(requestCall.eventList);
//            });

            navController.navigate(EventStartFragmentDirections.actionStartEventToEventResultFragment(mEvent));
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("MD"));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


}