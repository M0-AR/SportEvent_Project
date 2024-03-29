package com.example.sportevent.view.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.sportevent.R;
import com.example.sportevent.utilities.LogicValidation;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.view.ui.MainActivity;

public class LoginEmailFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "LoginEmailFragment";
    private Button login;
    private EditText mEmail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_email, container, false);
        login = (Button) view.findViewById(R.id.emailLogin);
        login.setOnClickListener(this);

        mEmail = view.findViewById(R.id.text_view_login_email);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.show();
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(true);    // to show the bottom bar when we destroy this fragment

    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        MainActivity activity = (MainActivity) getActivity();
//        if (activity != null)
//            activity.hideBottomBar(false);    // to show the bottom bar when
//        // we destroy this fragment
//    }

    @Override
    public void onClick(View view) {
           NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.emailLogin:

                if(validateEmail()) {
                    SampleData.currentUserEmail = mEmail.getText().toString().trim();
                    Log.d(TAG, "onClick: User's email: " + SampleData.currentUserEmail);
                    navController.navigate( LoginEmailFragmentDirections.actionLoginEmailToHomeFragment());
                    break;
                } else break;

        }
    }

    public boolean validateEmail(){
        String email = mEmail.getText().toString().trim();
        if(email.isEmpty()){
            mEmail.setError("Email field Can't be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please enter a valid email");
            return false;
        }else if(!LogicValidation.isEmailAlreadyExists(SampleData.participants, email)){
            mEmail.setError("Your email doesn't exist in system, please enter another email or sign up");
            return false;
        }
        else {
            mEmail.setError(null);
            return true;
        }
    }

}