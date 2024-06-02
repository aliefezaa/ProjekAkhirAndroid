package com.example.mounticket.models;

public class Rent {
    private int id;
    private String shop;
    private String location;
    private String rentable;
    private String imgalat;
    private String kontak;

    public Rent(int id, String shop, String location, String rentable, String imgalat, String kontak) {
        this.id = id;
        this.shop = shop;
        this.location = location;
        this.rentable = rentable;
        this.imgalat = imgalat;
        this.kontak = kontak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRentable() {
        return rentable;
    }

    public void setRentable(String rentable) {
        this.rentable = rentable;
    }

    public String getImgalat() {
        return imgalat;
    }

    public void setImgalat(String imgalat) {
        this.imgalat = imgalat;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }
}
