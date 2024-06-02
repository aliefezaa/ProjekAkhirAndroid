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
import com.example.mounticket.models.Basecamp;
import com.example.mounticket.models.Mountain;
import java.util.List;

public class BasecampAdapter extends RecyclerView.Adapter<BasecampAdapter.BasecampViewHolder> {

    private List<Basecamp> basecampList;
    private Context context;

    public BasecampAdapter(List<Basecamp> basecampList) { this.basecampList = basecampList;}

    @NonNull
    @Override
    public BasecampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_basecamp, parent, false);
        return new BasecampViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasecampViewHolder holder, int position) {
        Basecamp basecamp = basecampList.get(position);
        holder.bind(basecamp);
    }

    @Override
    public int getItemCount() {
        return basecampList.size();
    }

    class BasecampViewHolder extends RecyclerView.ViewHolder {
        private TextView basecampMtTextView;
        private TextView basecampCommodityTextView;
        private TextView basecampDistanceTextView;

        BasecampViewHolder(@NonNull View itemView) {
            super(itemView);
            basecampMtTextView = itemView.findViewById(R.id.mtTextview);
            basecampCommodityTextView = itemView.findViewById(R.id.basecampCommodityTextView);
            basecampDistanceTextView = itemView.findViewById(R.id.basecampDistanceTextView);
        }

        void bind(Basecamp basecamp) {
            basecampMtTextView.setText(basecamp.getMt());
            basecampCommodityTextView.setText(basecamp.getCommodity());
            basecampDistanceTextView.setText(basecamp.getDistance());

        }
    }
}