package com.example.firstapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.firstapp.R;
import com.example.firstapp.services.ServiceActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button backButton = findViewById(R.id.backButton);
        Button storageButton = findViewById(R.id.storageButton);

        backButton.setOnClickListener(v -> {
            Intent i = new Intent(MenuActivity.this, HomePageActivity.class);
            startActivity(i);
            Toast.makeText(MenuActivity.this, "You are in Menu Page", Toast.LENGTH_SHORT).show();
        });


        storageButton.setOnClickListener(v -> {
            Intent i = new Intent(MenuActivity.this, StorageActivity.class);
            startActivity(i);
            Toast.makeText(MenuActivity.this, "You are in Menu Page", Toast.LENGTH_SHORT).show();
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.feedbackAction) {
            Intent intent = new Intent(MenuActivity.this, FeedbackActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You are in feedback page", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.helpAction) {
            Intent intent = new Intent(MenuActivity.this, LifeCycleActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You are in Help page", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (itemId == R.id.viewFeedbacks) {
            Intent intent = new Intent(MenuActivity.this, ViewFeedbackActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You are viewing feedbacks submitted", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (itemId == R.id.broadcast) {
            Intent intent = new Intent(MenuActivity.this, BroadcastActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You are in Broadcast Page", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.service) {
            Intent intent = new Intent(MenuActivity.this, ServiceActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You are in Services Page", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}