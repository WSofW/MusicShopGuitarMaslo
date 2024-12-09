package com.example.musicshopguitar;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help); // Убедитесь, что это правильный layout

        // Инициализация WebView
        WebView webView = findViewById(R.id.webViewHelp); // Убедитесь, что ID правильный
        webView.setWebViewClient(new WebViewClient()); // Чтобы ссылки открывались внутри WebView
        webView.loadUrl("file:///android_asset/help_login.html"); // Путь к файлу в папке assets
    }
}
