package com.example.mounticket.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mounticket.R;
import com.example.mounticket.adapter.MountainAdapter;
import com.example.mounticket.adapter.RentAdapter;
import com.example.mounticket.models.Mountain;
import com.example.mounticket.models.Rent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InfoRentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RentAdapter rentAdapter;
    private List<Rent> rentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_rent);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rentList = new ArrayList<>();
        rentAdapter = new RentAdapter(rentList);
        recyclerView.setAdapter(rentAdapter);

        // Fetch data from JSON and update the list
        fetchRentData();
    }

    private void fetchRentData() {
        String url = "https://aliefezaa.github.io/ProjekAkhirAndroid/sewaalat.json";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String shop = jsonObject.getString("toko");
                        String location = jsonObject.getString("lokasi");
                        String rentable = jsonObject.getString("disewakan");
                        String imgalat = jsonObject.getString("imgalat");
                        String kontak = jsonObject.getString("kontak");

                        Rent rent = new Rent(id, shop, location, rentable, imgalat, kontak);
                        rentList.add(rent);
                    }

                    rentAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error fetching data: " + error.getMessage());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}