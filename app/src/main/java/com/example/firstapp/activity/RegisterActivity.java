package com.example.firstapp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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


        EditText register = findViewById(R.id.register);
        EditText newPassword = findViewById(R.id.newPassword);
        Button registerBtn = findViewById(R.id.registerBtn);
        TextView information = findViewById(R.id.information);
        Spinner user = findViewById(R.id.userSpinner);
        CheckBox termsCheckbox = findViewById(R.id.userCheckbox);


        List<String> userRoles = new ArrayList<>();
        userRoles.add("Select a role");
        userRoles.add("Admin");
        userRoles.add("User");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, userRoles);
        user.setAdapter(adapter);

        registerBtn.setOnClickListener(v -> {
            String newUsername = register.getText().toString();
            String newPass = newPassword.getText().toString();
            String userRole = user.getSelectedItem().toString();
            boolean terms = termsCheckbox.isChecked();


            if (newUsername.isEmpty() || newPass.isEmpty() || userRole.equals("Select a role") || !terms) {
                Toast.makeText(RegisterActivity.this, "Please fill all fields and tick the checkbox", Toast.LENGTH_SHORT).show();
            } else {
                information.setText("New Username is : " + newUsername + "\n" + "New Password is : " + newPass + "\n" + "Role selected is :" + userRole);

                Toast.makeText(RegisterActivity.this, "Registration Sucess", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}