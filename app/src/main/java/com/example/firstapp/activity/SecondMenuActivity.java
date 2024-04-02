package com.example.firstapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.firstapp.R;
import com.example.firstapp.broadcastFiles.BroadcastOtpActivity;
import com.example.firstapp.firebaseNotification.PushNotificationsActivity;
import com.google.android.material.navigation.NavigationView;

public class SecondMenuActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Toggle for the navigation drawer
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.call) {
                Toast.makeText(SecondMenuActivity.this, "Call Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SecondMenuActivity.this, CallActivity.class);
                startActivity(intent);
            } else if (id == R.id.otp) {
                Intent intent = new Intent(SecondMenuActivity.this, BroadcastOtpActivity.class);
                startActivity(intent);
                Toast.makeText(SecondMenuActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.contacts) {
                Intent intent = new Intent(SecondMenuActivity.this, PushNotificationsActivity.class);
                startActivity(intent);
                Toast.makeText(SecondMenuActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.about) {
                Intent intent = new Intent(SecondMenuActivity.this, SampleActivity.class);
                startActivity(intent);
                Toast.makeText(SecondMenuActivity.this, "About Clicked", Toast.LENGTH_SHORT).show();
            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
