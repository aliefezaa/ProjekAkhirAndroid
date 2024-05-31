package com.example.mounticket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.models.Mountain;

import java.util.List;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {

    private List<Mountain> mountainList;

    public MountainAdapter(List<Mountain> mountainList) {
        this.mountainList = mountainList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mountain, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mountain mountain = mountainList.get(position);
        holder.mountainNameTextView.setText(mountain.getName());
        holder.mountainHeightTextView.setText(String.valueOf(mountain.getHeight()));
    }

    @Override
    public int getItemCount() {
        return mountainList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mountainNameTextView;
        TextView mountainHeightTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mountainNameTextView = itemView.findViewById(R.id.mountainNameTextView);
            mountainHeightTextView = itemView.findViewById(R.id.mountainHeightTextView);
        }
    }
}