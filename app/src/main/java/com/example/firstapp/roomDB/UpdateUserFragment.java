package com.example.firstapp.roomDB;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstapp.R;
public class UpdateUserFragment extends Fragment {

    EditText username,userId,userEmail;
    Button backButton,clearButton,submitButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_user, container, false);

        userEmail = view.findViewById(R.id.editTextMail);
        userId = view.findViewById(R.id.editTextUserId);
        username = view.findViewById(R.id.usernameEditText);

        backButton = view.findViewById(R.id.backButton);
        clearButton = view.findViewById(R.id.btnClear);
        submitButton = view.findViewById(R.id.btnSubmit);

        submitButton.setOnClickListener(v -> {
            int id = Integer.parseInt(userId.getText().toString());
            String name = username.getText().toString();
            String email = userEmail.getText().toString();

            UserEntityClass userEntityClass = new UserEntityClass();
            userEntityClass.setUserId(id);
            userEntityClass.setUserName(name);
            userEntityClass.setEmailId(email);

            RoomDatabaseActivity.databaseClass.myDao().updateUser(userEntityClass);
            Toast.makeText(requireContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            userId.setText("");
            userEmail.setText("");
            username.setText("");
        });

        backButton.setOnClickListener((v -> {
            HomeDbFragment homeDbFragment = new HomeDbFragment();

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeDbFragment)
                    .addToBackStack(null)
                    .commit();
        }));

        clearButton.setOnClickListener(v -> {
            username.setText("");
            userId.setText("");
            userEmail.setText("");
        });

        return view;
    }
}