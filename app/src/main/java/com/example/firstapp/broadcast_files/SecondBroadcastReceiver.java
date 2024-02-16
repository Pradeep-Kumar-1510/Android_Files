package com.example.firstapp.broadcast_files;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SecondBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // checking if the action is same or not
        String action = intent.getAction();

        if ("com.example.SECOND_BROADCAST".equals(action)) {

            Toast.makeText(context, "Second Broadcast Triggered " + intent.getStringExtra("message"), Toast.LENGTH_SHORT).show();
        }

        if (action != null) {
            Log.d("SecondBroadcastReceiver", "Received action " + action);
        }
    }
}
