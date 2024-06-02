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
import java.util.List;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.MountainViewHolder> {

    private List<Mountain> mountainList;
    private Context context;
    private OnItemClickListener mListener;

    public MountainAdapter(List<Mountain> mountainList, Context context) {
        this.mountainList = mountainList;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MountainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mountain, parent, false);
        return new MountainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MountainViewHolder holder, int position) {
        Mountain currentMountain = mountainList.get(position);
        holder.bind(currentMountain);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION && mListener != null) {
                    mListener.onItemClick(clickedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mountainList.size();
    }

    class MountainViewHolder extends RecyclerView.ViewHolder {
        private ImageView mountainImageView;
        private TextView mountainNameTextView;
        private TextView mountainAlamatTextView;
        private TextView mountainHeightTextView;
        private TextView mountainHargaTextView;

        MountainViewHolder(@NonNull View itemView) {
            super(itemView);
            mountainImageView = itemView.findViewById(R.id.mountainImageView);
            mountainNameTextView = itemView.findViewById(R.id.mountainNameTextView);
            mountainAlamatTextView = itemView.findViewById(R.id.mountainAlamatTextView);
            mountainHeightTextView = itemView.findViewById(R.id.mountainHeightTextView);
            mountainHargaTextView = itemView.findViewById(R.id.mountainHargaTextView);
        }

        void bind(Mountain mountain) {
            mountainNameTextView.setText(mountain.getName());
            mountainAlamatTextView.setText(mountain.getAlamat());
            mountainHeightTextView.setText(mountain.getHeight());
            mountainHargaTextView.setText(mountain.getHarga());

            Glide.with(context)
                    .load(mountain.getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(mountainImageView);
        }
    }
}