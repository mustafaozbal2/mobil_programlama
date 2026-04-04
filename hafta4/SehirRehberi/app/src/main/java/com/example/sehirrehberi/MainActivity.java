package com.example.sehirrehberi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

/**
 * Geliştiren: Mustafa Özbal
 * Marmara Üniversitesi - Bilgisayar Programcılığı
 * Proje: Hafta 4 - AlertDialog ve Şehir Rehberi
 */
public class MainActivity extends AppCompatActivity {

    // 9 adet şehrin resim ID'lerini tutan dizimiz
    private int[] sehirResimleri = {
            R.drawable.ist_1, R.drawable.ist_2, R.drawable.ist_3,
            R.drawable.ank_1, R.drawable.ank_2, R.drawable.ank_3,
            R.drawable.izm_1, R.drawable.izm_2, R.drawable.izm_3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSehirSec = findViewById(R.id.btnSehirSec);

        btnSehirSec.setOnClickListener(v -> {
            // 1. Rastgele bir resim seçimi
            Random rastgeleMotoru = new Random();
            int rastgeleIndeks = rastgeleMotoru.nextInt(sehirResimleri.length);
            int secilenResimID = sehirResimleri[rastgeleIndeks];

            // 2. AlertDialog (Açılır Pencere) Oluşturma
            AlertDialog.Builder acilirPencere = new AlertDialog.Builder(MainActivity.this);
            acilirPencere.setTitle("Gideceğiniz Şehri Seçin");
            acilirPencere.setIcon(secilenResimID);

            // 3. Seçenekleri Hazırlama
            String[] sehirSecenekleri = {"İstanbul", "Ankara", "İzmir"};

            // 4. Pencereye seçenekleri ekleme ve tıklama dinleyicisi
            acilirPencere.setItems(sehirSecenekleri, (dialog, secilenSira) -> {
                // secilenSira 0=İstanbul, 1=Ankara, 2=İzmir
                if (secilenSira == 0) {
                    Intent istanbulaGecis = new Intent(MainActivity.this, IstanbulActivity.class);
                    startActivity(istanbulaGecis);
                } else if (secilenSira == 1) {
                    Intent ankarayaGecis = new Intent(MainActivity.this, AnkaraActivity.class);
                    startActivity(ankarayaGecis);
                } else if (secilenSira == 2) {
                    Intent izmireGecis = new Intent(MainActivity.this, IzmirActivity.class);
                    startActivity(izmireGecis);
                }
            });

            // Pencereyi ekranda göster
            acilirPencere.show();
        });
    }
}