package com.example.firstapp.services.foregroundService;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;

public class ThreadFragment extends Fragment {
    private static final int PERMISSION_REQUEST_CODE = 100;

    private ToggleButton toggleButton;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thread, container, false);

        toggleButton = view.findViewById(R.id.toggleButton);

        // Setting initial state of the toggle button
        toggleButton.setChecked(false); // "Off" state

        // Setting click listener for the toggle button
        toggleButton.setOnClickListener(v -> {
            // Handle toggle button clicks
            if (toggleButton.isChecked()) {
                // Toggle button is checked, check permissions and start service if granted
                checkAndStartService();
            } else {
                // Toggle button is unchecked, stop service
                stopForegroundService();
            }
        });

        return view;
    }

    // Check permissions and start the service if granted
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private void checkAndStartService() {
        if (hasPermissions()) {
            startForegroundService();
        } else {
            requestPermissions();
        }
    }


    // Check if permissions are granted
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private boolean hasPermissions() {
        return ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.FOREGROUND_SERVICE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;
    }

    // Request necessary permissions from the user
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private void requestPermissions() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{
                        Manifest.permission.FOREGROUND_SERVICE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                },
                PERMISSION_REQUEST_CODE);
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                startForegroundService();
            } else {
                Toast.makeText(requireContext(), "Permissions denied, service cannot be started", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void startForegroundService() {
        Intent serviceIntent = new Intent(requireContext(), ForegroundService.class);
        requireContext().startForegroundService(serviceIntent);
    }


    private void stopForegroundService() {
        Intent serviceIntent = new Intent(requireContext(), ForegroundService.class);
        requireContext().stopService(serviceIntent);
    }
}

/*
 *  below is an older implementation doesn't run on api 34 and above
 */

//package com.example.firstapp.services.foregroundService;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ToggleButton;
//
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//
//import com.example.firstapp.R;
//
//
//public class ThreadFragment extends Fragment {
//    private ToggleButton toggleButton;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_thread, container, false);
//
//        toggleButton = view.findViewById(R.id.toggleButton);
//
//        // Setting initial state of the toggle button
//        toggleButton.setChecked(false); // "Off" state
//
//        // Setting click listener for the toggle button
//        toggleButton.setOnClickListener(v -> {
//            // Handle toggle button clicks
//            if (toggleButton.isChecked()) {
//                // Toggle button is checked, start the service
//                startForegroundService();
//            } else {
//                stopForegroundService();
//            }
//        });
//
//        return view;
//    }
//
//    private void startForegroundService() {
//        // Start the foreground service
//        Intent serviceIntent = new Intent(requireContext(), ForegroundService.class);
//        requireContext().startForegroundService(serviceIntent);
//
//    }
//
//    private void stopForegroundService() {
//        // Stop the foreground service
//        Intent serviceIntent = new Intent(requireContext(), ForegroundService.class);
//        requireContext().stopService(serviceIntent);
//    }
//}