package com.example.musicshopguitar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicshopguitar.R;
import com.example.musicshopguitar.models.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<Product> cartItems;
    private OnCartItemRemoveListener removeListener;

    // Интерфейс для обработки удаления элемента
    public interface OnCartItemRemoveListener {
        void onItemRemove(Product product, int position);
    }

    public CartAdapter(Context context, List<Product> cartItems, OnCartItemRemoveListener removeListener) {
        this.context = context;
        this.cartItems = cartItems;
        this.removeListener = removeListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.productNameTextView.setText(product.getName());
        holder.productPriceTextView.setText(String.format("%.2f₽", product.getPrice()));

        // Обработчик клика по тексту "Remove"
        holder.removeTextView.setOnClickListener(v -> {
            if (removeListener != null) {
                removeListener.onItemRemove(product, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView, productPriceTextView, removeTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            removeTextView = itemView.findViewById(R.id.removeTextView);
        }
    }
}
