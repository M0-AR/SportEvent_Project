package com.example.sportevent_test.ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import com.example.sportevent_test.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        navController = Navigation.findNavController( this, R.id.nav_host_fragment_container);


        appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.login).setDrawerLayout(drawerLayout).build(); //R.id.drawer_layout
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


}


