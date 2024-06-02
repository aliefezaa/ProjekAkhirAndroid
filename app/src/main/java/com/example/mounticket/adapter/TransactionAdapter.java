package com.example.mounticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mounticket.R;
import com.example.mounticket.database.DatabaseHelper;
import com.example.mounticket.models.Transaction;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private Context mContext;
    private List<Transaction> mTransactionList;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        mContext = context;
        mTransactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = mTransactionList.get(position);
        holder.bind(transaction);

        // Set up the context menu for each item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, v);
                popup.getMenuInflater().inflate(R.menu.menu_transaction_item, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.action_delete) {
                            // Hapus transaksi
                            int adapterPosition = holder.getAdapterPosition();
                            if (adapterPosition != RecyclerView.NO_POSITION) {
                                deleteTransaction(adapterPosition);
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                popup.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTransactionList.size();
    }

    public void deleteTransaction(int position) {
        // Get the transaction to be deleted
        Transaction transactionToDelete = mTransactionList.get(position);

        // Delete the transaction from the database
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        dbHelper.deleteAllTransactions(); // This will delete all transactions from the database

        // Clear the list since all transactions are deleted from the database
        mTransactionList.clear();

        // Notify the adapter that the dataset has changed
        notifyDataSetChanged();
    }
    public void updateData(List<Transaction> transactions) {
        mTransactionList.clear();
        mTransactionList.addAll(transactions);
        notifyDataSetChanged();
    }
    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewDescription;
        private TextView mTextViewAmount;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewDescription = itemView.findViewById(R.id.textViewDescription);
            mTextViewAmount = itemView.findViewById(R.id.textViewAmount);
        }

        public void bind(Transaction transaction) {
            mTextViewDescription.setText(transaction.getDescription());

            // Format the amount using the currency format
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            formatter.setMaximumFractionDigits(0);
            String formattedAmount = formatter.format(transaction.getAmount());
            mTextViewAmount.setText(formattedAmount);
        }
    }
}