package com.example.firstapp.services.backgroundService;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;
import com.example.firstapp.activity.MenuActivity;

public class ServiceActivity extends AppCompatActivity {

    //@Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_service);
//        Button btn_Back = findViewById(R.id.btn_Back);
//
//
//        btn_Back.setOnClickListener(v -> {
//            Intent i = new Intent(ServiceActivity.this, MenuActivity.class);
//            startActivity(i);
//            Toast.makeText(ServiceActivity.this, "You are in Home Page", Toast.LENGTH_SHORT).show();
//        });
//
//    }
//
//    public void startService(View view) {
//        startService(new Intent(getBaseContext(), MyService.class));
//    }
//
//    // Method to stop the service
//    public void stopService(View view) {
//        stopService(new Intent(getBaseContext(), MyService.class));
//    }

    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);
        Button btn_Back = findViewById(R.id.btn_Back);


        btnStart.setOnClickListener(v -> startMusicService());

        btnStop.setOnClickListener(v -> stopMusicService());

        btn_Back.setOnClickListener(v -> {
            Intent i = new Intent(ServiceActivity.this, MenuActivity.class);
            startActivity(i);
            Toast.makeText(ServiceActivity.this, "You are in Home Page", Toast.LENGTH_SHORT).show();
        });
    }

    private void startMusicService() {
        if (serviceIntent == null) {
            serviceIntent = new Intent(this, MyService.class);
            startService(serviceIntent);
        }
    }

    private void stopMusicService() {
        if (serviceIntent != null) {
            stopService(serviceIntent);
            serviceIntent = null;
        }
    }
}