package com.example.sportevent.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sportevent.R;

public class CreateUserFragment2 extends Fragment implements View.OnClickListener {
    Button register;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_create_user_fragment2, container, false);

        register = (Button) view.findViewById(R.id.registerButton);
        register.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.registerButton:
                navController.navigate(R.id.action_createUser_to_homeFragment);
                break;


        }
    }
}