package com.example.sportevent.view.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sportevent.R;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.model.process.RequestCall;
import com.example.sportevent.utilities.Logic;
import com.example.sportevent.utilities.LogicValidation;
import com.example.sportevent.utilities.SampleData;
import com.example.sportevent.view.ui.MainActivity;
import com.example.sportevent.viewModel.EventViewModel;
import com.example.sportevent.viewModel.ParticipantViewModel;

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
    public void onStart() {
        super.onStart();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.show();
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(true);    // to show the bottom bar when we destroy this fragment
    }


    @Override
    public void onClick(View view) {
        NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.registerButton:
                if (validateName() && validateEmail() && validateAddress() && validatePhoneNumber() ){
                    Participant participant = new Participant(0,
                            mName.getText().toString().trim(),
                            mEmail.getText().toString().trim(),
                            mAddress.getText().toString().trim(),
                            mPhoneNumber.getText().toString().trim());
                    ParticipantViewModel participantViewModel = new ViewModelProvider(this).get(ParticipantViewModel.class);
                    participantViewModel.createParticipant(participant, participant.getEmail());
                    SampleData.currentUserEmail = mEmail.getText().toString();
                    Log.d(TAG, "onClick: User's email: " + mEmail.getText().toString());
                    navController.navigate(SignUpFragmentDirections.actionCreateUserToHomeFragment());
                    break;
                } else break;

        }
    }
    private boolean validatePhoneNumber(){
        if( mPhoneNumber.getText().toString().trim().isEmpty()){
            mPhoneNumber.setError("Phone number field Can't be empty");
            return false;
        }else if(!Patterns.PHONE.matcher( mPhoneNumber.getText().toString().trim()).matches()){
            mPhoneNumber.setError("Please enter a valid phone number");
            return false;
        }else {
            mPhoneNumber.setError(null);
            return true;
        }
    }


    private boolean validateName(){
        if(mName.getText().toString().trim().isEmpty()){
            mName.setError("Name field Can't be empty");
            return false;
        }else {
            mName.setError(null);
            return true;
        }
    }

    private boolean validateAddress(){
        if(mAddress.getText().toString().trim().isEmpty()){
            mAddress.setError("Address field Can't be empty");
            return false;
        }else {
            mAddress.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String email = mEmail.getText().toString().trim();
        if(email.isEmpty()){
            mEmail.setError("Email field Can't be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please enter a valid email");
            return false;
        }else if(LogicValidation.isEmailAlreadyExists(SampleData.participants, email)){
            mEmail.setError("This email is already exists");
            return false;
        }
        else {
            mEmail.setError(null);
            return true;
        }
    }
}