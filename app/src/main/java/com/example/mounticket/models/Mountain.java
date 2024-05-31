package com.example.mounticket.models;

public class Mountain {
    private int id;
    private String name;
    private String alamat;
    private String height;
    private String image;
    private String harga;

    // Constructors
    public Mountain() { }

    public Mountain(int id, String name, String alamat, String height, String image, String harga) {
        this.id = id;
        this.name = name;
        this.alamat = alamat;
        this.height = height;
        this.image = image;
        this.harga = harga;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getHarga() { return harga; }
    public void setHarga(String harga) { this.harga = harga; }
}