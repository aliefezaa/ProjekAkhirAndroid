package com.example.mounticket;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.activities.BeliTiketMasukActivity;
import com.example.mounticket.activities.LoginActivity;
import com.example.mounticket.activities.SewaPorterGuideActivity;
import com.example.mounticket.activities.SewaTravelOjekActivity;
import com.example.mounticket.adapter.MenuAdapter;
import com.example.mounticket.adapter.NewsAdapter;
import com.example.mounticket.models.MenuModel;
import com.example.mounticket.models.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnMenuItemClickListener {

    private RecyclerView menuRecyclerView;
    private RecyclerView newsRecyclerView;

    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        newsRecyclerView = findViewById(R.id.newsRecyclerView);

        new SetupMenuTask().execute();
        setupNewsRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    private void setupMenuRecyclerView(List<MenuModel> menuList) {
        MenuAdapter menuAdapter = new MenuAdapter(menuList, this);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setAdapter(menuAdapter);
    }

    private void setupNewsRecyclerView() {
        List<News> newsList = fetchNewsData();
        newsAdapter = new NewsAdapter(newsList);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setAdapter(newsAdapter);
    }

    private List<News> fetchNewsData() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(R.drawable.news_1, "Berita 1", "Deskripsi Berita 1"));
        newsList.add(new News(R.drawable.news_2, "Berita 2", "Deskripsi Berita 2"));
        newsList.add(new News(R.drawable.news_3, "Berita 3", "Deskripsi Berita 3"));
        return newsList;
    }

    @Override
    public void onMenuItemClicked(String menuItemName, String jsonUrl) {
        Intent intent;
        switch (menuItemName) {
            case "Tiket Masuk":
                intent = new Intent(this, BeliTiketMasukActivity.class);
                break;
            // Tambahkan case untuk menu lain jika diperlukan
            default:
                Toast.makeText(this, "Menu not implemented yet", Toast.LENGTH_SHORT).show();
                return;
        }
        intent.putExtra("jsonUrl", jsonUrl);
        startActivity(intent);
    }



    private class SetupMenuTask extends AsyncTask<Void, Void, List<MenuModel>> {

        @Override
        protected List<MenuModel> doInBackground(Void... voids) {
            return createMenuList();
        }

        @Override
        protected void onPostExecute(List<MenuModel> menuList) {
            super.onPostExecute(menuList);
            setupMenuRecyclerView(menuList);
        }
    }

    private List<MenuModel> createMenuList() {
        List<MenuModel> menuList = new ArrayList<>();
        menuList.add(new MenuModel(R.drawable.beli_tiket_masuk_icon, "Tiket Masuk", "https://aliefezaa.github.io/ProjekAkhirAndroid/mountain.json"));
        menuList.add(new MenuModel(R.drawable.sewa_travel_ojek_icon, "Travel/Ojek", "https://example.com/sewa_travel_ojek.json"));
        menuList.add(new MenuModel(R.drawable.sewa_porter_guide_icon, "Porter/Guide", "https://example.com/sewa_porter_guide.json"));
        menuList.add(new MenuModel(R.drawable.sewa_alat_icon, "Alat Pendakian", "https://example.com/sewa_alat.json"));
        menuList.add(new MenuModel(R.drawable.informasi_open_trip_icon, "Open Trip", "https://example.com/informasi_open_trip.json"));
        menuList.add(new MenuModel(R.drawable.informasi_basecamp_icon, "Basecamp", "https://example.com/informasi_basecamp.json"));
        return menuList;
    }
}