package com.example.mounticket.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.adapter.TravelOjekAdapter;
import com.example.mounticket.models.TravelOjek;

import java.util.ArrayList;
import java.util.List;

public class TravelOjekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_ojek);

        // Data unik untuk layanan travel ojek
        List<TravelOjek> travelOjekList = new ArrayList<>();
        travelOjekList.add(new TravelOjek("Pohon Ojek", "Layanan ojek online yang menyediakan perjalanan ke gunung dengan kuda terbang.", "0812-3456-7890"));
        travelOjekList.add(new TravelOjek("Naga Transport", "Platform transportasi online yang menawarkan perjalanan ke gunung dengan naga sebagai kendaraan utama.", "0856-7890-1234"));
        travelOjekList.add(new TravelOjek("Tikus Travel", "Aplikasi travel yang menyediakan layanan perjalanan ke gunung dengan tikus terlatih sebagai pengemudi.", "0878-1234-5678"));

        RecyclerView recyclerView = findViewById(R.id.travelOjekRecyclerView);
        TravelOjekAdapter adapter = new TravelOjekAdapter(this, travelOjekList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}