package com.example.firstapp.roomDB;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.firstapp.R;

import java.util.List;

public class LoadDataFragment extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_load_data, container, false);

        textView = view.findViewById(R.id.textView);

        List<UserEntityClass> users = RoomDatabaseActivity.databaseClass.myDao().getUsers();
        StringBuilder info = new StringBuilder();

        if (users.isEmpty()) {
            textView.setText(R.string.no_data);
        } else {

            for (UserEntityClass usr : users) {
                int id = usr.getUserId();
                String name = usr.getUserName();
                String mail = usr.getEmailId();

                info.append("\n\nid : ").append(id).append("\nName: ").append(name).append("\nEmail: ").append(mail);
            }

            textView.setText(info.toString());
        }
        return view;
    }
}