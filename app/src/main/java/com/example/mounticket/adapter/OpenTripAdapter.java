package com.example.mounticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.models.OpenTrip;

import java.util.List;

public class OpenTripAdapter extends RecyclerView.Adapter<OpenTripAdapter.ViewHolder> {

    private Context context;
    private List<OpenTrip> openTripList;

    public OpenTripAdapter(Context context, List<OpenTrip> openTripList) {
        this.context = context;
        this.openTripList = openTripList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_open_trip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OpenTrip openTrip = openTripList.get(position);
        holder.textViewTripName.setText(openTrip.getTripName());
        holder.textViewTripDescription.setText(openTrip.getTripDescription());
    }

    @Override
    public int getItemCount() {
        return openTripList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTripName;
        TextView textViewTripDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTripName = itemView.findViewById(R.id.textViewTripName);
            textViewTripDescription = itemView.findViewById(R.id.textViewTripDescription);
        }
    }
}