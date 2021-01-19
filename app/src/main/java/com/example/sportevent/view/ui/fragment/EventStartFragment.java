package com.example.sportevent.view.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Result;
import com.example.sportevent.utilities.Constants;
import com.example.sportevent.R;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.utilities.StopWatchWorker;
import com.example.sportevent.view.ui.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

import static com.example.sportevent.utilities.Constants.SIX_HOURS;
import static com.example.sportevent.utilities.StopWatchWorker.WORK_NUMBER_KEY;

public class EventStartFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    private static final int PERMISSIONS_FINE_LOCATIONS = 99;
    public static final String TAG = "EventStartFragment";
    private MapView mMapView;
    private Button startEvent;
    private TextView lat, lon, speed, distance, timer, locationTrack, address;
    private Switch locUpdate, gps;

    private Event mEvent;


    // Google API for location services
    FusedLocationProviderClient fusedLocationProviderClient;

    LocationRequest locationRequest;
    LocationCallback locationCallBack;

    Location currentLocation;
    LatLng destination;

    // I use this to move location data into the map methods
    GoogleMap mGoogleMap;


    private UUID mCountRequestId;
    WorkManager workManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_event, container, false);

        mEvent = EventDescriptionSignUpFragmentArgs.fromBundle(getArguments()).getEvent();

        startEvent = view.findViewById(R.id.start_event);
        startEvent.setOnClickListener(this);

        mMapView = view.findViewById(R.id.start_map);
        initGoogleMap(savedInstanceState);

        lat = view.findViewById(R.id.tempLAT);
        lon = view.findViewById(R.id.tempLON);
        speed = view.findViewById(R.id.speedEvent);
        distance = view.findViewById(R.id.distanceEvent);
        timer = view.findViewById(R.id.timerEvent);
        address = view.findViewById(R.id.tempAddress);

        // insert latlng values for destination here
        // TODO: 1/15/2021 make an easier way for organizer to create destinations (if time)
        destination = new LatLng(0,0);

        gps = view.findViewById(R.id.gpsSwitch);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gps.isChecked()) {
                    // most accurate
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                } else {
                    // save battery
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                }
            }
        });

        // set properties of LocationRequest
        locationRequest = new LocationRequest();
        // default location check
        locationRequest.setInterval(20000);
        // fastest location check
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                // save location
                Location location = locationResult.getLastLocation();
                updateUI(location);
            }
        };

        locationTrack = view.findViewById(R.id.tempTRACKING);
        locUpdate = view.findViewById(R.id.locationSwitch);
        locUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locUpdate.isChecked()) {
                    // tracking begins
                    startLocationUpdates();
                } else {
                    // tracking ends
                    stopLocationUpdates();
                }
            }
        });

        updateGPS();

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void startLocationUpdates() {

        locationTrack.setText("Location is being tracked");
        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    @SuppressLint("SetTextI18n")
    private void stopLocationUpdates() {

        locationTrack.setText("Location is not being tracked");
        lat.setText("N/A");
        lon.setText("N/A");

        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
    }



    private void updateGPS(){
        // get permissions from user to track GPS
        // get current location from fused client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity());

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this.getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // permissions granted
                    if (location != null) {
                        currentLocation = location;
                        updateUI(location);

                        //onPause();
                        //onResume();
                    }
                }
            });
        }
        else {
            // permission not granted
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATIONS);
            }
        }
    }

    private void updateUI(Location location) {

        Geocoder geocoder = new Geocoder(EventStartFragment.this.getActivity());

        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            // getAddressLine probably, others like ZIP code available etc.
            address.setText(addresses.get(0).getAddressLine(0));
        }
        catch (Exception e){
            address.setText("Error");
        }

        lat.setText(String.valueOf(location.getLatitude()));
        lon.setText(String.valueOf(location.getLongitude()));

        // cannot test, my pc does not have speed sensor, probably works!
        if (location.hasSpeed()) {
            speed.setText(String.valueOf(location.getSpeed()));
        }
        else {
            speed.setText("N/A");
        }

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
            // implement some kind of timer
            timer.setText("soon");


            initStopWatchWorker();

            Intent googleMaps= new Intent(Intent.ACTION_VIEW, Uri.parse(mEvent.getLocation()));
            startActivity(googleMaps);
        }
        else {

            // TODO: 19/01/2021 No one is working I can't stop the worker (it's will wait until the loop terminated)
           /* workManager.cancelWorkById(mCountRequestId);
            workManager.cancelAllWork();
            workManager.cancelAllWorkByTag("Count");*/
            workManager.cancelAllWorkByTag("Count");

            int seconds = SampleData.countOneByOneSecond % 60;
            int minuets = (seconds/60)%60;
            int hours = (seconds/60)/60;
            int placeNumber = mEvent.getFinishedRaceParticipantsEmails().size()+1;
            String medal = (placeNumber == 1) ? "Gold": (placeNumber == 2) ? "Silver": (placeNumber == 3) ? "Bronze": "SecondBronze";
            // TODO: 19/01/2021 Distance Logic: get latitude and longitude when the user click on finish race button then calculate the destination
            // Example: I was 15 km far from the beginning point when I click on finish button, but the event's distance is 50.0 km(mEvent.getDistance())
            Result result = new Result(mEvent.getDistance(), hours, minuets, seconds, placeNumber, medal);

            navController.navigate(EventStartFragmentDirections.actionStartEventToEventResultFragment(mEvent, result));
        }
    }

    private void initStopWatchWorker() {
        Data data = new Data.Builder()
                .putInt(WORK_NUMBER_KEY, SIX_HOURS)
                .build();

        OneTimeWorkRequest countRequest = new OneTimeWorkRequest.Builder(StopWatchWorker.class)
                .setInputData(data)
                .addTag("Count")
                .build();

        mCountRequestId = countRequest.getId();
        workManager = WorkManager.getInstance(getContext());
        workManager.enqueue(countRequest);

        workManager.getWorkInfosByTagLiveData("Count").observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                for (WorkInfo workInfo : workInfos) {
                    Log.d(TAG, "onChanged: Work status: " + workInfo.getState());
                }
            }
        });
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