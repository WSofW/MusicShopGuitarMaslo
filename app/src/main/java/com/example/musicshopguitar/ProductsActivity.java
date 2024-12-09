package com.example.musicshopguitar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicshopguitar.adapters.ProductAdapter;
import com.example.musicshopguitar.models.Category;
import com.example.musicshopguitar.models.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    private RecyclerView productRecyclerView;
    private TextView categoryTitleTextView;
    private Button openCartButton;
    private ArrayList<Product> cart = new ArrayList<>();
    private UserPreferences userPreferences; // Для получения баланса

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        categoryTitleTextView = findViewById(R.id.categoryTitleTextView);
        productRecyclerView = findViewById(R.id.productRecyclerView);
        openCartButton = findViewById(R.id.openCartButton);

        // Инициализация UserPreferences для получения баланса
        userPreferences = new UserPreferences(this);

        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Получаем категорию из Intent
        Category category = (Category) getIntent().getSerializableExtra("category");
        if (category != null) {
            categoryTitleTextView.setText(category.getName());

            ProductAdapter adapter = new ProductAdapter(
                    this,
                    category.getProducts(),
                    product -> {
                        if (product.getQuantity() > 0) {
                            product.setQuantity(product.getQuantity() - 1);
                            cart.add(product);
                        }
                    },
                    this::openCart // Обработчик кнопки корзины
            );
            productRecyclerView.setAdapter(adapter);
        }

        // Настроить кнопку для перехода в корзину
        openCartButton.setOnClickListener(v -> openCart());
    }

    private void openCart() {
        // Передаем корзину и баланс в CartActivity
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("cartItems", (Serializable) cart);
        intent.putExtra("balance", userPreferences.getUserBalance()); // Передаем баланс
        startActivity(intent);
    }
}