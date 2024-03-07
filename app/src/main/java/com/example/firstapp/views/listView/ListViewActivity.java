package com.example.firstapp.views.listView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    String[] title = {"List View","Grid View","Recycler View","Services","Broadcasts","Storages","Api"};
    String[] subtitle = {"Implemented list view","Implemented Grid view","Implemented Recycler view","Implemented Services","Implemented Broadcasts",
            "Implemented Storages","Implemented Api"};

    Integer[] imgId = {R.drawable.listview,R.drawable.gridview,R.drawable.recyclerview,R.drawable.service,R.drawable.broadcast,R.drawable.storage,R.drawable.api};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView list = findViewById(R.id.List_views);


        MyListAdapter adapter = new MyListAdapter(this,title,subtitle,imgId);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    public void goBackOnClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==0){
            Toast.makeText(this,"You selected List View",Toast.LENGTH_SHORT).show();
        } else if (position == 1) {
            Toast.makeText(this,"You selected Grid View",Toast.LENGTH_SHORT).show();
        } else if (position == 2){
            Toast.makeText(this,"You selected Recycler View",Toast.LENGTH_SHORT).show();
        } else if (position == 3){
            Toast.makeText(this,"You selected Services",Toast.LENGTH_SHORT).show();
        } else if (position == 4){
            Toast.makeText(this,"You selected Broadcasts",Toast.LENGTH_SHORT).show();
        } else if (position == 5){
            Toast.makeText(this,"You selected Storages",Toast.LENGTH_SHORT).show();
        } else if (position == 6){
            Toast.makeText(this,"You selected Api",Toast.LENGTH_SHORT).show();
        }

    }
}