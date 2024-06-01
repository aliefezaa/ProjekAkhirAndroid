package com.example.mounticket.models;

public class MenuModel {
    private int iconResource;
    private String title;
    private String jsonUrl;

    public MenuModel(int iconResource, String title, String jsonUrl) {
        this.iconResource = iconResource;
        this.title = title;
        this.jsonUrl = jsonUrl;
    }

    public int getIconResource() {
        return iconResource;
    }

    public String getTitle() {
        return title;
    }

    public String getJsonUrl() {
        return jsonUrl;
    }
}