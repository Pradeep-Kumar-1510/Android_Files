package com.example.firstapp.api.retrofitApiMethods;

import com.google.gson.annotations.SerializedName;

public class JsonDataClass {
//    private final int userid;
//    private final String title;
//    @SerializedName("body")
//    private final String text;
//    private Integer id;
//    public JsonDataClass(int userid, String title, String text) {
//        this.userid = userid;
//        this.title = title;
//        this.text = text;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getUserid() {
//        return userid;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getText() {
//        return text;
//    }

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
