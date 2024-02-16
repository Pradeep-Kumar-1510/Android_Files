package com.example.firstapp.broadcast_files;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class FirstBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // checking if the action is same or not
        String action = intent.getAction();

        if ("com.example.FIRST_BROADCAST".equals(action)) {

            Toast.makeText(context, "First Broadcast triggered " + intent.getStringExtra("message"), Toast.LENGTH_SHORT).show();
        }
        if (action != null) {
            Log.d("FirstBroadcastReceiver", "Received action: " + action);
        }
    }
}


