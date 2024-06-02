package com.example.mounticket.models;

public class TravelOjek {
    private String name;
    private String description;
    private String phoneNumber;

    public TravelOjek(String name, String description, String phoneNumber) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}