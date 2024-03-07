package com.example.firstapp.services.foregroundService;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;


public class ThreadFragment extends Fragment {
    private ToggleButton toggleButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thread, container, false);

        toggleButton = view.findViewById(R.id.toggleButton);

        // Setting initial state of the toggle button
        toggleButton.setChecked(false); // "Off" or "Unchecked" state

        // Setting click listener for the toggle button
        toggleButton.setOnClickListener(v -> {
            // Handle toggle button clicks
            if (toggleButton.isChecked()) {
                // Toggle button is checked, start the service
                startForegroundService();
            } else {
                stopForegroundService();
            }
        });

        return view;
    }

    private void startForegroundService() {
        // Start the foreground service
        Intent serviceIntent = new Intent(requireContext(), ForegroundService.class);
        ContextCompat.startForegroundService(requireContext(), serviceIntent);

    }

    private void stopForegroundService() {
        // Stop the foreground service
        Intent serviceIntent = new Intent(requireContext(), ForegroundService.class);
        requireContext().stopService(serviceIntent);
    }
}