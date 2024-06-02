    package com.example.mounticket.adapter;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.TextView;
    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;
    import com.example.mounticket.R;
    import com.example.mounticket.models.Member;
    import java.util.ArrayList;

    public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

        private ArrayList<Member> memberList;
        private OnItemClickListener onItemClickListener;

        public MemberAdapter(ArrayList<Member> memberList, Context context) {
            this.memberList = memberList;
        }

        public interface OnItemClickListener {
            void onItemClick(int position);
            void onEditClick(int position);
            void onDeleteClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.onItemClickListener = listener;
        }

        @NonNull
        @Override
        public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
            return new MemberViewHolder(itemView, onItemClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
            Member currentMember = memberList.get(position);
            holder.nameTextView.setText(currentMember.getName());
            holder.ageTextView.setText(String.valueOf(currentMember.getAge()));
            holder.phoneTextView.setText(currentMember.getPhone());
        }

        @Override
        public int getItemCount() {
            return memberList.size();
        }

        public static class MemberViewHolder extends RecyclerView.ViewHolder {
            public TextView nameTextView;
            public TextView ageTextView;
            public TextView phoneTextView;
            public Button editButton;
            public Button deleteButton;

            public MemberViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
                super(itemView);
                nameTextView = itemView.findViewById(R.id.nameTextView);
                ageTextView = itemView.findViewById(R.id.ageTextView);
                phoneTextView = itemView.findViewById(R.id.phoneTextView);
                editButton = itemView.findViewById(R.id.editButton);
                deleteButton = itemView.findViewById(R.id.deleteButton);

                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onEditClick(position);
                            }
                        }
                    }
                });

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onDeleteClick(position);
                            }
                        }
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onItemClick(position);
                            }
                        }
                    }
                });
            }
        }
    }