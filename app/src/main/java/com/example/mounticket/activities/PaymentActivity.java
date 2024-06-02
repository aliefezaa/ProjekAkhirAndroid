package com.example.mounticket.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mounticket.MainActivity;
import com.example.mounticket.R;
import com.example.mounticket.database.DatabaseHelper;

public class PaymentActivity extends AppCompatActivity {

    private TextView textViewPaymentStatus;
    private ProgressBar progressBar;
    private double totalPrice;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        textViewPaymentStatus = findViewById(R.id.textViewPaymentStatus);
        progressBar = findViewById(R.id.progressBar);
        dbHelper = new DatabaseHelper(this);

        totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);

        startPayment();
    }

    private void startPayment() {
        progressBar.setVisibility(View.VISIBLE);
        textViewPaymentStatus.setText("Processing payment...");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                boolean isSuccess = simulatePayment();
                showTransactionResult(isSuccess);
            }
        }, 3000);
    }

    private boolean simulatePayment() {
        return Math.random() < 0.8;
    }

    private void showTransactionResult(boolean isSuccess) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(isSuccess ? "Transaction Success" : "Transaction Failed");
        builder.setMessage(isSuccess ? "Your payment was successful." : "Your payment failed. Please try again.");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.setOnDismissListener(dialogInterface -> {
            textViewPaymentStatus.setText(isSuccess ? "Transaction Success" : "Transaction Failed");

            if (isSuccess) {
                long transactionId = dbHelper.insertTransaction("Payment for ticket", totalPrice);
                if (transactionId != -1) {
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }, 1000);
                }
            } else {
                new Handler().postDelayed(() -> {
                    finish();
                }, 1000);
            }
        });
    }
}