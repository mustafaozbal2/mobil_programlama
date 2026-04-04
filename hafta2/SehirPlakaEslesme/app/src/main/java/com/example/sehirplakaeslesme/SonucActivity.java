package com.example.sehirplakaeslesme;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Geliştiren: Mustafa Özbal
 */
public class SonucActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        TextView sonucEkrani = findViewById(R.id.tvSonuc);
        Button btnBastanBasla = findViewById(R.id.btnYenidenDagit);

        // MainActivity'den gönderilen verileri karşılıyoruz
        int alinanPlaka = getIntent().getIntExtra("gelenPlaka", 0);
        String alinanSehir = getIntent().getStringExtra("gelenSehir");
        int gercekPlaka = getIntent().getIntExtra("dogruPlaka", 0);

        // Doğruluk Kontrolü
        if (alinanPlaka == gercekPlaka) {
            sonucEkrani.setText(alinanSehir + " (" + alinanPlaka + ") \n\nTebrikler, Doğru Eşleşme!");
            sonucEkrani.setTextColor(Color.parseColor("#4CAF50")); // Yeşil renk
        } else {
            sonucEkrani.setText(alinanSehir + " için seçtiğin plaka: " + alinanPlaka + "\nYanlış! Doğrusu: " + gercekPlaka);
            sonucEkrani.setTextColor(Color.parseColor("#F44336")); // Kırmızı renk
        }

        // Oyunu Sıfırla ve Yeniden Başlat
        btnBastanBasla.setOnClickListener(v -> {
            Intent basaDon = new Intent(SonucActivity.this, MainActivity.class);
            basaDon.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(basaDon);
            finish();
        });
    }
}