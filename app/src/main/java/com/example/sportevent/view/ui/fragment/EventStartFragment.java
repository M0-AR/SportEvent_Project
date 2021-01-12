package com.example.sportevent.view.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportevent.utilities.Constants;
import com.example.sportevent.R;
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

import java.util.List;

public class EventStartFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    private static final int PERMISSIONS_FINE_LOCATIONS = 99;
    private MapView mMapView;
    private Button startEvent;
    private TextView lat, lon, speed, locationTrack, address, timer;
    Switch locUpdate, gps;

    //Google API for location services
    FusedLocationProviderClient fusedLocationProviderClient;

    boolean updateLocation = false;

    LocationRequest locationRequest;
    LocationCallback locationCallBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_event, container, false);

        startEvent = view.findViewById(R.id.start_event);
        startEvent.setOnClickListener(this);

        mMapView = view.findViewById(R.id.start_map);
        //initGoogleMap(savedInstanceState);

        lat = view.findViewById(R.id.tempLAT);
        lon = view.findViewById(R.id.tempLON);
        speed = view.findViewById(R.id.speedEvent);
        address = view.findViewById(R.id.tempAddress);

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
        // how often default location checks
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
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    @SuppressLint("SetTextI18n")
    private void stopLocationUpdates() {

        locationTrack.setText("Location is not being tracked");
        lat.setText("No tracking");
        lon.setText("No tracking");

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

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this.getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // permissions granted
                    updateUI(location);
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
        // Cannot test, my pc does not have speed sensor
        if (location.hasSpeed()) {
            speed.setText(String.valueOf(location.getSpeed()));
        }
        else {
            speed.setText("N/A");
        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }


    /*
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
        else
            navController.navigate(EventStartFragmentDirections.actionStartEventToEventResultFragment());
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
     */

}