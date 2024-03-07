package com.example.firstapp.storage.sharedPreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;
/*In this Fragment , when the user clicks the view details button in the previous fragment, he is replaced
with this fragment where when he clicks the load button, the details from the contact details file is
fetched using the key. And when he clicks go back button, he is navigated to the save fragment.*/

public class DetailsFragment extends Fragment {

    public static final String d = "N/A";
    private TextView user;
    private TextView phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
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
/*In the load method, it initially checks whether the contact details is filled with values or not.
the condition of null is checked by comparing with the "d" value which is assigned as N/A representing 0.
*/
    private void load() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ContactDetails", 0);
        String name = sharedPreferences.getString("name", d);
        String phoneNumber = sharedPreferences.getString("phone", d);
        if (name.equals(d) || phoneNumber.equals(d)) {
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Data found", Toast.LENGTH_SHORT).show();
            user.setText(name);
            phone.setText(phoneNumber);
        }
    }

    private void previous() {
        // Navigate back to SaveFragment
        getParentFragmentManager().popBackStack();
    }
}