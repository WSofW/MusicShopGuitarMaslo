package com.example.musicshopguitar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicshopguitar.R;
import com.example.musicshopguitar.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> products;
    private OnProductClickListener productClickListener;
    private ArrayList<Product> cartItems = new ArrayList<>();
    private OnCartButtonClickListener cartButtonClickListener;

    public interface OnProductClickListener {
        void onAddToCartClick(Product product);
    }

    public interface OnCartButtonClickListener {
        void onCartButtonClick();
    }

    public ProductAdapter(Context context, List<Product> products, OnProductClickListener productClickListener, OnCartButtonClickListener cartButtonClickListener) {
        this.context = context;
        this.products = products;
        this.productClickListener = productClickListener;
        this.cartButtonClickListener = cartButtonClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productNameTextView.setText(product.getName());
        holder.brandTextView.setText(product.getBrand());
        holder.priceTextView.setText(product.getPrice() + "₽");
        holder.quantityTextView.setText("В наличии: " + product.getQuantity());
        holder.productImageView.setImageResource(product.getImageResourceId());

        holder.addToCartButton.setOnClickListener(v -> {
            cartItems.add(product);
            Toast.makeText(context, "Добавлено в корзину!", Toast.LENGTH_SHORT).show();
            if (productClickListener != null) {
                productClickListener.onAddToCartClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView, brandTextView, priceTextView, quantityTextView;
        ImageView productImageView;
        View addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            brandTextView = itemView.findViewById(R.id.brandTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}