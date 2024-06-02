package com.example.mounticket.models;

public class OpenTrip {
    private String tripName;
    private String tripDescription;

    public OpenTrip(String tripName, String tripDescription) {
        this.tripName = tripName;
        this.tripDescription = tripDescription;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }
}