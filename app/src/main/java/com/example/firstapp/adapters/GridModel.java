package com.example.firstapp.adapters;

public class GridModel {

    private String Fruit_name;
    private int img_id;

    public GridModel(String Fruit_name, int img_id) {
        this.Fruit_name = Fruit_name;
        this.img_id = img_id;
    }

    public String getFruit_name() {
        return Fruit_name;
    }

    public void setFruit_name() {
        this.Fruit_name = Fruit_name;

    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id() {
        this.img_id = img_id;
    }
}
