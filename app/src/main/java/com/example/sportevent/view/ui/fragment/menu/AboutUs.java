package com.example.sportevent.view.ui.fragment.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sportevent.R;
import com.example.sportevent.view.ui.fragment.EventDescriptionSignUpFragmentArgs;

public class AboutUs extends Fragment {

    private TextView mTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_about_us, container, false);
        mTextView = view.findViewById(R.id.textView);
        String aboutUs = "The app were being develop by: \n\n" +
                "\tMohammad Javad Zamani\t\t\nTest@gmail.com\n\n" +
                "\tBashar Khalad Bdawi\t\t\nbasharbdewi86@gmail.com\n\n" +
                "\tAndrey Baskakov\t\t\nTest@gmail.com\n\n" +
                "\tMohamad Ashmar\t\t\ns176492@student.dtu.dk";
        mTextView.setText(aboutUs);
        return view;
    }
}