package com.example.mounticket.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mounticket.R;

public class SewaPorterGuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_porter_guide);

        // Ambil URL JSON dari intent
        String jsonUrl = getIntent().getStringExtra("jsonUrl");

        // Implementasi logika bisnis untuk aktivitas SewaPorterGuideActivity di sini
        // Contoh: Menampilkan URL JSON
        TextView textView = findViewById(R.id.textView);
        textView.setText("URL JSON: " + jsonUrl);
    }
}