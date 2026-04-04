package com.example.sehirplakaeslesme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Geliştiren: Mustafa Özbal
 * Marmara Üniversitesi - Bilgisayar Programcılığı
 * Proje: Hafta 2 - Plaka ve Şehir Eşleştirme Oyunu
 */
public class MainActivity extends AppCompatActivity {

    // Kullanıcının yaptığı seçimleri hafızada tutacağımız değişkenler
    private int kullaniciSecilenPlaka = -1;
    private int kullaniciSecilenSehirIndeksi = -1;
    private String kullaniciSecilenSehirAdi = "";

    // Türkiye Şehirleri Dizisi
    private String[] turkiyeSehirleri = {
            "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir",
            "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
            "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari",
            "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
            "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir",
            "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat",
            "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman",
            "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye",
            "Düzce"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView solListeSayilar = findViewById(R.id.listViewSayilar);
        ListView sagListeSehirler = findViewById(R.id.listViewSehirler);
        Button btnKontrol = findViewById(R.id.btnKontrolEt);

        // 1-81 arası plakaları oluşturup karıştırıyoruz
        ArrayList<Integer> plakaSayilari = new ArrayList<>();
        for (int i = 1; i <= 81; i++) {
            plakaSayilari.add(i);
        }
        Collections.shuffle(plakaSayilari); // Plakaları rastgele karıştır

        // Listeleri Adaptör ile ekrana bağlıyoruz
        ArrayAdapter<Integer> sayiAdaptoru = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, plakaSayilari);
        solListeSayilar.setAdapter(sayiAdaptoru);

        ArrayAdapter<String> sehirAdaptoru = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, turkiyeSehirleri);
        sagListeSehirler.setAdapter(sehirAdaptoru);

        // SOL LİSTE KONTROLÜ - Kullanıcının soldan rastgele seçim yapmasını engelliyoruz
        solListeSayilar.setOnItemClickListener((parent, view, position, id) -> {
            if (kullaniciSecilenSehirIndeksi != -1) {
                solListeSayilar.setItemChecked(kullaniciSecilenSehirIndeksi, true);
            } else {
                solListeSayilar.setItemChecked(position, false);
            }
        });

        // SAĞ LİSTE KONTROLÜ - Asıl seçim buradan yapılacak
        sagListeSehirler.setOnItemClickListener((parent, view, position, id) -> {
            // Seçilen verileri değişkenlere kaydediyoruz
            kullaniciSecilenSehirIndeksi = position;
            kullaniciSecilenPlaka = plakaSayilari.get(position);
            kullaniciSecilenSehirAdi = turkiyeSehirleri[position];

            // Sol listede karşılık gelen satırı otomatik seçiyoruz
            solListeSayilar.setItemChecked(position, true);
            solListeSayilar.smoothScrollToPosition(position);
        });

        // KONTROL ET BUTONU
        btnKontrol.setOnClickListener(v -> {
            if (kullaniciSecilenPlaka == -1 || kullaniciSecilenSehirIndeksi == -1) {
                Toast.makeText(MainActivity.this, "Lütfen sağdaki listeden bir şehir seçin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Sonuçları diğer sayfaya gönderiyoruz
            Intent sayfaGecisi = new Intent(MainActivity.this, SonucActivity.class);
            sayfaGecisi.putExtra("gelenPlaka", kullaniciSecilenPlaka);
            sayfaGecisi.putExtra("gelenSehir", kullaniciSecilenSehirAdi);
            sayfaGecisi.putExtra("dogruPlaka", kullaniciSecilenSehirIndeksi + 1); // İndeks 0'dan başlar, plaka 1'den
            startActivity(sayfaGecisi);
        });
    }
}