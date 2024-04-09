package com.example.firstapp.roomDB;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import com.example.firstapp.R;

public class RoomDatabaseActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_database);

        fragmentManager = getSupportFragmentManager();
        databaseClass = Room.databaseBuilder(getApplicationContext(), DatabaseClass.class, "userDB").allowMainThreadQueries().build();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeDbFragment()).commit();
        }
    }
}