package com.example.firstapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button homeBtn = findViewById(R.id.homebtn);
        Button nextBtn = findViewById(R.id.nextbtn);
        Button ListBtn = findViewById(R.id.ListBtn);
        Button r_viewBtn = findViewById(R.id.r_viewBtn);
        Button menuBtn = findViewById(R.id.menuBtn);

        homeBtn.setOnClickListener(v -> {
            Intent i = new Intent(HomePageActivity.this, HomePageActivity.class);
            startActivity(i);
            Toast.makeText(HomePageActivity.this, "You are in Home Page", Toast.LENGTH_SHORT).show();
        });

        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, GridViewActivity.class);
            startActivity(intent);
            Toast.makeText(HomePageActivity.this, "You are in Gridview page", Toast.LENGTH_SHORT).show();
        });

        ListBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, ListViewActivity.class);
            startActivity(intent);
            Toast.makeText(HomePageActivity.this, "You are in Listview page", Toast.LENGTH_SHORT).show();
        });


        r_viewBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, RecyclerViewActivity.class);
            startActivity(intent);
            Toast.makeText(HomePageActivity.this, "You are in RecyclerView page", Toast.LENGTH_SHORT).show();
        });

        menuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, MenuActivity.class);
            startActivity(intent);
            Toast.makeText(HomePageActivity.this, "You are in MenuView page", Toast.LENGTH_SHORT).show();
        });
    }

}