package com.example.firstapp.adapters;

public class Recycler_data {
    private final String description;
    private final int imgId;

    public Recycler_data(String description, int imgId) {
        this.description = description;
        this.imgId = imgId;
    }

    public String getDescription() {
        return description;
    }

    public int getImgId() {
        return imgId;
    }

}