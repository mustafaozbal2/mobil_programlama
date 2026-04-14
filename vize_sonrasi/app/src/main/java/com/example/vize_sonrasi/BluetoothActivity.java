package com.example.vize_sonrasi;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> cihazListesi;

    private final ActivityResultLauncher<Intent> btAcLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Toast.makeText(this, "Bluetooth Açıldı", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        ListView listViewCihazlar = findViewById(R.id.listViewCihazlar);
        cihazListesi = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cihazListesi);
        listViewCihazlar.setAdapter(adapter);

        Button btnBtAc = findViewById(R.id.btnBtAc);
        Button btnBtKapat = findViewById(R.id.btnBtKapat);
        Button btnGorunurOl = findViewById(R.id.btnGorunurOl);
        Button btnListele = findViewById(R.id.btnListele);

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Cihazda Bluetooth bulunmuyor!", Toast.LENGTH_LONG).show();
            return;
        }

        btnBtAc.setOnClickListener(v -> {
            if (!bluetoothAdapter.isEnabled()) {
                btAcLauncher.launch(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
            } else {
                Toast.makeText(this, "Bluetooth zaten açık!", Toast.LENGTH_SHORT).show();
            }
        });

        btnBtKapat.setOnClickListener(v -> {
            if (bluetoothAdapter.isEnabled()) {
                bluetoothAdapter.disable();
                Toast.makeText(this, "Bluetooth Kapatıldı", Toast.LENGTH_SHORT).show();
                cihazListesi.clear();
                adapter.notifyDataSetChanged();
            }
        });

        btnGorunurOl.setOnClickListener(v -> {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
            startActivity(discoverableIntent);
        });

        btnListele.setOnClickListener(v -> {
            if (bluetoothAdapter.isEnabled()) {
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                cihazListesi.clear();
                if (pairedDevices.size() > 0) {
                    for (BluetoothDevice device : pairedDevices) {
                        cihazListesi.add(device.getName() + "\n" + device.getAddress());
                    }
                } else {
                    cihazListesi.add("Eşleşmiş cihaz bulunamadı.");
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Cihazlar listelendi", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Önce Bluetooth'u açmalısın!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}