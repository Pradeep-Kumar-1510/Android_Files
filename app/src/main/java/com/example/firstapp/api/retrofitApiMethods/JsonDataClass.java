package com.example.firstapp.api.retrofitApiMethods;

import com.google.gson.annotations.SerializedName;

public class JsonDataClass {

    private final int userId;
    private final String title;
    @SerializedName("body")
    private final String text;
    private Integer id;

    public JsonDataClass(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;

    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
