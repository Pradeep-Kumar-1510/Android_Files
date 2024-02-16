package com.example.firstapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final Recycler_data[] Recyclerdata;


    public CustomAdapter(Recycler_data[] listdata) {
        this.Recyclerdata = listdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recycler_data myListData = Recyclerdata[position];
        holder.textView.setText(Recyclerdata[position].getDescription());
        holder.imageView.setImageResource(Recyclerdata[position].getImgId());
        holder.relativeLayout.setOnClickListener(view -> Toast.makeText(view.getContext(), "click on item: " + myListData.getDescription(), Toast.LENGTH_LONG).show());
    }


    @Override
    public int getItemCount() {
        return Recyclerdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.textView = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
