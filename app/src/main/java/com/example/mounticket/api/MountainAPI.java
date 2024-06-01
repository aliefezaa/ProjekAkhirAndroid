package com.example.mounticket.api;

import com.example.mounticket.models.Mountain;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MountainAPI {
    @GET("mountain.json") // Endpoint for fetching mountains
    Call<List<Mountain>> getMountains(); // Method to fetch mountains
}