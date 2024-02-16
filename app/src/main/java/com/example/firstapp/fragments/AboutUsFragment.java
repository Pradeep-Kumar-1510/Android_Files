package com.example.firstapp.fragments;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;

public class AboutUsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);

        TextView popupTextView = rootView.findViewById(R.id.popup_textview);
        TextView contextTextView = rootView.findViewById(R.id.context_textview);

        registerForContextMenu(contextTextView);

        popupTextView.setOnClickListener(this::showPopupMenu);

        return rootView;


    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.pop_item1) {
                showToast("Popup Item 1 clicked");
                return true;
            } else if (itemId == R.id.pop_item2) {
                showToast("Popup Item 2 clicked");
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.context_menu_item1) {
            showToast("Context Item 1 clicked");
            return true;
        } else if (itemId == R.id.context_menu_item2) {
            showToast("Context Item 2 clicked");
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}

