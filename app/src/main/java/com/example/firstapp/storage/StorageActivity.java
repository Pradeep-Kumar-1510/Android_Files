package com.example.firstapp.storage;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstapp.R;
import com.example.firstapp.databinding.ActivityStorageBinding;
import com.example.firstapp.storage.appSpecificStorage.ExternalStorageFragment;
import com.example.firstapp.storage.appSpecificStorage.InternalStorageFragment;
import com.example.firstapp.storage.sharedPreferences.SaveFragment;

public class StorageActivity extends AppCompatActivity {

    ActivityStorageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStorageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefrag(new SaveFragment());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.sharedpreferences) {
                replacefrag(new SaveFragment());
                Toast.makeText(this, "You are in Shared Preferences page", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.internalstorage) {
                replacefrag(new InternalStorageFragment());
                Toast.makeText(this, "You are in Internal Storage page", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.externalstorage) {
                replacefrag(new ExternalStorageFragment());
                Toast.makeText(this, "You are in External Storage page", Toast.LENGTH_SHORT).show();
            }

            return true;
        });
    }


    private void replacefrag(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

}