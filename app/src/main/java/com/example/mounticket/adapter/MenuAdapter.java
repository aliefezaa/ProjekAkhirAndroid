package com.example.mounticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.models.MenuModel;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuModel> menuList;
    private OnMenuItemClickListener listener;
    private Context context;

    public MenuAdapter(List<MenuModel> menuList, OnMenuItemClickListener listener) {
        this.menuList = menuList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuModel menu = menuList.get(position);
        holder.bind(menu);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClicked(String menuItemName, String jsonUrl);
    }

    class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iconImageView;
        private TextView titleTextView;
        private MenuModel menu;

        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.iconImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            itemView.setOnClickListener(this);
        }

        void bind(MenuModel menu) {
            this.menu = menu;
            iconImageView.setImageResource(menu.getIconResource());
            titleTextView.setText(menu.getTitle());
        }

        @Override
        public void onClick(View v) {
            listener.onMenuItemClicked(menu.getTitle(), menu.getJsonUrl());
        }
    }
}