package com.example.sportevent.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sportevent.R;
import com.example.sportevent.ui.MainActivity;

public class CreateUserFragment2 extends Fragment {
    Button register;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_create_user_fragment2, container, false);

        register = view.findViewById(R.id.registerButton);
        // https://developer.android.com/guide/navigation/navigation-navigate#java
        register.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_createUser_to_homeFragment, null));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(false);    // to show the bottom bar when
        // we destroy this fragment
    }
}