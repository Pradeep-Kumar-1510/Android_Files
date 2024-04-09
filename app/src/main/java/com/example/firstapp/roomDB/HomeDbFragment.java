package com.example.firstapp.roomDB;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.firstapp.R;
import com.example.firstapp.activity.SecondMenuActivity;

public class HomeDbFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_db, container, false);

        Button addBtn = view.findViewById(R.id.addButton);
        Button loadBtn = view.findViewById(R.id.btnLoad);
        Button updateBtn = view.findViewById(R.id.updateButton);
        Button deleteBtn = view.findViewById(R.id.deleteButton);
        Button backButton = view.findViewById(R.id.backButton);
        addBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SecondMenuActivity.class);
            startActivity(intent);
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.addButton) {
            RoomDatabaseActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddUserFragment())
                    .addToBackStack(null).commit();
        } else if (viewId == R.id.btnLoad) {
            RoomDatabaseActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new LoadDataFragment())
                    .addToBackStack(null).commit();
        } else if (viewId == R.id.deleteButton) {
            RoomDatabaseActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteUserFragment())
                    .addToBackStack(null).commit();
        } else if (viewId == R.id.updateButton) {
            RoomDatabaseActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateUserFragment())
                    .addToBackStack(null).commit();
        }
    }

}