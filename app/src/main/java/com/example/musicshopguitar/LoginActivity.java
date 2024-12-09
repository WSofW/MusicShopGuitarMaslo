package com.example.musicshopguitar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Убедитесь, что здесь правильный layout

        // Инициализация элементов UI
        editTextUsername = findViewById(R.id.editTextUsername); // Поле ввода имени пользователя
        Button buttonLogin = findViewById(R.id.buttonLogin); // Кнопка "Войти"
        Button buttonRegister = findViewById(R.id.buttonRegister); // Кнопка "Зарегистрироваться"
        Button buttonHelp = findViewById(R.id.buttonHelp); // Кнопка "Справка"

        // Инициализация UserPreferences
        userPreferences = new UserPreferences(this);

        // Обработчик кнопки для регистрации
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на экран регистрации
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        // Обработчик кнопки для входа
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                if (!username.isEmpty()) {
                    String storedUsername = userPreferences.getUserName();

                    if (storedUsername != null && storedUsername.equals(username)) {
                        // Пользователь найден, переходим на главную страницу
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Пользователь не найден. Пожалуйста, зарегистрируйтесь.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Введите имя пользователя", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Обработчик кнопки справки
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на экран справки
                Intent intent = new Intent(LoginActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }
}
