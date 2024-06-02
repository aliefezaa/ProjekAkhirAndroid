package com.example.mounticket.models;

public class Basecamp {
    private int id;
    private String mt;
    private String commodity;
    private String distance;

    public Basecamp(int id, String mt, String commodity, String distance) {
        this.id = id;
        this.mt = mt;
        this.commodity = commodity;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
