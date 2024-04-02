package com.example.firstapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;

public class ProfileActivity extends AppCompatActivity {

    TextView usernameTextView, passwordTextView, emailTextView, phoneTextView, roleView;
    Button backButton;
    ImageView editButton, deleteButton;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameTextView = findViewById(R.id.nameView);
        passwordTextView = findViewById(R.id.passwordView);
        emailTextView = findViewById(R.id.emailView);
        roleView = findViewById(R.id.roleView);
        phoneTextView = findViewById(R.id.phoneView);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, SecondMenuActivity.class);
            startActivity(intent);
        });
        sharedPreferences = getSharedPreferences("UserDetails", 0);

        displayUserDetails();

        editButton.setOnClickListener(v -> showUpdateDialog());

        deleteButton.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Delete account details from SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    Toast.makeText(ProfileActivity.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    finish();
                })
                .setNegativeButton("No", null)
                .show());
    }

    public void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Details");

        // Inflate the layout for the dialog
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams")
        View dialogView = inflater.inflate(R.layout.update_details, null);
        builder.setView(dialogView);

        // EditText fields in the dialog layout
        final EditText inputUsername = dialogView.findViewById(R.id.inputUsername);
        final EditText inputPassword = dialogView.findViewById(R.id.inputPassword);
        final EditText inputEmail = dialogView.findViewById(R.id.inputEmail);
        final EditText inputPhone = dialogView.findViewById(R.id.inputPhone);
        final EditText inputRole = dialogView.findViewById(R.id.inputRole);

        inputUsername.setHint("Enter new username");
        inputPassword.setHint("Enter new password");
        inputEmail.setHint("Enter new email");
        inputPhone.setHint("Enter new phone number");
        inputRole.setHint("Enter new role");

        builder.setPositiveButton("OK", (dialog, which) -> {
            String newUsername = inputUsername.getText().toString();
            String newPassword = inputPassword.getText().toString();
            String newEmail = inputEmail.getText().toString();
            String newPhone = inputPhone.getText().toString();
            String newRole = inputRole.getText().toString();

            if (newUsername.isEmpty() || newPassword.isEmpty() || newEmail.isEmpty() || newPhone.isEmpty() || newRole.isEmpty()) {
                Toast.makeText(ProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                saveUserDetails(newUsername, newPassword, newEmail, newPhone, newRole);
                Toast.makeText(ProfileActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                displayUserDetails();
            }
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();
    }

    protected void saveUserDetails(String username, String password, String email, String phone, String role) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("role", role);
        editor.apply();
    }

    @SuppressLint("SetTextI18n")
    public void displayUserDetails() {
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        String role = sharedPreferences.getString("role", "");

        usernameTextView.setText(username);
        passwordTextView.setText(password);
        emailTextView.setText(email);
        phoneTextView.setText(phone);
        roleView.setText(role);
    }
}