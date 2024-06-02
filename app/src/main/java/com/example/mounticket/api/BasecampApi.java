package com.example.mounticket.api;

import com.example.mounticket.models.Basecamp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BasecampApi {
    @GET("basecamp.json") // Endpoint for fetching
    Call<List<Basecamp>> getBasecamps(); // Method to fetch
}