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


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
import io.sentry.android.core.SentryAndroid;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private BottomNavigationView  bottomNavigationView;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSentry();




        EventViewModel viewModel = new ViewModelProvider(this).get(EventViewModel.class);
        viewModel.getAllEvents().observe( this, requestCall -> {
            initSentryExecption("onCreate: get all eventList from cloud firestore: " + requestCall.message);
            Log.d(TAG, "onCreate: eventList: " + requestCall.eventList);
            SampleData.signUpEventList = requestCall.eventList;
            Logic.sortEventStartDateInAscendingOrder(SampleData.signUpEventList);
        });

        ParticipantViewModel participantViewModel = new ViewModelProvider(this).get(ParticipantViewModel.class);
        participantViewModel.getAllParticipants().observe( this, requestCall -> {
            initSentryExecption("onCreate: get all participants from cloude firestore: " + requestCall.message);
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

    public void initSentry(){
        boolean EMULATOR = Build.PRODUCT.contains("SDK") || Build.MODEL.contains("Emulator");
        if(!EMULATOR) {
            Context context = this.getApplicationContext();
            String sentryDSN = "https://a22caf113b674befa0cfc6583f54df0b@o508120.ingest.sentry.io/5600164";
            SentryAndroid.init(context , options -> {
                options.setDsn(sentryDSN);
                options.setDebug(true);
                options.setSampleRate(1.0);
                options.setAttachStacktrace(true);
                options.setBeforeSend(((event, hint) -> {
                    if(event.getTag("TAG") != null) return null;
                    else
                        return event;
                }));
            });
        }
    }

    public void initSentryExecption(String message){
        try {
            throw new Exception(message);
        } catch (Exception e) {
            Sentry.captureException(e);
        }
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


