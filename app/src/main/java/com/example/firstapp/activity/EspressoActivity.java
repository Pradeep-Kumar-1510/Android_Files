package com.example.firstapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;

public class EspressoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso);

        Button buttonClear = findViewById(R.id.button1);
        EditText editText = findViewById(R.id.editText1);
        Button submitButton = findViewById(R.id.button2);
        buttonClear.setOnClickListener(v -> editText.setText(" "));
        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondMenuActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
        });
        submitButton.setOnClickListener(v -> {
            String inputText = editText.getText().toString();
            if (!inputText.isEmpty()) {
                Toast.makeText(EspressoActivity.this, "Submitted data: " + inputText, Toast.LENGTH_SHORT).show();
                editText.setText("");
            } else {
                Toast.makeText(EspressoActivity.this, "Please enter some data before submitting", Toast.LENGTH_SHORT).show();
            }
        });


    }
}