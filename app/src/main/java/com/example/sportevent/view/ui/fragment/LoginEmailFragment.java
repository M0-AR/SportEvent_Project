package com.example.sportevent.view.ui.fragment;

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

import com.example.sportevent.R;
import com.example.sportevent.view.ui.MainActivity;

public class LoginEmailFragment extends Fragment implements View.OnClickListener {
    Button login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_email, container, false);
        login = (Button) view.findViewById(R.id.emailLogin);
        login.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
           NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.emailLogin:
                navController.navigate( LoginEmailFragmentDirections.actionLoginEmailToHomeFragment() );
                break;
        }
    }

}