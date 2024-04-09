package com.example.firstapp.roomDB;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.firstapp.R;

public class AddUserFragment extends Fragment {

    EditText userId, userName, userMail;
    Button backButton, clearButton, saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        userId = view.findViewById(R.id.userIdEditText);
        userName = view.findViewById(R.id.usernameEditText);
        userMail = view.findViewById(R.id.emailEditText);
        backButton = view.findViewById(R.id.backButton);
        saveButton = view.findViewById(R.id.btnSave);
        clearButton = view.findViewById(R.id.btnClear);

        saveButton.setOnClickListener(v -> {
            if (userName == null || userId.getText().length() == 0 || userMail == null) {
                Toast.makeText(requireContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                int userID = Integer.parseInt(userId.getText().toString());
                String username = userName.getText().toString();
                String mail = userMail.getText().toString();

                UserEntityClass userEntityClass = new UserEntityClass();
                userEntityClass.setUserId(userID);
                userEntityClass.setUserName(username);
                userEntityClass.setEmailId(mail);

                RoomDatabaseActivity.databaseClass.myDao().addUser(userEntityClass);

                Toast.makeText(requireContext(), "User Added Successfully", Toast.LENGTH_SHORT).show();
                clearData();
            }
        });

        backButton.setOnClickListener((v -> {
            HomeDbFragment homeDbFragment = new HomeDbFragment();

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeDbFragment)
                    .addToBackStack(null)
                    .commit();
        }));

        clearButton.setOnClickListener((v -> clearData()));

        return view;
    }

    private void clearData() {

        userMail.setText("");
        userName.setText("");
        userId.setText("");
    }
}