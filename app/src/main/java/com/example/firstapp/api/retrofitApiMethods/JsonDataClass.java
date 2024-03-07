package com.example.firstapp.api.retrofitApiMethods;

import com.google.gson.annotations.SerializedName;

public class JsonDataClass {
    private final int userid;
    private final String title;
    @SerializedName("body")
    private final String text;
    private Integer id;
    public JsonDataClass(int userid, String title, String text) {
        this.userid = userid;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
