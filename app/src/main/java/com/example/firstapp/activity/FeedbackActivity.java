package com.example.firstapp.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstapp.fragments.FormFragment;
import com.example.firstapp.R;
import com.example.firstapp.fragments.RatingFragment;

public class FeedbackActivity extends AppCompatActivity {


 //   Button backButton = findViewById(R.id.backButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_feedback);
        Button ratingButton = findViewById(R.id.frag1btn);
        Button formButton = findViewById(R.id.frag2btn);

//        backButton.setOnClickListener(v -> {
//            Intent i = new Intent(FeedbackActivity.this, MenuActivity.class);
//            startActivity(i);
//            Toast.makeText(FeedbackActivity.this, "You are in Menu Page", Toast.LENGTH_SHORT).show();
//        });


        formButton.setOnClickListener(v -> replaceFragment(new FormFragment()));


        ratingButton.setOnClickListener(v -> replaceFragment(new RatingFragment()));
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame1,fragment);
        fragmentTransaction.commit();
    }
}