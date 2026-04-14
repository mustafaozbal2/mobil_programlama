package com.example.vize_sonrasi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {

    private ImageView imageView;
    private VideoView videoView;

    private final ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bitmap imageBitmap = (Bitmap) result.getData().getExtras().get("data");
                    imageView.setImageBitmap(imageBitmap);
                    imageView.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.GONE);
                }
            }
    );

    private final ActivityResultLauncher<Intent> takeVideoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri videoUri = result.getData().getData();
                    if (videoUri != null) {
                        videoView.setVideoURI(videoUri);
                        videoView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                        videoView.start();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button btnFotoCek = findViewById(R.id.btnFotoCek);
        Button btnVideoCek = findViewById(R.id.btnVideoCek);
        imageView = findViewById(R.id.imageView);
        videoView = findViewById(R.id.videoView);

        // Try-catch blokları eklendi. Artık çökmez, ekrana hata sebebini yazar!
        btnFotoCek.setOnClickListener(v -> {
            try {
                takePictureLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            } catch (Exception e) {
                Toast.makeText(CameraActivity.this, "HATA: Kamera uygulaması bulunamadı!", Toast.LENGTH_LONG).show();
            }
        });

        btnVideoCek.setOnClickListener(v -> {
            try {
                takeVideoLauncher.launch(new Intent(MediaStore.ACTION_VIDEO_CAPTURE));
            } catch (Exception e) {
                Toast.makeText(CameraActivity.this, "HATA: Video uygulaması bulunamadı!", Toast.LENGTH_LONG).show();
            }
        });
    }
}