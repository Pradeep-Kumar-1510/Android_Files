package com.example.firstapp.storage.appSpecificStorage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*In this Fragment , when the user clicks the view details button in the previous fragment, he is replaced
with this fragment where when he clicks the load button, the details from the contact details file is
fetched using the key. And when he clicks go back button, he is navigated to the save fragment.*/

public class InternalDetailsFragment extends Fragment {
    private TextView user;
    private TextView phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_internal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = view.findViewById(R.id.username);
        phone = view.findViewById(R.id.phone);

        // Load data when the "Load" button is clicked
        Button btnLoad = view.findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(v -> load());

        // Navigate back to SaveFragment when the "Previous" button is clicked
        Button btnPrevious = view.findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(v -> previous());
    }

    private void load() {
            try {
                FileInputStream fin = requireContext().openFileInput("Contact");
                Scanner scanner = new Scanner(fin);

                if (scanner.hasNextLine()) {
                    String name = scanner.nextLine(); // Reading the name
                    if (scanner.hasNextLine()) {
                        String number = scanner.nextLine(); // Reading the number
                        user.setText(name);
                        phone.setText(number);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }

    private void previous() {
        // Navigate back to SaveFragment
        getParentFragmentManager().popBackStack();
    }
}