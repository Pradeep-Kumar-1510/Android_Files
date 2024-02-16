package com.example.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapp.R;

public class ViewFeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        Button backBtn = findViewById(R.id.backButton);
        TextView feedbackview = findViewById(R.id.feedbackview);

        // Retrieve the feedback from the intent extra
        String feedback = getIntent().getStringExtra("feedback");
        if (feedback != null) {
            feedbackview.setText(feedback);
        }else {
            feedbackview.setText("No feedback available");
        }

        backBtn.setOnClickListener(v -> {
            Intent i = new Intent(ViewFeedbackActivity.this, MenuActivity.class);
            startActivity(i);
            Toast.makeText(ViewFeedbackActivity.this, "You are in Menu Page", Toast.LENGTH_SHORT).show();
        });
    }
}