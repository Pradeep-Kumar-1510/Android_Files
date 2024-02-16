package com.example.firstapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.firstapp.R;

public class RatingFragment extends Fragment {

    private View view;
    private RatingBar ratingBar;
    private Button rateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_rating, container, false);


        ratingBar = view.findViewById(R.id.ratingBar);
        rateButton = view.findViewById(R.id.rateBtn);


        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting the rating value from the rating bar
                float rating = ratingBar.getRating();

                // Display a toast message with the rating
                Toast.makeText(getActivity(), "You rated us: " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}