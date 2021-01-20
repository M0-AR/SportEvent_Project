package com.example.sportevent.view.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sportevent.R;
import com.example.sportevent.utilities.Logic;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.viewModel.EventViewModel;
import com.example.sportevent.viewModel.ParticipantViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import io.sentry.Sentry;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private BottomNavigationView  bottomNavigationView;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sentry
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }

        setContentView(R.layout.activity_main);

        EventViewModel viewModel = new ViewModelProvider(this).get(EventViewModel.class);
        viewModel.getAllEvents().observe( this, requestCall -> {
            Log.d(TAG, "onCreate: eventList: " + requestCall.eventList);
            SampleData.signUpEventList = requestCall.eventList;
            Logic.sortEventsByClosestDateToToday(SampleData.signUpEventList);
        });

        ParticipantViewModel participantViewModel = new ViewModelProvider(this).get(ParticipantViewModel.class);
        participantViewModel.getAllParticipants().observe( this, requestCall -> {
            Log.d(TAG, "onCreate: participantList: " + requestCall.participantList);
            SampleData.participants = requestCall.participantList;
        });


        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        navController = Navigation.findNavController( this, R.id.nav_host_fragment_container);


        appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.login, R.id.closestEvent, R.id.eventResultFragment).setDrawerLayout(drawerLayout).build(); //R.id.drawer_layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar,navController, appBarConfiguration);


        NavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public void hideBottomBar(boolean isHidden){
        bottomNavigationView.setVisibility(isHidden ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAndRemoveTask();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

}


