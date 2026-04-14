package com.example.vize_sonrasi;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WifiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        Button btnWifiToggle = findViewById(R.id.btnWifiToggle);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        btnWifiToggle.setText(wifiManager.isWifiEnabled() ? "AÇIK" : "KAPALI");

        btnWifiToggle.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                startActivity(new Intent(Settings.Panel.ACTION_WIFI));
            } else {
                boolean isEnabled = wifiManager.isWifiEnabled();
                wifiManager.setWifiEnabled(!isEnabled);
                btnWifiToggle.setText(!isEnabled ? "AÇIK" : "KAPALI");
                Toast.makeText(WifiActivity.this, !isEnabled ? "WiFi Açıldı" : "WiFi Kapatıldı", Toast.LENGTH_SHORT).show();
            }
        });
    }
}