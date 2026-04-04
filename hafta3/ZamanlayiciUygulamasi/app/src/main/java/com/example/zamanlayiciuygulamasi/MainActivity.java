package com.example.zamanlayiciuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

/**
 * Geliştiren: Mustafa Özbal
 * Marmara Üniversitesi - Bilgisayar Programcılığı
 * Proje: Hafta 3 - SeekBar ve Rastgele Sayı Üretimi
 */
public class MainActivity extends AppCompatActivity {

    private SeekBar kaydiriciBar1, kaydiriciBar2;
    private TextView txtDeger1, txtDeger2;
    private Button btnBasla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Arayüz elemanlarını bağlama
        kaydiriciBar1 = findViewById(R.id.seekBar1);
        kaydiriciBar2 = findViewById(R.id.seekBar2);
        txtDeger1 = findViewById(R.id.textViewDeger1);
        txtDeger2 = findViewById(R.id.textViewDeger2);
        btnBasla = findViewById(R.id.button);

        kaydiriciBar1.setMax(100);
        kaydiriciBar2.setMax(100);

        // 1. SeekBar Dinleyicisi
        kaydiriciBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtDeger1.setText("Seçilen: " + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 2. SeekBar Dinleyicisi
        kaydiriciBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtDeger2.setText("Seçilen: " + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Butona Tıklanma Olayı
        btnBasla.setOnClickListener(v -> {
            int secim1 = kaydiriciBar1.getProgress();
            int secim2 = kaydiriciBar2.getProgress();

            int altSinir = Math.min(secim1, secim2);
            int ustSinir = Math.max(secim1, secim2);

            int uretilenRastgeleSayi;
            if (altSinir == ustSinir) {
                uretilenRastgeleSayi = altSinir;
            } else {
                Random rastgeleMotoru = new Random();
                uretilenRastgeleSayi = rastgeleMotoru.nextInt((ustSinir - altSinir) + 1) + altSinir;
            }

            // Diğer sayfaya geçiş ve veriyi gönderme
            Intent sayfaGecisi = new Intent(MainActivity.this, SecondActivity.class);
            sayfaGecisi.putExtra("RASTGELE_SAYI", uretilenRastgeleSayi);
            startActivity(sayfaGecisi);
        });
    }
}