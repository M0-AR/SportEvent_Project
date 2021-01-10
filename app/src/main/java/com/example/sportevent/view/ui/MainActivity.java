package com.example.sportevent.view.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sportevent.R;
import com.example.sportevent.data.model.process.RequestCall;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.viewModel.EventViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


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

        EventViewModel viewModel = new ViewModelProvider(this).get(EventViewModel.class);
        viewModel.getAllEvents().observe( this, new Observer<RequestCall>() {
            @Override
            public void onChanged(RequestCall requestCall) {
                SampleData.initFireStoreEventsData(requestCall.eventList);
                //CacheManager.cacheEvents(requestCall.eventList);
            }
        });

        //SampleData.initData();
        Log.d(TAG, "onCreate: create events");
        // TODO: 07/01/2021 Delete this later
//        mEventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
//        for (int i = 0; i < 8; i++) {
//            mEventViewModel.createEvent(SampleData.signUpEventList.get(i), SampleData.signUpEventList.get(i).getId());
//        }


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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // Todo: There is a small problem here, when ClosetEventOrHome is being access from other fragments, so headFragment located under them in the stack. How to make them first layer in stack
         if (item.getItemId() == R.id.contactUs) {
            // todo 13:14 https://www.youtube.com/watch?v=WWgNCPu8MeQ&list=PLrnPJCHvNZuCamMFswP597mUF-whXoAA6&index=6
            return true;
        }

        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAndRemoveTask();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}


