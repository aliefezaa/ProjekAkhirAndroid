package com.example.mounticket.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.adapter.OpenTripAdapter;
import com.example.mounticket.models.OpenTrip;

import java.util.ArrayList;
import java.util.List;

public class OpenTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_trip);

        // Dummy data for demonstration
        List<OpenTrip> openTripList = new ArrayList<>();
        openTripList.add(new OpenTrip("Pendakian Gunung Semeru", "Open trip untuk pendakian ke Gunung Semeru dengan fasilitas lengkap."));
        openTripList.add(new OpenTrip("Pendakian Gunung Rinjani", "Open trip ke Gunung Rinjani dengan pemandangan alam yang menakjubkan."));
        openTripList.add(new OpenTrip("Pendakian Gunung Bromo", "Open trip ke Gunung Bromo untuk menyaksikan keindahan matahari terbit."));
        openTripList.add(new OpenTrip("Pendakian Gunung Merapi", "Open trip ke Gunung Merapi untuk mengenal aktivitas vulkanik dan kehidupan lokal."));

        RecyclerView recyclerView = findViewById(R.id.openTripRecyclerView);
        OpenTripAdapter adapter = new OpenTripAdapter(this, openTripList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}