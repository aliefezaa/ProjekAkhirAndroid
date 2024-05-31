package com.example.mounticket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mounticket.adapter.MenuAdapter;
import com.example.mounticket.adapter.MountainAdapter;
import com.example.mounticket.models.MenuModel;
import com.example.mounticket.models.Mountain;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnMenuItemClickListener {

    private RecyclerView recyclerView;
    private MountainAdapter mountainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.newsRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // Menggunakan GridLayoutManager dengan spanCount 3

        setupMenuRecyclerView();
    }

    private void setupMenuRecyclerView() {
        List<MenuModel> menuList = createMenuList();
        RecyclerView menuRecyclerView = findViewById(R.id.menuRecyclerView);
        MenuAdapter menuAdapter = new MenuAdapter(menuList, this);

        // Menggunakan GridLayoutManager dengan spanCount 3
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        menuRecyclerView.setLayoutManager(layoutManager);

        menuRecyclerView.setAdapter(menuAdapter);
    }

    private List<MenuModel> createMenuList() {
        List<MenuModel> menuList = new ArrayList<>();
        menuList.add(new MenuModel(R.drawable.beli_tiket_masuk_icon, "Beli Tiket Masuk", "https://example.com/beli_tiket_masuk.json"));
        menuList.add(new MenuModel(R.drawable.sewa_travel_ojek_icon, "Sewa Travel/Ojek", "https://example.com/sewa_travel_ojek.json"));
        menuList.add(new MenuModel(R.drawable.sewa_porter_guide_icon, "Sewa Porter/Guide", "https://example.com/sewa_porter_guide.json"));
        menuList.add(new MenuModel(R.drawable.sewa_alat_icon, "Sewa Alat", "https://example.com/sewa_alat.json"));
        menuList.add(new MenuModel(R.drawable.informasi_open_trip_icon, "Informasi Open Trip", "https://example.com/informasi_open_trip.json"));
        menuList.add(new MenuModel(R.drawable.informasi_basecamp_icon, "Informasi Basecamp", "https://example.com/informasi_basecamp.json"));
        return menuList;
    }

    @Override
    public void onMenuItemClicked(String menuItemName, String jsonUrl) {
        if (menuItemName.equals("Beli Tiket Masuk") || menuItemName.equals("Sewa Travel/Ojek")) {
            loadDataFromURL(jsonUrl);
        }
        // Implement logic for other menu items if needed
    }

    private void loadDataFromURL(String jsonUrl) {
        new LoadDataFromURLTask().execute(jsonUrl);
    }

    private List<Mountain> fetchDataFromURL(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                String json = stringBuilder.toString();
                Gson gson = new Gson();
                Mountain[] mountainArray = gson.fromJson(json, Mountain[].class);
                List<Mountain> mountainList = new ArrayList<>();
                for (Mountain mountain : mountainArray) {
                    mountainList.add(mountain);
                }
                return mountainList;
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class LoadDataFromURLTask extends AsyncTask<String, Void, List<Mountain>> {
        @Override
        protected List<Mountain> doInBackground(String... urls) {
            String urlString = urls[0];
            return fetchDataFromURL(urlString);
        }

        @Override
        protected void onPostExecute(List<Mountain> mountainList) {
            if (mountainList != null) {
                mountainAdapter = new MountainAdapter(mountainList);
                recyclerView.setAdapter(mountainAdapter);
            } else {
                Toast.makeText(MainActivity.this, "Failed to load data from URL", Toast.LENGTH_SHORT).show();
            }
        }
    }
}