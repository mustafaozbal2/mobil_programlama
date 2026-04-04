package com.example.zamanlayiciuygulamasi;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;

/**
 * Geliştiren: Mustafa Özbal
 * Proje: Hafta 3 - Zamanlayıcı ve Dinamik Arka Plan
 */
public class SecondActivity extends AppCompatActivity {

    private TextView ekranSayaci;
    private ConstraintLayout arkaplanTasarimi;
    private int sayacSuresi;

    // Geri sayım motoru için gerekenler
    private Handler zamanlayici;
    private Runnable tekrarlayanGorev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ekranSayaci = findViewById(R.id.sayacTextView);
        arkaplanTasarimi = findViewById(R.id.ikinciEkranArkaplan);

        // MainActivity'den gelen sayıyı karşılıyoruz
        sayacSuresi = getIntent().getIntExtra("RASTGELE_SAYI", 10);
        ekranSayaci.setText(String.valueOf(sayacSuresi));

        zamanlayici = new Handler();

        // Her 1 saniyede bir çalışacak kod bloğu
        tekrarlayanGorev = new Runnable() {
            @Override
            public void run() {
                if (sayacSuresi > 0) {
                    sayacSuresi--; // Süreyi azalt
                    ekranSayaci.setText(String.valueOf(sayacSuresi));

                    // Arka planı rastgele renklendir
                    Random renkUretici = new Random();
                    int yeniRenk = Color.rgb(renkUretici.nextInt(256), renkUretici.nextInt(256), renkUretici.nextInt(256));
                    arkaplanTasarimi.setBackgroundColor(yeniRenk);

                    // Bu bloğu 1 saniye (1000ms) sonra tekrar tetikle
                    zamanlayici.postDelayed(this, 1000);
                } else {
                    // Süre bittiğinde döngüyü kır
                    Toast.makeText(SecondActivity.this, "Süre Doldu! Uygulama bitmiştir.", Toast.LENGTH_LONG).show();
                    zamanlayici.removeCallbacks(tekrarlayanGorev);
                }
            }
        };

        // Geri sayımı ilk kez tetikliyoruz
        zamanlayici.post(tekrarlayanGorev);
    }
}