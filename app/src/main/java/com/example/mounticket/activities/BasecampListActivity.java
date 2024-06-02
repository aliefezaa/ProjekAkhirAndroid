package com.example.mounticket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.adapter.MountainAdapter;
import com.example.mounticket.database.DatabaseHelper;
import com.example.mounticket.models.Mountain;

import java.util.List;

public class BasecampListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basecamp_list);

        // Initialize Database Helper
        dbHelper = new DatabaseHelper(this);

        // Retrieve Mountain Data from Database
        List<Mountain> mountains = dbHelper.getAllMountains();

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MountainAdapter adapter = new MountainAdapter(mountains, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Logout Action
        if (id == R.id.action_logout) {
            // Redirect to LoginActivity and clear previous activities
            Intent intent = new Intent(BasecampListActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}