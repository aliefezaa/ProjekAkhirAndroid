package com.example.mounticket.models;

public class Transaction {
    private int id;
    private String description;
    private double amount;

    public Transaction(int id, String description, double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
