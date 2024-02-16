package com.example.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.firstapp.R;

public class ListViewActivity extends AppCompatActivity {


    String[] fruitNames = {"Apple", "Banana", "Orange", "Grapes", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView list = findViewById(R.id.List_views);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fruitNames);


        list.setAdapter(adapter);
    }

    public void goBackOnClick(View view) {
        finish();
    }
}