package com.example.sportevent.view.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.example.sportevent.utilities.Constants;
import com.example.sportevent.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EventStartFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    private static final String API_KEY = "AIzaSyBnR4ViXl40S9SvXdCt4g2lGg8jWr5ATA0";
    private static final int PERMISSIONS_FINE_LOCATIONS = 99;
    private MapView mMapView;
    private Button startEvent;
    private TextView lat, lon, speed, distance, timer, locationTrack, address;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch locUpdate, gps;

    // Google API for location services
    FusedLocationProviderClient fusedLocationProviderClient;

    LocationRequest locationRequest;
    LocationCallback locationCallBack;

    Location currentLocation;
    LatLng origin, destination;

    // I use this to move location data into the map methods
    GoogleMap mGoogleMap;

    // I use the handler and runnable to end tracking with the switch so app doesn't crash when navigating to results
    Handler handler = new Handler();
    Runnable myRunnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_event, container, false);

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
        origin = new LatLng(0, 0);

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
        // uncheck before finishing event or app will crash in <= 4s
        locUpdate = view.findViewById(R.id.locationSwitch);
        locUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locUpdate.isChecked()) {
                    // tracking begins
                    final int delay = 4000;
                    handler.postDelayed(myRunnable = new Runnable() {
                        public void run() {
                            startLocationUpdates();
                            handler.postDelayed(this, delay);
                        }
                    }, delay);
                } else {
                    // tracking ends
                    stopLocationUpdates();
                    handler.removeCallbacks(myRunnable);
                }
            }
        });

        updateGPS();

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void startLocationUpdates() {

        locationTrack.setText("Location is being tracked");
        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity()
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_FINE_LOCATIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    updateGPS();
                }
                else {
                    Toast.makeText(this.getActivity(), "This app requires permission in order to work", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
        }
    }

    private void updateGPS(){
        // get permissions from user to track GPS
        // get current location from fused client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity());

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this.getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // permissions granted
                    currentLocation = location;
                    origin = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());

                    updateUI(location);
                    onPause();
                    onResume();
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

    @SuppressLint("SetTextI18n")
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
        }
        else
            navController.navigate(EventStartFragmentDirections.actionStartEventToEventResultFragment());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        double Lat = 0, Lon = 0;

        if (currentLocation != null) {
            Lat = currentLocation.getLatitude();
            Lon = currentLocation.getLongitude();
        }

        // current location
        LatLng latLng = new LatLng(Lat, Lon);
        mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("Du er her"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(7));

        // destination
        mGoogleMap.addMarker(new MarkerOptions().position(destination).title("Destination"));

    }

    @Override
    public void onResume() {
        super.onResume();

        if (mGoogleMap != null) {
            mGoogleMap.clear();

            // current location marker
            double Lat, Lon;

            Lat = currentLocation.getLatitude();
            Lon = currentLocation.getLongitude();
            LatLng latLng = new LatLng(Lat, Lon);
            mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("Du er her"));
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(14));

            // destination marker
            mGoogleMap.addMarker(new MarkerOptions().position(destination).title("Destination"));

        }

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