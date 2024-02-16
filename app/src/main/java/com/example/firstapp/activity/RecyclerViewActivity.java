package com.example.firstapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.adapters.CustomAdapter;
import com.example.firstapp.R;
import com.example.firstapp.adapters.Recycler_data;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Recycler_data[] myListData = new Recycler_data[]{
                new Recycler_data("Email", android.R.drawable.ic_dialog_email),
                new Recycler_data("Info", android.R.drawable.ic_dialog_info),
                new Recycler_data("Delete", android.R.drawable.ic_delete),
                new Recycler_data("Dialer", android.R.drawable.ic_dialog_dialer),
                new Recycler_data("Alert", android.R.drawable.ic_dialog_alert),
                new Recycler_data("Map", android.R.drawable.ic_dialog_map),
                new Recycler_data("Email", android.R.drawable.ic_dialog_email),
                new Recycler_data("Info", android.R.drawable.ic_dialog_info),
                new Recycler_data("Delete", android.R.drawable.ic_delete),
                new Recycler_data("Dialer", android.R.drawable.ic_dialog_dialer),
                new Recycler_data("Alert", android.R.drawable.ic_dialog_alert),
                new Recycler_data("Map", android.R.drawable.ic_dialog_map),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        CustomAdapter adapter = new CustomAdapter(myListData);
       recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
