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
import android.widget.Toast;

public class Login extends Fragment implements View.OnClickListener {
    Button loginUser;
    Button loginOrg;
    Button signup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        loginUser = (Button) view.findViewById(R.id.userLoginButton);
        loginUser.setOnClickListener(this);

        loginOrg = (Button) view.findViewById(R.id.orgLoginButton);
        loginOrg.setOnClickListener(this);

        signup = (Button) view.findViewById(R.id.signupButton);
        signup.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.orgLoginButton:
                // todo Organizer button  first screen
                Toast.makeText(view.getContext(), "Could work next time", Toast.LENGTH_SHORT).show();
                break;
            case R.id.userLoginButton:
                navController.navigate(R.id.action_login_to_loginEmail);
                break;
            case R.id.signupButton:
                Toast.makeText(view.getContext(), "It's working but there is small bug Can't open now", Toast.LENGTH_SHORT).show();
                // todo SignUpButton First screen navController.navigate(R.id.action_login_to_createUser);
                break;
            default:
                break;

        }
    }

}