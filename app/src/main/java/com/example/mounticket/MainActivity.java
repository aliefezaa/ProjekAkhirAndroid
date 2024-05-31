package com.example.mounticket;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.mounticket.activities.LoginActivity;
import com.example.mounticket.adapter.BannerAdapter;
import com.example.mounticket.adapter.MenuAdapter;
import com.example.mounticket.adapter.MountainAdapter;
import com.example.mounticket.adapter.NewsAdapter;
import com.example.mounticket.models.BannerItem;
import com.example.mounticket.models.MenuModel;
import com.example.mounticket.models.Mountain;
import com.example.mounticket.models.News;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnMenuItemClickListener {

    private ViewPager2 bannerViewPager;
    private RecyclerView menuRecyclerView;
    private RecyclerView newsRecyclerView;

    private BannerAdapter bannerAdapter;
    private NewsAdapter newsAdapter;
    private MountainAdapter mountainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bannerViewPager = findViewById(R.id.bannerViewPager);
        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        newsRecyclerView = findViewById(R.id.newsRecyclerView);

        setupBanner();
        setupMenuRecyclerView();
        setupNewsRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupBanner() {
        List<BannerItem> bannerItems = fetchBannerData();
        bannerAdapter = new BannerAdapter(bannerItems);
        bannerViewPager.setAdapter(bannerAdapter);
    }

    private List<BannerItem> fetchBannerData() {
        List<BannerItem> bannerItems = new ArrayList<>();
        bannerItems.add(new BannerItem(R.drawable.banner_1, "Banner 1"));
        bannerItems.add(new BannerItem(R.drawable.banner_2, "Banner 2"));
        bannerItems.add(new BannerItem(R.drawable.banner_3, "Banner 3"));
        return bannerItems;
    }

    private void setupMenuRecyclerView() {
        List<MenuModel> menuList = createMenuList();
        MenuAdapter menuAdapter = new MenuAdapter(menuList, this);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        menuRecyclerView.setAdapter(menuAdapter);
    }

    private void setupNewsRecyclerView() {
        List<News> newsList = fetchNewsData();
        newsAdapter = new NewsAdapter(newsList);
        newsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        newsRecyclerView.setAdapter(newsAdapter);
    }

    private List<News> fetchNewsData() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(R.drawable.news_1, "Berita 1", "Deskripsi Berita 1"));
        newsList.add(new News(R.drawable.news_2, "Berita 2", "Deskripsi Berita 2"));
        newsList.add(new News(R.drawable.news_3, "Berita 3", "Deskripsi Berita 3"));
        return newsList;
    }

    private List<MenuModel> createMenuList() {
        List<MenuModel> menuList = new ArrayList<>();
        menuList.add(new MenuModel(R.drawable.beli_tiket_masuk_icon, "Beli Tiket Masuk", "https://example.com/beli_tiket_masuk.json"));
        menuList.add(new MenuModel(R.drawable.sewa_travel_ojek_icon, "Sewa Travel/Ojek", "https://example.com/sewa_travel_ojek.json"));
        menuList.add(new MenuModel(R.drawable.sewa_porter_guide_icon, "Sewa Porter/Guide", "https://example.com/sewa_porter_guide.json"));
        menuList.add(new MenuModel(R.drawable.sewa_alat_icon, "Sewa Alat Pendakian", "https://example.com/sewa_alat.json"));
        menuList.add(new MenuModel(R.drawable.informasi_open_trip_icon, "Informasi Open Trip", "https://example.com/informasi_open_trip.json"));
        menuList.add(new MenuModel(R.drawable.informasi_basecamp_icon, "Informasi Basecamp", "https://example.com/informasi_basecamp.json"));
        return menuList;
    }

    @Override
    public void onMenuItemClicked(String menuItemName, String jsonUrl) {
        if (menuItemName.equals("Beli Tiket Masuk") || menuItemName.equals("Sewa Travel/Ojek")) {
            loadDataFromURL(jsonUrl);
        }
    }

    private void loadDataFromURL(String jsonUrl) {
        new LoadDataFromURLTask().execute(jsonUrl);
    }

    private class LoadDataFromURLTask extends AsyncTask<String, Void, List<Mountain>> {
        @Override
        protected List<Mountain> doInBackground(String... urls) {
            String urlString = urls[0];
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    String json = stringBuilder.toString();
                    return parseJsonToMountainList(json);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Mountain> mountainList) {
            if (mountainList != null) {
                mountainAdapter = new MountainAdapter(mountainList);
                newsRecyclerView.setAdapter(mountainAdapter);
            } else {
                Toast.makeText(MainActivity.this, "Failed to load data from URL", Toast.LENGTH_SHORT).show();
            }
        }

        private List<Mountain> parseJsonToMountainList(String json) throws JSONException {
            JSONArray jsonArray = new JSONArray(json);
            Gson gson = new Gson();
            List<Mountain> mountainList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Mountain mountain = gson.fromJson(jsonArray.getJSONObject(i).toString(), Mountain.class);
                mountainList.add(mountain);
            }
            return mountainList;
        }
    }
}



