package com.example.firstapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class PushNotificationsActivity extends AppCompatActivity {
    private EditText etToken;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notifications);
        etToken = findViewById(R.id.etToken);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                System.out.println("Fetching FCM registration Token failed");
                return;
            }
            String token = task.getResult();
            System.out.println(token);
            Toast.makeText(PushNotificationsActivity.this,
                    "Your device registration token is " + token,
                    Toast.LENGTH_SHORT).show();

            etToken.setText(token);
        });
    }
}
