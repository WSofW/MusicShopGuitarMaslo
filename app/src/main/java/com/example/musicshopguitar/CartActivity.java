package com.example.musicshopguitar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musicshopguitar.adapters.CartAdapter;
import com.example.musicshopguitar.models.Product;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private TextView cartBalanceTextView, totalCostTextView;
    private Button checkoutButton;

    private ArrayList<Product> cartItems;
    private float balance;
    private double totalCost = 0.0;

    private CartAdapter adapter;
    private UserPreferences userPreferences; // Для сохранения обновленного баланса

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Инициализация UI элементов
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartBalanceTextView = findViewById(R.id.cartBalanceTextView);
        totalCostTextView = findViewById(R.id.totalCostTextView);
        checkoutButton = findViewById(R.id.checkoutButton);

        // Инициализация UserPreferences для получения и сохранения баланса
        userPreferences = new UserPreferences(this);

        // Получаем данные из Intent
        balance = getIntent().getFloatExtra("balance", 0.0f);
        cartItems = (ArrayList<Product>) getIntent().getSerializableExtra("cartItems");

        // Рассчитываем общую стоимость
        calculateTotalCost();

        // Обновляем отображение баланса и общей стоимости
        updateCartDetails();

        // Инициализация адаптера для корзины
        adapter = new CartAdapter(this, cartItems, (product, position) -> {
            cartItems.remove(position); // Удаляем товар из корзины
            adapter.notifyItemRemoved(position); // Обновляем адаптер
            calculateTotalCost(); // Пересчитываем общую стоимость
            updateCartDetails(); // Обновляем отображение
        });

        // Настройка RecyclerView для отображения корзины
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(adapter);

        // Обработчик кнопки "Checkout"
        checkoutButton.setOnClickListener(v -> {
            if (balance >= totalCost) {
                // Если на балансе достаточно средств
                userPreferences.deductFromBalance((float) totalCost); // Снижаем баланс после покупки

                cartItems.clear(); // Очистить корзину
                adapter.notifyDataSetChanged(); // Обновить адаптер
                calculateTotalCost(); // Пересчитываем общую стоимость
                updateCartDetails(); // Обновляем отображение

                Toast.makeText(this, "Покупка совершена!", Toast.LENGTH_SHORT).show();
                finish(); // Закрыть корзину
            } else {
                Toast.makeText(this, "Недостаточно средств!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void calculateTotalCost() {
        totalCost = 0.0;
        for (Product product : cartItems) {
            totalCost += product.getPrice(); // Рассчитываем общую стоимость
        }
    }

    private void updateCartDetails() {
        cartBalanceTextView.setText(String.format("Ваш баланс составляет: %.2f₽", balance));
        totalCostTextView.setText(String.format("Всего к оплате: %.2f₽", totalCost));
        checkoutButton.setEnabled(!cartItems.isEmpty() && balance >= totalCost); // Активировать кнопку "Checkout" только если есть товары и достаточный баланс
    }
}