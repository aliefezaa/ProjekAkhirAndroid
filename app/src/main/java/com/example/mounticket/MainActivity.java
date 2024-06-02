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
import com.example.mounticket.activities.InfoBasecampActivity;
import com.example.mounticket.activities.InfoRentActivity;
import com.example.mounticket.activities.OpenTripActivity;
import com.example.mounticket.activities.TransactionHistoryActivity;
import com.example.mounticket.activities.TravelOjekActivity;
import com.example.mounticket.activities.PorterGuideActivity;
import com.example.mounticket.activities.OpenTripActivity;
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
        getSupportActionBar().setTitle("Halo! Selamat Datang");

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
        int id = item.getItemId();

        if (id == R.id.action_transaction_history) {
            Intent intent = new Intent(MainActivity.this, TransactionHistoryActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_logout) {
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
        newsList.add(new News(R.drawable.news_1, "Pendakian Gunung Semeru Dibuka Kembali", "Setelah beberapa bulan ditutup karena kondisi cuaca ekstrem, pendakian Gunung Semeru kembali dibuka untuk umum mulai pekan depan. Para pendaki diharapkan tetap mematuhi protokol keamanan yang telah ditetapkan."));
        newsList.add(new News(R.drawable.news_2, "Gunung Merapi Meningkatkan Status", "Aktivitas vulkanik Gunung Merapi meningkat, menyebabkan pihak berwenang meningkatkan status menjadi siaga. Warga di sekitar daerah tersebut diminta untuk waspada dan mempersiapkan diri terhadap kemungkinan erupsi."));
        newsList.add(new News(R.drawable.news_3, "Keindahan Gunung Bromo di Pagi Hari", "Gunung Bromo kembali menjadi destinasi favorit para wisatawan. Pemandangan matahari terbit di Bromo menarik ribuan pengunjung setiap bulannya. Pastikan untuk mengunjungi saat kondisi cuaca baik untuk mendapatkan pengalaman terbaik."));
        newsList.add(new News(R.drawable.news_4, "Gunung Rinjani Jadi Favorit Pendaki Internasional", "Gunung Rinjani di Lombok menjadi salah satu gunung favorit pendaki internasional. Dengan pemandangan yang menakjubkan dan trek yang menantang, Rinjani menawarkan pengalaman pendakian yang tak terlupakan."));
        newsList.add(new News(R.drawable.news_5, "Festival Tahunan di Gunung Kerinci", "Festival tahunan di Gunung Kerinci akan diadakan bulan depan. Acara ini akan menampilkan berbagai kegiatan menarik seperti pendakian massal, lomba fotografi alam, dan pertunjukan budaya lokal."));
        return newsList;
    }


    @Override
    public void onMenuItemClicked(String menuItemName, String jsonUrl) {
        Intent intent;
        switch (menuItemName) {
            case "Tiket Masuk":
                intent = new Intent(this, BeliTiketMasukActivity.class);
                break;
            case "Basecamp":
                intent = new Intent(this, InfoBasecampActivity.class);
                break;  // Menambahkan break statement
            case "Alat Pendakian":
                intent = new Intent(this, InfoRentActivity.class);
                break;  // Menambahkan break statement
            case "Travel/Ojek":
                intent = new Intent(this, TravelOjekActivity.class);
                break;
            case "Porter/Guide":
                intent = new Intent(this, PorterGuideActivity.class);
                break;
            case "Open Trip":
                intent = new Intent(this, OpenTripActivity.class);
                break;
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