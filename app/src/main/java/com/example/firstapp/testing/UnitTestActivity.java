package com.example.firstapp.testing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;
import com.example.firstapp.activity.SecondMenuActivity;

import java.util.regex.Pattern;

public class UnitTestActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextPhoneNumber;
    private EditText editTextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);


        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextUrl = findViewById(R.id.editTextUrl);
        Button btnValidate = findViewById(R.id.btnValidate);
        Button backButton = findViewById(R.id.backButton);
        Button clearButton = findViewById(R.id.btnClear);

        backButton.setOnClickListener(v -> {

            Intent intent = new Intent(this, SecondMenuActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Redirecting",Toast.LENGTH_SHORT).show();
        });

        clearButton.setOnClickListener(v -> clearFields());
        btnValidate.setOnClickListener(v -> {

            String email = editTextEmail.getText().toString();
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            String phoneNumber = editTextPhoneNumber.getText().toString();
            String url = editTextUrl.getText().toString();


            boolean isEmailValid = isEmailValid(email);
            boolean isUsernameValid = isUsernameValid(username);
            boolean isPasswordValid = isPasswordValid(password);
            boolean isPhoneNumberValid = isPhoneNumberValid(phoneNumber);
            boolean isUrlValid = isUrlValid(url);

            Toast.makeText(UnitTestActivity.this, "Email Valid: " + isEmailValid, Toast.LENGTH_SHORT).show();
            Toast.makeText(UnitTestActivity.this, "Username Valid: " + isUsernameValid, Toast.LENGTH_SHORT).show();
            Toast.makeText(UnitTestActivity.this, "Password Valid: " + isPasswordValid, Toast.LENGTH_SHORT).show();
            Toast.makeText(UnitTestActivity.this, "Phone Number Valid: " + isPhoneNumberValid, Toast.LENGTH_SHORT).show();
            Toast.makeText(UnitTestActivity.this, "URL Valid: " + isUrlValid, Toast.LENGTH_SHORT).show();
        });
    }

    public void clearFields(){

        editTextEmail.setText("");
        editTextPhoneNumber.setText("");
        editTextPassword.setText("");
        editTextUrl.setText("");
        editTextUsername.setText("");
    }
    public static boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    public static boolean isUsernameValid(String username) {
        String usernamePattern = "^[a-zA-Z0-9_-]{3,15}$";
        return username.matches(usernamePattern);
    }

    public static boolean isPasswordValid(String password) {
        // Updated password pattern: At least 8 characters, at least one uppercase letter, one lowercase letter, and one number
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.compile(passwordPattern).matcher(password).matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        // Simple phone number pattern: 10 digits
        String phoneNumberPattern = "\\d{10}";
        return phoneNumber.matches(phoneNumberPattern);
    }

    public static boolean isUrlValid(String url) {
        // Simple URL pattern: starts with http:// or https://
        String urlPattern = "^(http://|https://).*";
        return url.matches(urlPattern);
    }
}
