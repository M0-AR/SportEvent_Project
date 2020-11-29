package com.example.sportevent.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sportevent.NavGraphDirections;
import com.example.sportevent.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        navController = Navigation.findNavController( this, R.id.nav_host_fragment_container);


        appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.login, R.id.closestEvent, R.id.eventResultFragment).setDrawerLayout(drawerLayout).build(); //R.id.drawer_layout
        Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar,navController, appBarConfiguration);


        NavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);

        BottomNavigationView  bottomNavigationView = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

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
        //Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}


