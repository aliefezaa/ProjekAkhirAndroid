package com.example.mounticket.models;

public class Mountain {
    private int id;
    private String name;
    private String alamat;
    private String height;
    private String image;
    private String harga;

    public Mountain(int id, String name, String alamat, String height, String image, String harga) {
        this.id = id;
        this.name = name;
        this.alamat = alamat;
        this.height = height;
        this.image = image;
        this.harga = harga;
    }

    // Tambahkan konstruktor default
    public Mountain() {}

    // Tambahkan setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    // Tambahkan getter jika diperlukan
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getHeight() {
        return height;
    }

    public String getImage() {
        return image;
    }

    public String getHarga() {
        return harga;
    }
}