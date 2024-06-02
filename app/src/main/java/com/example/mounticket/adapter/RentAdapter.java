package com.example.mounticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mounticket.R;
import com.example.mounticket.models.Mountain;
import com.example.mounticket.models.Rent;

import java.util.List;

public class RentAdapter extends RecyclerView.Adapter<RentAdapter.RentViewHolder> {

    private List<Rent> rentList;
    private Context context;

    public RentAdapter(List<Rent> rentList) {
        this.rentList = rentList;
    }

    @NonNull
    @Override
    public RentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_rent, parent, false);
        return new RentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentViewHolder holder, int position) {
        Rent rent = rentList.get(position);
        holder.bind(rent);
    }

    @Override
    public int getItemCount() {
        return rentList.size();
    }

    class RentViewHolder extends RecyclerView.ViewHolder {
        private ImageView rentImgalatView;
        private TextView rentShopTextView;
        private TextView rentLocationTextView;
        private TextView rentRentableTextView;
        private TextView rentKontakTextView;

        RentViewHolder(@NonNull View itemView) {
            super(itemView);
            rentImgalatView = itemView.findViewById(R.id.rentImageView);
            rentShopTextView = itemView.findViewById(R.id.rentShopTextView);
            rentLocationTextView = itemView.findViewById(R.id.rentLocationTextView);
            rentRentableTextView = itemView.findViewById(R.id.rentRentableTextView);
            rentKontakTextView = itemView.findViewById(R.id.rentKontakTextView);
        }

        void bind(Rent rent) {
            rentShopTextView.setText(rent.getShop());
            rentLocationTextView.setText(rent.getLocation());
            rentRentableTextView.setText(rent.getRentable());
            rentKontakTextView.setText(rent.getKontak());

            Glide.with(context)
                    .load(rent.getImgalat())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(rentImgalatView);
        }
    }
}