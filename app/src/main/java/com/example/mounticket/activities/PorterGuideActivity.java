package com.example.mounticket.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.adapter.PorterGuideAdapter;
import com.example.mounticket.models.PorterGuide;

import java.util.ArrayList;
import java.util.List;

public class PorterGuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_guide);

        // Dummy data for demonstration
        List<PorterGuide> porterGuideList = new ArrayList<>();
        porterGuideList.add(new PorterGuide("John Doe", "Gunung Semeru", "John adalah seorang porter berpengalaman yang telah bekerja di Gunung Semeru selama lebih dari 10 tahun. Dia memiliki pengetahuan mendalam tentang rute pendakian dan dapat membantu pendaki dengan segala kebutuhan mereka.", R.drawable.john_profile, "+1234567890", "john@example.com"));
        porterGuideList.add(new PorterGuide("Jane Smith", "Gunung Rinjani", "Jane adalah seorang guide bersertifikasi yang ahli dalam memandu pendaki di Gunung Rinjani. Dia memiliki keahlian komunikasi yang baik dan dapat memberikan informasi yang jelas tentang jalur pendakian dan keindahan alam sekitar.", R.drawable.jane_profile, "+1234567891", "jane@example.com"));
        porterGuideList.add(new PorterGuide("Michael Johnson", "Gunung Merapi", "Michael adalah seorang porter yang terkenal dengan kemampuannya dalam membawa beban berat melintasi lereng Gunung Merapi. Dia sangat tangguh dan bertanggung jawab dalam tugasnya sebagai porter.", R.drawable.michael_profile, "+1234567892", "michael@example.com"));
        porterGuideList.add(new PorterGuide("Emily Brown", "Gunung Bromo", "Emily adalah seorang guide yang ramah dan berpengalaman dalam menjelaskan tentang keindahan Gunung Bromo kepada pendaki. Dia selalu siap membantu pendaki yang membutuhkan panduan selama perjalanan mereka.", R.drawable.emily_profile, "+1234567893", "emily@example.com"));

        RecyclerView recyclerView = findViewById(R.id.porterGuideRecyclerView);
        PorterGuideAdapter adapter = new PorterGuideAdapter(this, porterGuideList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}