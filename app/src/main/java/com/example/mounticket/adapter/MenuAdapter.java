package com.example.mounticket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.MainActivity;
import com.example.mounticket.R;
import com.example.mounticket.models.MenuModel;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuModel> menuList;
    private MainActivity mainActivity;

    // Listener untuk menangani klik pada menu
    public interface OnMenuItemClickListener {
        void onMenuItemClicked(String menuItemName, String jsonUrl);
    }

    // Konstruktor dengan listener sebagai parameter
    public MenuAdapter(List<MenuModel> menuList, MainActivity mainActivity) {
        this.menuList = menuList;
        this.mainActivity = mainActivity;
    }

    // Metode untuk mengatur listener
    public void setOnMenuItemClickListener(MainActivity listener) {
        this.mainActivity = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuModel menu = menuList.get(position);
        holder.menuIconImageView.setImageResource(menu.getIconResId());
        holder.menuNameTextView.setText(menu.getName());

        // Menambahkan OnClickListener ke setiap item menu
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memanggil metode onMenuItemClicked dari MainActivity
                mainActivity.onMenuItemClicked(menu.getName(), menu.getJsonUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView menuIconImageView;
        TextView menuNameTextView;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuIconImageView = itemView.findViewById(R.id.menuIconImageView);
            menuNameTextView = itemView.findViewById(R.id.menuNameTextView);
        }
    }
}