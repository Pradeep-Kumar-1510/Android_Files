package com.example.firstapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;

public class HomePageActivity extends AppCompatActivity {

    public static final String d = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView popupTextView = findViewById(R.id.popup_textview);
        popupTextView.setOnClickListener(this::showPopupMenu);

        TextView userRole = findViewById(R.id.role);
        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);

        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", 0);
        String name = sharedPreferences.getString("username", d);
        String phoneNumber = sharedPreferences.getString("password", d);
        String role = sharedPreferences.getString("role",d);
        username.setText(name);
        password.setText(phoneNumber);
        userRole.setText(role);

    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.pop_item1) {
                showToast("You are in Home Page");
                return true;
            } else if (itemId == R.id.pop_item2) {
                Intent intent = new Intent(this, GridViewActivity.class);
                startActivity(intent);
                showToast("You are in Grid View Page");
                return true;
            } else if (itemId == R.id.pop_item3) {
                Intent intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                showToast("You are in List View Page");
                return true;
            } else if (itemId == R.id.pop_item4) {
                Intent intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                showToast("You are in Recycler View Page");
                return true;
            } else if (itemId == R.id.pop_item5) {
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                showToast("Showing more options");
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}