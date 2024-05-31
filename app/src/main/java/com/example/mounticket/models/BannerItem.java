package com.example.mounticket.models;

public class BannerItem {
    private int imageResource;
    private String title;

    public BannerItem(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    // Tambahkan getter dan setter jika diperlukan
    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }
}