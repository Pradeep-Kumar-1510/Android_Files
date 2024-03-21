package com.example.firstapp.views.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firstapp.R;

public class ViewHolderClass extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;
    public TextView textView2;

    public ViewHolderClass(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgView);
        textView = itemView.findViewById(R.id.text1);
        textView2 = itemView.findViewById(R.id.text2);

        // Set click listener for the item
        itemView.setOnClickListener(v -> {
            // Display a toast message with the name of the item
            Toast.makeText(itemView.getContext(), "You clicked "+textView.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }
}