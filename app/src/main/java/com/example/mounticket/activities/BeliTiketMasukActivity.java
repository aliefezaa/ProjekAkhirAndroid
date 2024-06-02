package com.example.mounticket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.example.mounticket.adapter.MountainAdapter;
import com.example.mounticket.models.Mountain;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class BeliTiketMasukActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MountainAdapter mountainAdapter;
    private List<Mountain> mountainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beli_tiket_masuk);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mountainList = new ArrayList<>();
        mountainAdapter = new MountainAdapter(mountainList, this);
        recyclerView.setAdapter(mountainAdapter);

        fetchMountainData();

        // Tangani klik tombol kembali
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Atau finish() untuk menutup activity
            }
        });

        mountainAdapter.setOnItemClickListener(new MountainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Mountain selectedMountain = mountainList.get(position);

                Intent intent = new Intent(BeliTiketMasukActivity.this, FormActivity.class);
                intent.putExtra("selectedMountain", selectedMountain);
                startActivity(intent);
            }
        });
    }

    private void fetchMountainData() {
        String url = "https://aliefezaa.github.io/ProjekAkhirAndroid/mountain.json";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String alamat = jsonObject.getString("alamat");
                        String height = jsonObject.getString("height");
                        String image = jsonObject.getString("image");
                        String harga = jsonObject.getString("harga");

                        Mountain mountain = new Mountain(id, name, alamat, height, image, harga);
                        mountainList.add(mountain);
                    }

                    mountainAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSON", "Error parsing JSON: " + e.getMessage());
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
