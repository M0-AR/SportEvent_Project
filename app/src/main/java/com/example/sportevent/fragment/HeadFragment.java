package com.example.sportevent.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sportevent.R;
import com.example.sportevent.ui.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HeadFragment extends Fragment implements View.OnClickListener {
    Button loginUser;
    Button signup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginUser =  view.findViewById(R.id.userLoginButton);
        loginUser.setOnClickListener(this);

        signup = view.findViewById(R.id.buttonsignUp);
        signup.setOnClickListener(this);

        return view;
    }


    public void onClick(View view) {
        NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.userLoginButton:
                navController.navigate(R.id.action_login_to_loginEmail);
                break;
            case R.id.buttonsignUp:
                navController.navigate(R.id.action_login_to_createUser);
                break;
            default:
              break;

        }
    }

}