package com.example.mounticket.models;

public class MenuModel {
    private int iconResId;
    private String name;
    private String jsonUrl;

    public MenuModel(int iconResId, String name, String jsonUrl) {
        this.iconResId = iconResId;
        this.name = name;
        this.jsonUrl = jsonUrl;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getName() {
        return name;
    }

    public String getJsonUrl() {
        return jsonUrl;
    }
}