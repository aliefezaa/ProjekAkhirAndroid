package com.example.mounticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.models.TravelOjek;

import java.util.List;

public class TravelOjekAdapter extends RecyclerView.Adapter<TravelOjekAdapter.ViewHolder> {

    private Context context;
    private List<TravelOjek> travelOjekList;

    public TravelOjekAdapter(Context context, List<TravelOjek> travelOjekList) {
        this.context = context;
        this.travelOjekList = travelOjekList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_travel_ojek, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TravelOjek travelOjek = travelOjekList.get(position);
        holder.textViewName.setText(travelOjek.getName());
        holder.textViewDescription.setText(travelOjek.getDescription());
        holder.textViewPhoneNumber.setText(travelOjek.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return travelOjekList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        TextView textViewPhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
        }
    }
}