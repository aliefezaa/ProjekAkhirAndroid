package com.example.mounticket.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mounticket.R;
import com.example.mounticket.adapter.MemberAdapter;
import com.example.mounticket.models.Member;
import com.example.mounticket.models.Mountain;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private Mountain selectedMountain;
    private ArrayList<Member> memberList;

    private TextView textViewMountainName;
    private TextView textViewMountainPrice;
    private TextView textViewTotalPrice;
    private RecyclerView recyclerViewSummary;
    private MemberAdapter memberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        textViewMountainName = findViewById(R.id.textViewMountainName);
        textViewMountainPrice = findViewById(R.id.textViewMountainPrice);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        recyclerViewSummary = findViewById(R.id.recyclerViewSummary);

        selectedMountain = getIntent().getParcelableExtra("selectedMountain");
        memberList = getIntent().getParcelableArrayListExtra("memberList");

        if (selectedMountain != null) {
            textViewMountainName.setText(selectedMountain.getName());
            textViewMountainPrice.setText("Price per Ticket: " + selectedMountain.getHarga());
        }

        recyclerViewSummary.setLayoutManager(new LinearLayoutManager(this));
        memberAdapter = new MemberAdapter(memberList, this);
        recyclerViewSummary.setAdapter(memberAdapter);

        calculateAndDisplayTotalPrice();
    }

    private void calculateAndDisplayTotalPrice() {
        double pricePerTicket = parsePrice(selectedMountain.getHarga());
        double totalPrice = pricePerTicket * memberList.size();

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        formatter.setMaximumFractionDigits(0);
        String formattedPrice = formatter.format(totalPrice);

        // Setelah memformat harga, atur ke textViewTotalPrice
        textViewTotalPrice.setText("Total Price: " + formattedPrice);
    }


    private double parsePrice(String price) {
        // Remove currency symbol and thousand separator
        price = price.replace("Rp", "").replace(".", "").trim();
        return Double.parseDouble(price);
    }
}