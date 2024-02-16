package com.example.firstapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstapp.R;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<GridModel> {
    public GridAdapter(@NonNull Context context, ArrayList<GridModel> gridModelArrayList) {
        super(context, 0, gridModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {

            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        GridModel gridModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.TVCourse);
        ImageView courseIV = listitemView.findViewById(R.id.IVCourse);

        courseTV.setText(gridModel.getFruit_name());
        courseIV.setImageResource(gridModel.getImg_id());
        return listitemView;
    }
}
