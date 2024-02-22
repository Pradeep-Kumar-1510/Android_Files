package com.example.firstapp.adapters;

public class GridModel {

    private final String Fruit_name;
    private final int img_id;

    public GridModel(String Fruit_name, int img_id) {
        this.Fruit_name = Fruit_name;
        this.img_id = img_id;
    }

    public String getFruit_name() {
        return Fruit_name;
    }

    public int getImg_id() {
        return img_id;
    }

}
