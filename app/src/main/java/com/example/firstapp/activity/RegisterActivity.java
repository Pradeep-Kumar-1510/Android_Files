package com.example.firstapp.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        EditText username = findViewById(R.id.register);
        EditText newPassword = findViewById(R.id.newPassword);
        Button registerBtn = findViewById(R.id.registerBtn);
        Spinner user = findViewById(R.id.userSpinner);
        CheckBox termsCheckbox = findViewById(R.id.userCheckbox);


        List<String> userRoles = new ArrayList<>();
        userRoles.add("Select a role");
        userRoles.add("Admin");
        userRoles.add("User");
        userRoles.add("Guest");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, userRoles);
        user.setAdapter(adapter);


        registerBtn.setOnClickListener(v -> {
            String newUsername = username.getText().toString();
            String newPass = newPassword.getText().toString();
            String userRole = user.getSelectedItem().toString();
            boolean terms = termsCheckbox.isChecked();

            if (newUsername.isEmpty() || newPass.isEmpty() || userRole.equals("Select a role") || !terms) {
                Toast.makeText(RegisterActivity.this, "Please fill all fields and tick the checkbox", Toast.LENGTH_SHORT).show();
            } else {

                SharedPreferences sharedPreferences = getSharedPreferences("UserDetails",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",newUsername);
                editor.putString("password",newPass);
                editor.putString("role",userRole);
                editor.apply();
                Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
