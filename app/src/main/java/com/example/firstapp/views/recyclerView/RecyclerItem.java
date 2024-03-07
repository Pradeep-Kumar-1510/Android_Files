package com.example.firstapp.views.recyclerView;

public class RecyclerItem {
    String name;
    Integer image;
    String desc;

    public RecyclerItem(String name, Integer image, String desc) {
        this.name = name;
        this.image = image;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

}