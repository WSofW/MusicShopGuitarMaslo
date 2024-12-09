package com.example.musicshopguitar;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    private SharedPreferences sharedPreferences;

    // Конструктор для инициализации SharedPreferences
    public UserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
    }

    // Сохранение имени пользователя
    public void saveUserName(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

    // Получение имени пользователя
    public String getUserName() {
        return sharedPreferences.getString("username", null); // Возвращаем сохраненное имя пользователя
    }

    // Сохранение баланса пользователя
    public void saveUserBalance(float amount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("amount", amount); // Сохраняем новый баланс
        editor.apply();
    }

    // Получение баланса пользователя
    public float getUserBalance() {
        return sharedPreferences.getFloat("amount", 0.0f); // Возвращаем сохраненный баланс или 0.0f, если он не установлен
    }

    // Метод для увеличения баланса на определенную сумму
    public void addToBalance(float amount) {
        float currentBalance = getUserBalance();
        saveUserBalance(currentBalance + amount); // Обновляем баланс, добавляя сумму
    }

    // Метод для уменьшения баланса на определенную сумму
    public void deductFromBalance(float amount) {
        float currentBalance = getUserBalance();
        saveUserBalance(currentBalance - amount); // Обновляем баланс, вычитая сумму
    }
}
