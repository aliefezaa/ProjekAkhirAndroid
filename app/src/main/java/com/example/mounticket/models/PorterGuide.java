package com.example.mounticket.models;

public class PorterGuide {
    private String name;
    private String mountain;
    private String description;
    private int profileImage; // Add profile image field
    private String phoneNumber; // Add phone number field
    private String email; // Add email field

    public PorterGuide(String name, String mountain, String description, int profileImage, String phoneNumber, String email) {
        this.name = name;
        this.mountain = mountain;
        this.description = description;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMountain() {
        return mountain;
    }

    public String getDescription() {
        return description;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}