package com.example.a3032026hesapmakinas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText inputSayi1, inputSayi2;
    TextView textSonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputSayi1 = findViewById(R.id.editTextSayi1);
        inputSayi2 = findViewById(R.id.editTextSayi2);
        textSonuc = findViewById(R.id.textViewSonuc);

        Button btnTopla = findViewById(R.id.btnTopla);
        Button btnCikar = findViewById(R.id.btnCikar);
        Button btnCarp = findViewById(R.id.btnCarp);
        Button btnBol = findViewById(R.id.btnBol);
        Button btnUs = findViewById(R.id.btnUs);
        Button btnKarekok = findViewById(R.id.btnKarekok);
        Button btnBirBoluX = findViewById(R.id.btnBirBoluX);

        btnTopla.setOnClickListener(v -> islemYap("+"));
        btnCikar.setOnClickListener(v -> islemYap("-"));
        btnCarp.setOnClickListener(v -> islemYap("*"));
        btnBol.setOnClickListener(v -> islemYap("/"));
        btnUs.setOnClickListener(v -> islemYap("^"));


        btnKarekok.setOnClickListener(v -> {
            try {
                double sayi1 = Double.parseDouble(inputSayi1.getText().toString());
                if (sayi1 < 0) {
                    textSonuc.setText("Negatif sayının karekökü olmaz!");
                } else {
                    double sonuc = Math.sqrt(sayi1);
                    textSonuc.setText("Sonuç: " + sonuc);
                }
            } catch (Exception e) {
                textSonuc.setText("Lütfen 1. sayı kutusuna geçerli bir sayı girin!");
            }
        });

        btnBirBoluX.setOnClickListener(v -> {
            try {
                double sayi1 = Double.parseDouble(inputSayi1.getText().toString());
                if (sayi1 == 0) {
                    textSonuc.setText("Sıfıra bölme hatası!");
                } else {
                    double sonuc = 1 / sayi1;
                    textSonuc.setText("Sonuç: " + sonuc);
                }
            } catch (Exception e) {
                textSonuc.setText("Lütfen 1. sayı kutusuna geçerli bir sayı girin!");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void islemYap(String operator) {
        String s1 = inputSayi1.getText().toString();
        String s2 = inputSayi2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            textSonuc.setText("Lütfen iki sayıyı da girin!");
            return;
        }

        try {
            double sayi1 = Double.parseDouble(s1);
            double sayi2 = Double.parseDouble(s2);
            double sonuc = 0;

            switch (operator) {
                case "+": sonuc = sayi1 + sayi2; break;
                case "-": sonuc = sayi1 - sayi2; break;
                case "*": sonuc = sayi1 * sayi2; break;
                case "/":
                    if (sayi2 == 0) {
                        textSonuc.setText("Bir sayı 0'a bölünemez!");
                        return;
                    }
                    sonuc = sayi1 / sayi2;
                    break;
                case "^": sonuc = Math.pow(sayi1, sayi2); break;
            }
            textSonuc.setText("Sonuç: " + sonuc);

        } catch (Exception e) {
            textSonuc.setText("Hatalı giriş yaptınız!");
        }
    }
}