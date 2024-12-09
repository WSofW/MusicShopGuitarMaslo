package com.example.musicshopguitar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musicshopguitar.adapters.CategoryAdapter;
import com.example.musicshopguitar.models.Category;
import com.example.musicshopguitar.models.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private TextView balanceTextView;
    private List<Category> categories;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов UI
        balanceTextView = findViewById(R.id.balanceTextView);
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);

        // Инициализация UserPreferences
        userPreferences = new UserPreferences(this);

        // Обновление баланса
        updateBalanceDisplay();

        // Инициализация списка категорий
        categories = generateCategories();
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Адаптер для категорий
        CategoryAdapter adapter = new CategoryAdapter(this, categories, category -> {
            // Обработчик клика на категорию
            Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
            intent.putExtra("category", (Serializable) category); // Передача объекта категории
            startActivity(intent);
        });

        categoryRecyclerView.setAdapter(adapter);
    }

    // Обновление отображения баланса
    private void updateBalanceDisplay() {
        // Получаем текущий баланс из SharedPreferences
        float balance = userPreferences.getUserBalance();
        balanceTextView.setText(String.format("Ваш баланс составляет: %.2f₽", balance));
    }

    // Генерация списка категорий и продуктов
    private List<Category> generateCategories() {
        List<Category> categories = new ArrayList<>();

        // Список продуктов для каждой категории
        List<Product> guitars = new ArrayList<>();
        guitars.add(new Product("Acoustic Guitar", "Yamaha", 12500.0, 10, R.drawable.yamaha_guitar_image));
        guitars.add(new Product("Electric Guitar", "Fender", 50200.0, 5, R.drawable.fender_guitar_image));
        guitars.add(new Product("Bass Guitar", "Ibanez", 33000.0, 7, R.drawable.ibanez_bass_image));
        guitars.add(new Product("Classical Guitar", "Cordoba", 18000.0, 15, R.drawable.cordoba_classical_image));
        guitars.add(new Product("Electric Guitar", "Gibson Les Paul", 85000.0, 3, R.drawable.gibson_les_paul_image));
        guitars.add(new Product("Acoustic Guitar", "Taylor", 45000.0, 8, R.drawable.taylor_acoustic_image));
        guitars.add(new Product("Electric Guitar", "PRS SE", 60000.0, 4, R.drawable.prs_se_image));
        guitars.add(new Product("Bass Guitar", "Fender Jazz Bass", 78000.0, 6, R.drawable.fender_jazz_bass_image));
        guitars.add(new Product("Electric Guitar", "ESP LTD", 55000.0, 5, R.drawable.esp_ltd_image));
        guitars.add(new Product("Acoustic Guitar", "Martin", 95000.0, 2, R.drawable.martin_acoustic_image));
        guitars.add(new Product("Electric Guitar", "Schecter", 47000.0, 6, R.drawable.schecter_image));
        guitars.add(new Product("Classical Guitar", "Yamaha C40", 12000.0, 20, R.drawable.yamaha_c40_image));
        guitars.add(new Product("Electric Guitar", "Jackson", 40000.0, 5, R.drawable.jackson_image));
        guitars.add(new Product("Acoustic Guitar", "Seagull", 38000.0, 7, R.drawable.seagull_image));
        guitars.add(new Product("Electric Guitar", "Charvel", 53000.0, 4, R.drawable.charvel_image));
        guitars.add(new Product("Bass Guitar", "Music Man", 89000.0, 3, R.drawable.music_man_bass_image));
        guitars.add(new Product("Electric Guitar", "Dean", 36000.0, 6, R.drawable.dean_image));
        guitars.add(new Product("Acoustic Guitar", "Epiphone", 22000.0, 10, R.drawable.epiphone_image));
        guitars.add(new Product("Electric Guitar", "Kramer", 44000.0, 4, R.drawable.kramer_image));
        guitars.add(new Product("Acoustic Guitar", "Takamine", 41000.0, 5, R.drawable.takamine_image));
        guitars.add(new Product("Electric Guitar", "Ibanez RG", 63000.0, 3, R.drawable.ibanez_rg_image));

        List<Product> accessories = new ArrayList<>();
        accessories.add(new Product("Гитарный тюнер", "Korg", 1500.0, 50, R.drawable.guitar_tuner_image));
        accessories.add(new Product("Медиатор", "Dunlop", 150.0, 200, R.drawable.guitar_pick_image));
        accessories.add(new Product("Каподастр", "Fender", 2500.0, 30, R.drawable.guitar_capo_image));
        accessories.add(new Product("Гитарный ремень", "Levy's", 1200.0, 40, R.drawable.guitar_strap_image));
        accessories.add(new Product("Струны для акустической гитары", "Elixir", 2000.0, 100, R.drawable.guitar_strings_image));
        accessories.add(new Product("Подставка для гитары", "Hercules", 3000.0, 20, R.drawable.guitar_stand_image));
        accessories.add(new Product("Педаль эффектов", "Boss", 9500.0, 15, R.drawable.guitar_pedal_image));
        accessories.add(new Product("Гитарный чехол", "Gator", 4500.0, 25, R.drawable.guitar_case_image));
        accessories.add(new Product("Гитарный усилитель", "Marshall", 25000.0, 10, R.drawable.guitar_amp_image));
        accessories.add(new Product("Кабель для гитары", "Planet Waves", 1200.0, 70, R.drawable.guitar_cable_image));

        categories.add(new Category("Гитары", guitars, R.drawable.sample_category_image));
        categories.add(new Category("Аксессуары к гитаре", accessories, R.drawable.guitar_accessories_image));

        return categories;
    }
}