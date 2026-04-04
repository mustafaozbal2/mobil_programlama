package com.example.hesapmakinesi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Geliştiren: Mustafa Özbal
 * Marmara Üniversitesi - Bilgisayar Programcılığı
 * Proje: Hafta 1 - Gelişmiş Hesap Makinesi Uygulaması
 */
public class MainActivity extends AppCompatActivity {

    private TextView ekranGostergesi;
    private double birinciSayi = 0;
    private String secilenIslem = "";
    private boolean yeniGirisMi = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ekran bileşenini XML'den Java'ya bağlama işlemi
        ekranGostergesi = findViewById(R.id.textViewSonuc);

        // Rakam Butonları için Ortak Tıklama Dinleyicisi
        View.OnClickListener rakamDinleyicisi = view -> {
            if (yeniGirisMi) {
                ekranGostergesi.setText("");
                yeniGirisMi = false;
            }
            Button tiklananButon = (Button) view;
            ekranGostergesi.append(tiklananButon.getText().toString());
        };

        // Sayı butonlarının ID'leri (Döngü ile atama yapılıyor)
        int[] rakamButonlariId = {
                R.id.button1,  // 0
                R.id.button6, R.id.button7, R.id.button8,    // 1, 2, 3
                R.id.button11, R.id.button12, R.id.button13, // 4, 5, 6
                R.id.button16, R.id.button17, R.id.button18  // 7, 8, 9
        };

        for (int id : rakamButonlariId) {
            findViewById(id).setOnClickListener(rakamDinleyicisi);
        }

        // Temel İşlem Butonları (+, -, *, /, x^y) için Ortak Dinleyici
        View.OnClickListener islemDinleyicisi = view -> {
            if (ekranGostergesi.getText().toString().isEmpty()) return;
            birinciSayi = Double.parseDouble(ekranGostergesi.getText().toString());
            yeniGirisMi = true;

            int id = view.getId();
            if (id == R.id.button19) secilenIslem = "+";
            else if (id == R.id.button14) secilenIslem = "-";
            else if (id == R.id.button9) secilenIslem = "*";
            else if (id == R.id.button4) secilenIslem = "/";
            else if (id == R.id.button10) secilenIslem = "^"; // Üslü sayı işlemi
        };

        findViewById(R.id.button19).setOnClickListener(islemDinleyicisi);
        findViewById(R.id.button14).setOnClickListener(islemDinleyicisi);
        findViewById(R.id.button9).setOnClickListener(islemDinleyicisi);
        findViewById(R.id.button4).setOnClickListener(islemDinleyicisi);
        findViewById(R.id.button10).setOnClickListener(islemDinleyicisi);

        // Eşittir (=) Butonu Hesaplama Algoritması
        findViewById(R.id.button3).setOnClickListener(v -> {
            if (secilenIslem.isEmpty() || ekranGostergesi.getText().toString().isEmpty()) return;
            double ikinciSayi = Double.parseDouble(ekranGostergesi.getText().toString());
            double hesapSonucu = 0;

            switch (secilenIslem) {
                case "+": hesapSonucu = birinciSayi + ikinciSayi; break;
                case "-": hesapSonucu = birinciSayi - ikinciSayi; break;
                case "*": hesapSonucu = birinciSayi * ikinciSayi; break;
                case "/":
                    if (ikinciSayi == 0) {
                        ekranGostergesi.setText("Hata: 0'a bölünemez");
                        yeniGirisMi = true;
                        return; // Hata durumunda işlemi kes
                    }
                    hesapSonucu = birinciSayi / ikinciSayi;
                    break;
                case "^": hesapSonucu = Math.pow(birinciSayi, ikinciSayi); break;
            }

            ekranGostergesi.setText(String.valueOf(hesapSonucu));
            secilenIslem = "";
            yeniGirisMi = true;
        });

        // Temizle (C) Butonu İşlevi
        findViewById(R.id.button2).setOnClickListener(v -> {
            ekranGostergesi.setText("");
            birinciSayi = 0;
            secilenIslem = "";
            yeniGirisMi = true;
        });

        // Karekök (√x) İşlevi
        findViewById(R.id.button5).setOnClickListener(v -> {
            if (ekranGostergesi.getText().toString().isEmpty()) return;
            double girilenSayi = Double.parseDouble(ekranGostergesi.getText().toString());
            ekranGostergesi.setText(String.valueOf(Math.sqrt(girilenSayi)));
            yeniGirisMi = true;
        });

        // 1/x İşlevi
        findViewById(R.id.button15).setOnClickListener(v -> {
            if (ekranGostergesi.getText().toString().isEmpty()) return;
            double girilenSayi = Double.parseDouble(ekranGostergesi.getText().toString());
            if (girilenSayi == 0) { ekranGostergesi.setText("Tanımsız"); }
            else { ekranGostergesi.setText(String.valueOf(1 / girilenSayi)); }
            yeniGirisMi = true;
        });

        // Yüzde (%) İşlevi
        findViewById(R.id.button20).setOnClickListener(v -> {
            if (ekranGostergesi.getText().toString().isEmpty()) return;
            double girilenSayi = Double.parseDouble(ekranGostergesi.getText().toString());
            ekranGostergesi.setText(String.valueOf(girilenSayi / 100));
            yeniGirisMi = true;
        });
    }
}