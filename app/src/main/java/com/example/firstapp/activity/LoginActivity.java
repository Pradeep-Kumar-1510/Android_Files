package com.example.firstapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;

public class LoginActivity extends AppCompatActivity {

    //username = admin
    //password = admin
    // only this will accept while login.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView username = findViewById(R.id.username);
        TextView Password = findViewById(R.id.Password);
        Button loginbtn = findViewById(R.id.loginbtn);
        Button registerationBtn = findViewById(R.id.registerBtn);
        ImageButton fbBtn = findViewById(R.id.fb);
        ImageButton twitterBtn = findViewById(R.id.twitter);
        ImageButton gmailBtn = findViewById(R.id.gmail);

        loginbtn.setOnClickListener(v -> {
            if (username.getText().toString().equals("admin") && Password.getText().toString().equals("admin")) {
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
            if (username.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Username and Password is Required", Toast.LENGTH_SHORT).show();
            }
        });
        registerationBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        fbBtn.setOnClickListener(v -> Toast.makeText(LoginActivity.this, "Clicked Facebook", Toast.LENGTH_SHORT).show());

        twitterBtn.setOnClickListener(v -> Toast.makeText(LoginActivity.this, "Clicked Twitter", Toast.LENGTH_SHORT).show());

        gmailBtn.setOnClickListener(v -> Toast.makeText(LoginActivity.this, "Clicked Gmail", Toast.LENGTH_SHORT).show());
    }
}