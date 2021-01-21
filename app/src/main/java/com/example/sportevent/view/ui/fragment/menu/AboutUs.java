package com.example.sportevent.view.ui.fragment.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sportevent.R;

public class AboutUs extends Fragment {

    private TextView mTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_about_us, container, false);
        mTextView = view.findViewById(R.id.textView);
        String aboutUs = "The app were being developed by: \n\n" +
                "\tMohammad Javad Zamani\t\t\n\tjvdzmn@gmail.com\n\n\n" +
                "\tBashar Khalad Bdawi\t\t\n\tbasharbdewi86@gmail.com\n\n" +
                "\tAndrey Baskakov\t\t\n\tflere2@outlook.dk\n\n\n" +
                "\tMohamad Ashmar\t\t\n\ts176492@student.dtu.dk";
        mTextView.setText(aboutUs);
        return view;
    }
}