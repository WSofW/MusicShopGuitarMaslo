package com.example.musicshopguitar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextAmount;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Инициализация элементов
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextAmount = findViewById(R.id.editTextAmount);

        // Инициализация UserPreferences
        userPreferences = new UserPreferences(this);
        if (userPreferences == null) {
            Toast.makeText(this, "Ошибка инициализации UserPreferences", Toast.LENGTH_LONG).show();
        }
    }

    public void onRegister(View view) {
        String username = editTextUsername.getText().toString().trim();
        String amountStr = editTextAmount.getText().toString().trim();

        if (!username.isEmpty() && !amountStr.isEmpty()) {
            try {
                // Преобразуем строку в число
                float amount = Float.parseFloat(amountStr);

                // Сохраняем данные
                userPreferences.saveUserName(username); // Сохраняем имя
                userPreferences.saveUserBalance(amount);  // Сохраняем сумму

                // Переход в LoginActivity
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Ошибка в вводе суммы. Пожалуйста, введите корректное число.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Введите имя пользователя и сумму", Toast.LENGTH_SHORT).show();
        }
    }
}
