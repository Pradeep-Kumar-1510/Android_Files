package com.example.firstapp.storage.appSpecificStorage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstapp.R;
import com.example.firstapp.activity.MenuActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalStorageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_external_storage, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText user = view.findViewById(R.id.editText);
        Button internalCache = view.findViewById(R.id.internalCache);
        Button externalCache = view.findViewById(R.id.externalCache);
        Button externalPublic = view.findViewById(R.id.externalPublic);
        Button externalPrivate = view.findViewById(R.id.externalPrivate);
        Button btnLoad = view.findViewById(R.id.btnLoad);
        Button btnClear = view.findViewById(R.id.btnClear);
        Button btnBack = view.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MenuActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(), "You are in Menu Page", Toast.LENGTH_SHORT).show();
        });


        btnClear.setOnClickListener(v -> {
            user.setText("");
            Toast.makeText(getContext(), "Cleared Details", Toast.LENGTH_SHORT).show();
        });

        btnLoad.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.framelayout, new ExternalDetailsFragment())
                    .addToBackStack(null)  // Add to back stack to handle back navigation
                    .commit();
            Toast.makeText(getContext(), "Showing Details", Toast.LENGTH_SHORT).show();
        });

        internalCache.setOnClickListener(v -> {

            String data = user.getText().toString();
            File folder = requireContext().getCacheDir();
            File myfile = new File(folder,"Details1");
            writeMethod(myfile,data);
            Toast.makeText(getContext(),"Data added to "+folder,Toast.LENGTH_SHORT).show();
            });

        externalCache.setOnClickListener(v -> {

            String data = user.getText().toString();
            File folder = requireContext().getExternalCacheDir();
            File myfile = new File(folder,"Details2");
            writeMethod(myfile,data);
            Toast.makeText(getContext(),"Data added to "+folder,Toast.LENGTH_SHORT).show();
        });

        externalPublic.setOnClickListener(v -> {

            String data = user.getText().toString();
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myfile = new File(folder,"Details3");
            writeMethod(myfile,data);
            Toast.makeText(getContext(),"Data added to "+folder,Toast.LENGTH_SHORT).show();

        });

        externalPrivate.setOnClickListener(v -> {

            String data = user.getText().toString();
            File folder = requireContext().getExternalFilesDir("Details");
            File myfile = new File(folder,"Details4");
            writeMethod(myfile,data);
            Toast.makeText(getContext(),"Data added to "+folder,Toast.LENGTH_SHORT).show();

        });

    }

    public void writeMethod(File myfile,String data){
        try (FileOutputStream fileOutputStream = new FileOutputStream(myfile)) {
           fileOutputStream.write(data.getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}