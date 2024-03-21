package com.example.firstapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    TextView usernameErrorView, passwordErrorView;
    SharedPreferences sharedPreferences;
    Button registerBtn, loginBtn;
    ImageButton mailBtn,twitterBtn,fbBtn;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        usernameErrorView = findViewById(R.id.usernameErrorTextView);
        passwordErrorView = findViewById(R.id.passwordErrorTextView);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);
        fbBtn = findViewById(R.id.fbBtn);
        twitterBtn = findViewById(R.id.twitterBtn);
        mailBtn = findViewById(R.id.mailBtn);

        /*Initializing the  SharedPreferences, mode is given 0 because to ensure it is in read only mode
         for security purpose.
         UserDetails is the file in which we are going to store the data of user when he registers. */
        sharedPreferences = getSharedPreferences("UserDetails", 0);
        //clearing the field whenever the app starts ensuring that previous user details are unknown.
        username.setText("");
        password.setText("");

        //providing actions for buttons

        registerBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
            Toast.makeText(LoginActivity.this, "You are in Register Page", Toast.LENGTH_SHORT).show();
        });

        mailBtn.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/gmail/about/"));
            startActivity(browserIntent);
            Toast.makeText(LoginActivity.this, "You are in Gmail Page", Toast.LENGTH_SHORT).show();
        });

        fbBtn.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/login/"));
            startActivity(browserIntent);
            Toast.makeText(LoginActivity.this, "You are in Facebook Page", Toast.LENGTH_SHORT).show();
        });

        twitterBtn.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/login"));
            startActivity(browserIntent);
            Toast.makeText(LoginActivity.this, "You are in Twitter Page", Toast.LENGTH_SHORT).show();
        });


        loginBtn.setOnClickListener(v -> {

            /*
             * Initialising the values that are stored in the shared preference to a
             * new variable so that it will be easy to compare.
             * */
            String savedUsername = sharedPreferences.getString("username", "");
            String savedPassword = sharedPreferences.getString("password", "");
            String enteredUsername = username.getText().toString();
            String enteredPassword = password.getText().toString();


            /*Checking if the values entered by the user are already registered, by comparing the
             * values that are available in the shared preference file("UserDetails").*/

            if (enteredUsername.equals(savedUsername) && enteredPassword.equals(savedPassword) && !enteredPassword.isEmpty() && !enteredUsername.isEmpty()) {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(i);
            } else {
                // Displaying the error messages
                if (!enteredUsername.equals(savedUsername) || enteredUsername.isEmpty()) {
                    usernameErrorView.setVisibility(View.VISIBLE);
                    usernameErrorView.setText("Invalid username Register First!!");
                } else {
                    usernameErrorView.setVisibility(View.GONE);
                }

                if (!enteredPassword.equals(savedPassword) || enteredPassword.isEmpty()) {
                    passwordErrorView.setVisibility(View.VISIBLE);
                    passwordErrorView.setText("Invalid password");
                } else {
                    passwordErrorView.setVisibility(View.GONE);
                }
            }
        });

    }
}