package com.example.firstapp.views.recyclerView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        List<RecyclerItem> items = new ArrayList<>();
        items.add(new RecyclerItem("Email", android.R.drawable.ic_dialog_email, "Send Email"));
        items.add(new RecyclerItem("Info", android.R.drawable.ic_dialog_info, "Check Information"));
        items.add(new RecyclerItem("Alert", android.R.drawable.ic_dialog_alert, "Raise alert"));
        items.add(new RecyclerItem("Dialer", android.R.drawable.ic_dialog_dialer, "Dial someone"));
        items.add(new RecyclerItem("Map", android.R.drawable.ic_dialog_map, "Check location"));
        items.add(new RecyclerItem("Delete", android.R.drawable.ic_delete, "Delete details"));
        items.add(new RecyclerItem("Email", android.R.drawable.ic_dialog_email, "Send Email"));
        items.add(new RecyclerItem("Info", android.R.drawable.ic_dialog_info, "Check Information"));
        items.add(new RecyclerItem("Alert", android.R.drawable.ic_dialog_alert, "Raise alert"));
        items.add(new RecyclerItem("Dialer", android.R.drawable.ic_dialog_dialer, "Dial someone"));
        items.add(new RecyclerItem("Map", android.R.drawable.ic_dialog_map, "Check location"));
        items.add(new RecyclerItem("Email", android.R.drawable.ic_dialog_email, "Send Email"));
        items.add(new RecyclerItem("Info", android.R.drawable.ic_dialog_info, "Check Information"));
        items.add(new RecyclerItem("Alert", android.R.drawable.ic_dialog_alert, "Raise alert"));
        items.add(new RecyclerItem("Dialer", android.R.drawable.ic_dialog_dialer, "Dial someone"));
        items.add(new RecyclerItem("Map", android.R.drawable.ic_dialog_map, "Check location"));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapter(this, items));
    }

}