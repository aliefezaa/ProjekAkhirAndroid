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
import com.example.mounticket.models.PorterGuide;

import java.util.List;

public class PorterGuideAdapter extends RecyclerView.Adapter<PorterGuideAdapter.ViewHolder> {

    private Context context;
    private List<PorterGuide> porterGuideList;

    public PorterGuideAdapter(Context context, List<PorterGuide> porterGuideList) {
        this.context = context;
        this.porterGuideList = porterGuideList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_porter_guide, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PorterGuide porterGuide = porterGuideList.get(position);
        holder.textViewName.setText(porterGuide.getName());
        holder.textViewMountain.setText("Gunung: " + porterGuide.getMountain());
        holder.textViewDescription.setText(porterGuide.getDescription());
        holder.textViewPhoneNumber.setText(porterGuide.getPhoneNumber());
        holder.textViewEmail.setText(porterGuide.getEmail());

        // Load profile image using Glide library
        Glide.with(context)
                .load(porterGuide.getProfileImage()) // URL gambar profil
                .placeholder(R.drawable.jane_profile) // Gambar default jika gagal memuat
                .error(R.drawable.john_profile) // Gambar default jika terjadi kesalahan
                .circleCrop() // Menggunakan bentuk lingkaran untuk gambar profil
                .into(holder.imageViewProfile);
    }

    @Override
    public int getItemCount() {
        return porterGuideList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewMountain;
        TextView textViewDescription;
        TextView textViewPhoneNumber;
        TextView textViewEmail;
        ImageView imageViewProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewMountain = itemView.findViewById(R.id.textViewMountain);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            imageViewProfile = itemView.findViewById(R.id.imageViewProfile);
        }
    }
}