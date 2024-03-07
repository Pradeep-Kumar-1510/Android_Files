package com.example.firstapp.storage.appSpecificStorage;

import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExternalDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_external_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView user = view.findViewById(R.id.editText);
        Button internalCache = view.findViewById(R.id.internalCache);
        Button externalCache = view.findViewById(R.id.externalCache);
        Button externalPublic = view.findViewById(R.id.externalPublic);
        Button externalPrivate = view.findViewById(R.id.externalPrivate);
        Button btnback = view.findViewById(R.id.btnBack);


        btnback.setOnClickListener(v -> previous());

        internalCache.setOnClickListener(v -> {

            File folder = requireContext().getCacheDir();
            File myfile = new File(folder, "Details1");
            String data = read(myfile);
            if (data != null){

                user.setText(data);
                Toast.makeText(getContext(), "Data Fetched", Toast.LENGTH_SHORT).show();
            }else {
                user.setText(" ");
                Toast.makeText(getContext(), "No Data Fetched", Toast.LENGTH_SHORT).show();
            }
        });

        externalCache.setOnClickListener(v -> {

            File folder = requireContext().getExternalCacheDir();
            File myfile = new File(folder, "Details2");
            String data = read(myfile);
            if (data != null){
                user.setText(data);
                Toast.makeText(getContext(), "Data Fetched", Toast.LENGTH_SHORT).show();
            }else {
                user.setText(" ");
                Toast.makeText(getContext(), "No Data Fetched", Toast.LENGTH_SHORT).show();
            }
        });

        externalPublic.setOnClickListener(v -> {

            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myfile = new File(folder, "Details3");
            String data = read(myfile);
            if (data != null){

                user.setText(data);
                Toast.makeText(getContext(), "Data Fetched", Toast.LENGTH_SHORT).show();
            }else {
                user.setText(" ");
                Toast.makeText(getContext(), "No Data Fetched", Toast.LENGTH_SHORT).show();
            }
        });

        externalPrivate.setOnClickListener(v -> {

            File folder = requireContext().getExternalFilesDir("Details");
            File myfile = new File(folder, "Details4");
            String data = read(myfile);
            if (data != null){

                user.setText(data);
                Toast.makeText(getContext(), "Data Fetched", Toast.LENGTH_SHORT).show();
            }else {
                user.setText(" ");
                Toast.makeText(getContext(), "No Data Fetched", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String read(File myfile) {
        try (FileInputStream fin = new FileInputStream(myfile)) {
            StringBuilder buffer = new StringBuilder();
            byte[] bytes = new byte[1024]; // Reading data in 1024 bytes to improve efficiency.
            int length;
            while ((length = fin.read(bytes)) != -1) {
                buffer.append(new String(bytes, 0, length));
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void previous() {
        // Navigate back to SaveFragment
        getParentFragmentManager().popBackStack();
    }
}
