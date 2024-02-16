package com.example.firstapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.broadcast_files.FirstBroadcastReceiver;
import com.example.firstapp.R;
import com.example.firstapp.broadcast_files.SecondBroadcastReceiver;


//To run this activity , follow the below mentioned steps.
//login(use admin for both username and password)->click menu button-> choose broadcast from the options menu->click the start button to start the broadcast.


public class BroadcastActivity extends AppCompatActivity {

    private FirstBroadcastReceiver firstReceiver;
    private SecondBroadcastReceiver secondReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        Button startBtn = findViewById(R.id.startBtn);
        Button backBtn = findViewById(R.id.backButton);

        //registering the receivers

        if (firstReceiver == null) {
            firstReceiver = new FirstBroadcastReceiver();
            IntentFilter firstIntentFilter = new IntentFilter("com.example.FIRST_BROADCAST");
            registerReceiver(firstReceiver, firstIntentFilter, Context.RECEIVER_EXPORTED);
        }

        if (secondReceiver == null) {
            secondReceiver = new SecondBroadcastReceiver();
            IntentFilter secondIntentFilter = new IntentFilter("com.example.SECOND_BROADCAST");
            registerReceiver(secondReceiver, secondIntentFilter, Context.RECEIVER_EXPORTED);
        }

        //button which initiates the startBroadcast method
        startBtn.setOnClickListener(v -> startBroadcast());

        //back button navigation
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(BroadcastActivity.this, MenuActivity.class);
            startActivity(intent);
            Toast.makeText(BroadcastActivity.this, "You are in Menu Page", Toast.LENGTH_SHORT).show();
        });
    }

    private void startBroadcast() {
        //method is responsible for creating action with a message "Hello" and sends it to respective broadcasts
        Intent firstintent = new Intent("com.example.FIRST_BROADCAST");
        firstintent.putExtra("message", "Hello");
        sendOrderedBroadcast(firstintent, null);

        Intent secondintent = new Intent("com.example.SECOND_BROADCAST");
        secondintent.putExtra("message", "Hello");
        sendOrderedBroadcast(secondintent, null);
    }

    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onPause() {
        super.onPause();

        // Unregistering the broadcasts if they are not null
        if (firstReceiver != null) {
            unregisterReceiver(firstReceiver);
            firstReceiver = null;
        }

        if (secondReceiver != null) {
            unregisterReceiver(secondReceiver);
            secondReceiver = null;
        }
    }
}