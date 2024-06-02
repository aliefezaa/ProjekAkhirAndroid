package com.example.mounticket.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mounticket.R;
import com.example.mounticket.adapter.BasecampAdapter;
import com.example.mounticket.adapter.MountainAdapter;
import com.example.mounticket.models.Basecamp;
import com.example.mounticket.models.Mountain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InfoBasecampActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BasecampAdapter basecampAdapter;
    private List<Basecamp> basecampList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_basecamp);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        basecampList = new ArrayList<>();
        basecampAdapter = new BasecampAdapter(basecampList);
        recyclerView.setAdapter(basecampAdapter);

        // Fetch data from JSON and update the list
        fetchMountainData();
    }

    private void fetchMountainData() {
        String url = "https://aliefezaa.github.io/ProjekAkhirAndroid/basecamp.json";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String mt = jsonObject.getString("mt");
                        String commodity = jsonObject.getString("commodity");
                        String distance = jsonObject.getString("distance");

                        Basecamp basecamp = new Basecamp(id, mt, commodity, distance);
                        basecampList.add(basecamp);
                    }

                    basecampAdapter.notifyDataSetChanged();
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
