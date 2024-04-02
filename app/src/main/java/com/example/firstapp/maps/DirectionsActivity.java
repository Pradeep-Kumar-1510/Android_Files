package com.example.firstapp.maps;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;
import com.example.firstapp.databinding.ActivityDirectionsBinding;

public class DirectionsActivity extends AppCompatActivity {
    private ActivityDirectionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRoute.setOnClickListener(v -> {
            String startLocation = binding.currentLocation.getText().toString().trim();
            String endLocation = binding.destination.getText().toString().trim();
            if (startLocation.isEmpty() || endLocation.isEmpty()) {
                Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
            } else {
                getDirections(startLocation, endLocation);
            }
        });
    }

    private void getDirections(String startLocation, String endLocation) {
        try {
            Uri uri = Uri.parse(getString(R.string.maps_link) + Uri.encode(startLocation) + "&destination=" + Uri.encode(endLocation));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(getString(R.string.maps_package));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            Uri uri = Uri.parse(getString(R.string.maps_download_link));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}