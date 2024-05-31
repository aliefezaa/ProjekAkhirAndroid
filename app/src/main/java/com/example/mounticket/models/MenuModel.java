package com.example.mounticket.models;

public class MenuModel {
    private int iconResId;
    private String title;
    private String jsonUrl;

    public MenuModel(int iconResId, String title, String jsonUrl) {
        this.iconResId = iconResId;
        this.title = title;
        this.jsonUrl = jsonUrl;
    }

    public int getIconResource() {
        return iconResId;
    }

    public String getTitle() {
        return title;
    }

    public String getJsonUrl() {
        return jsonUrl;
    }
}