package com.example.firstapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firstapp.R;
import com.example.firstapp.activity.ViewHolderClass;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolderClass> {
    Context context;
    List<RecyclerItem> items;

    public CustomAdapter(Context context, List<RecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_recycler, parent, false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        RecyclerItem currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getName());
        holder.textView2.setText(currentItem.getDesc());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}