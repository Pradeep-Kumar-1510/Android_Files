package com.example.firstapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;
import com.example.firstapp.activity.ViewFeedbackActivity;

public class FormFragment extends Fragment {

    private EditText feedbackEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);


        feedbackEditText = view.findViewById(R.id.feedbackEditText);
        Button submitFeedbackButton = view.findViewById(R.id.submitFeedbackButton);


        submitFeedbackButton.setOnClickListener(v -> {

            String feedback = feedbackEditText.getText().toString();


            if (!feedback.isEmpty()) {
                 Intent intent = new Intent(getActivity(), ViewFeedbackActivity.class);
                 intent.putExtra("feedback",feedback);
                 startActivity(intent);
                Toast.makeText(getActivity(), "Feedback submitted: ", Toast.LENGTH_SHORT).show();
                feedbackEditText.getText().clear();
            } else {
                Toast.makeText(getActivity(), "Please enter your feedback", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
