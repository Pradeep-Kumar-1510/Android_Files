package com.example.firstapp.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;

public class SaveFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false);
    }


    @SuppressLint("SetTextI18n")
    @Override

    public void
    onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnDetails = view.findViewById(R.id.btnDetails);
        Button btnClear = view.findViewById(R.id.btnClear);
        Button btnSave = view.findViewById(R.id.btnSave);
        EditText username = view.findViewById(R.id.username);
        EditText phoneNumber = view.findViewById(R.id.phone);
        TextView errorName = view.findViewById(R.id.usernameErrorTextView);
        TextView errorNumber = view.findViewById(R.id.numberErrorTextView);


        btnDetails.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.framelayout, new DetailsFragment())
                    .addToBackStack(null)  // Add to back stack to handle back navigation
                    .commit();
         Toast.makeText(getContext(), "Showing Details", Toast.LENGTH_SHORT).show();
        });

        btnSave.setOnClickListener(v -> {

            String name = username.getText().toString();
            String number = phoneNumber.getText().toString();

            if (name.isEmpty() ){
                errorName.setVisibility(View.VISIBLE);
                errorName.setText("Name must not be empty");
            }else{
                errorName.setVisibility(View.GONE);
            }
            if (number.isEmpty() ) {
                errorNumber.setVisibility(View.VISIBLE);
                errorNumber.setText("Phone number is required");
            }else {
                errorNumber.setVisibility(View.GONE);
            }
            if (!name.isEmpty() && !number.isEmpty()) {
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ContactDetails",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", username.getText().toString());
                editor.putString("phone", phoneNumber.getText().toString());
                editor.apply();
                Toast.makeText(getContext(), "Saved Details", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Failed to Saved Details", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(v -> {
            username.setText("");
            phoneNumber.setText("");
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ContactDetails",0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(getContext(), "Cleared Details", Toast.LENGTH_SHORT).show();
        });

    }

}