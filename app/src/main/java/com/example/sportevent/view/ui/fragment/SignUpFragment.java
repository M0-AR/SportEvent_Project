package com.example.sportevent.view.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sportevent.R;
import com.example.sportevent.view.ui.MainActivity;

public class SignUpFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "SignUpFragment";
    private Button register;
    private EditText mName, mEmail, mAddress, mPhoneNumber;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_create_user, container, false);

        register = view.findViewById(R.id.registerButton);
        register.setOnClickListener(this);

        mName = view.findViewById(R.id.user_signup_name);
        mEmail = view.findViewById(R.id.user_singup_email);
        mAddress = view.findViewById(R.id.user_signup_address);
        mPhoneNumber = view.findViewById(R.id.user_signup_phoneNumber);

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
            case R.id.registerButton:
                Log.d(TAG, "onClick: User's email: " + mEmail.getText().toString());
                navController.navigate(SignUpFragmentDirections.actionCreateUserToHomeFragment(mEmail.getText().toString()));
                break;
        }
    }
}