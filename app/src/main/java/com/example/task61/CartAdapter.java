package com.example.task61;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> Cart;
    private Context context;
    private OnRowClickListener listener;

    public CartAdapter(List<CartItem> Cart, Context context, OnRowClickListener clickListener) {
        this.Cart = Cart;
        this.context = context;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_row, parent, false);
        return new CartViewHolder(itemView, listener);
    }

    public interface OnRowClickListener {
        void onItemClick(int position);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView, quantityTextView, priceTextView;
        public OnRowClickListener onRowClickListener;

        public CartViewHolder(@NonNull View itemView, OnRowClickListener onRowClickListener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nametextView);
            quantityTextView = itemView.findViewById(R.id.quantitytextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem temp = Cart.get(position);
        holder.nameTextView.setText(temp.getItem_name());
        holder.quantityTextView.setText(String.valueOf(temp.getQuantity()));
        holder.priceTextView.setText(temp.getPrice());
    }

    @Override
    public int getItemCount() {
        if (Cart != null) {
            return Cart.size();
        } else {
            return 0;
        }
    }
}