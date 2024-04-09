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
public class DeleteUserFragment extends Fragment {

    Button submitBtn,clearBtn,backBtn;

    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_user, container, false);

        editText = view.findViewById(R.id.editText);
        submitBtn = view.findViewById(R.id.btnSubmit);
        clearBtn = view.findViewById(R.id.btnClear);
        backBtn = view.findViewById(R.id.backButton);

        submitBtn.setOnClickListener(v -> {

            String idString = editText.getText().toString();
            if (idString.isEmpty()) {
                Toast.makeText(requireContext(), "Id cannot be empty", Toast.LENGTH_SHORT).show();
            } else {

                int id = Integer.parseInt(editText.getText().toString());
                UserEntityClass userEntityClass = new UserEntityClass();
                userEntityClass.setUserId(id);
                RoomDatabaseActivity.databaseClass.myDao().deleteUser(userEntityClass);

                Toast.makeText(requireContext(), "Data successfully deleted", Toast.LENGTH_SHORT).show();
                editText.setText("");
            }
        });

        backBtn.setOnClickListener((v -> {
            HomeDbFragment homeDbFragment = new HomeDbFragment();

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeDbFragment) // Replacing the current fragment with HomeDbFragment
                    .addToBackStack(null) // Add the transaction to the back stack
                    .commit();
        }));

        clearBtn.setOnClickListener(v -> editText.setText(""));

        return view;
    }
}