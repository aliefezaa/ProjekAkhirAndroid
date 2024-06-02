package com.example.mounticket.api;

import com.example.mounticket.models.Rent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RentApi {
    @GET("sewaalat.json") // Endpoint for fetching
    Call<List<Rent>> getRents(); // Method to fetch
}
