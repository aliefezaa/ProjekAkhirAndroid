package com.example.mounticket.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mounticket.R;

public class SewaTravelOjekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_travel_ojek);

        // Ambil URL JSON dari intent
        String jsonUrl = getIntent().getStringExtra("jsonUrl");

        // Implementasi logika bisnis untuk aktivitas SewaTravelOjekActivity di sini
        // Contoh: Menampilkan URL JSON
        TextView textView = findViewById(R.id.textView);
        textView.setText("URL JSON: " + jsonUrl);
    }
}