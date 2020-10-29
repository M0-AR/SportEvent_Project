package com.example.sportevent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Login extends Fragment implements View.OnClickListener {
    Button loginUser;
    Button loginOrg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        loginUser = (Button) view.findViewById(R.id.userLoginButton);
        loginUser.setOnClickListener(this);

        loginOrg = (Button) view.findViewById(R.id.orgLoginButton);
        loginOrg.setOnClickListener(this);



        return view;
    }

    public void onClick(View view) {
        NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.userLoginButton:
                navController.navigate(R.id.action_login_to_loginEmail2);
                break;
            case R.id.orgLoginButton:
                navController.navigate(R.id.action_login_to_loginEmail2);
                break;
            default:
                break;

        }
    }

}