package com.example.vize_sonrasi;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBluetooth = findViewById(R.id.btnBluetooth);
        Button btnWifi = findViewById(R.id.btnWifi);
        Button btnCamera = findViewById(R.id.btnCamera);

        btnBluetooth.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BluetoothActivity.class)));
        btnWifi.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WifiActivity.class)));
        btnCamera.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CameraActivity.class)));
    }
}