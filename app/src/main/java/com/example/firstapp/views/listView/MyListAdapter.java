package com.example.firstapp.views.listView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.firstapp.R;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;
    private final String[] subtitle;
    private final Integer[] imgId;

    public MyListAdapter(@NonNull Context context, String[] title, String[] subtitle, Integer[] imgId) {
        super(context, R.layout.custom_list_item, title);
        this.context = (Activity) context;
        this.title = title;
        this.subtitle = subtitle;
        this.imgId = imgId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View rowId = convertView;
        ViewHolder holder;

        if (rowId == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowId = inflater.inflate(R.layout.custom_list_item, parent, false);

            // Initializing ViewHolder
            holder = new ViewHolder();
            holder.imageView = rowId.findViewById(R.id.imgView);
            holder.textView1 = rowId.findViewById(R.id.text1);
            holder.textView2 = rowId.findViewById(R.id.text2);

            rowId.setTag(holder); // Setting tag for reuse
        } else {
            // Reuse ViewHolder
            holder = (ViewHolder) rowId.getTag();
        }

        // Set data to views
        holder.imageView.setImageResource(imgId[position]);
        holder.textView1.setText(title[position]);
        holder.textView2.setText(subtitle[position]);

        return rowId;
    }

    // ViewHolder pattern
    static class ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}