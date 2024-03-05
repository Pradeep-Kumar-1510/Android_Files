package com.example.firstapp.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.POJOClass.MyService;
import com.example.firstapp.R;
import com.example.firstapp.activity.MenuActivity;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Button btn_Back = findViewById(R.id.btn_Back);


        btn_Back.setOnClickListener(v -> {
            Intent i = new Intent(ServiceActivity.this, MenuActivity.class);
            startActivity(i);
            Toast.makeText(ServiceActivity.this, "You are in Home Page", Toast.LENGTH_SHORT).show();
        });

    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

}