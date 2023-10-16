package com.example.imagegallery;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private ImageView[] imgViews = new ImageView[9];
    private static final int EDIT_REQUEST_CODE = 1;
    Uri photoUri;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgViews[0] = findViewById(R.id.imageview1);
        imgViews[1] = findViewById(R.id.imageview2);
        imgViews[2] = findViewById(R.id.imageview3);
        imgViews[3] = findViewById(R.id.imageview4);
        imgViews[4] = findViewById(R.id.imageview5);
        imgViews[5] = findViewById(R.id.imageview6);
        imgViews[6] = findViewById(R.id.imageview7);
        imgViews[7] = findViewById(R.id.imageview8);
        imgViews[8] = findViewById(R.id.imageview9);
        for (int i = 0; i < imgViews.length; i++) {
            int finalId = i;
            imgViews[i].setOnClickListener(view -> {
                id = finalId;
                imageChooser();
            });
        }
    }
    private void imageChooser() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        launcher.launch(i);
    }
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK
                        && result.getData() != null) {
//                    Toast.makeText(getApplicationContext(), result.getData().toString(), Toast.LENGTH_SHORT).show();
                    photoUri = result.getData().getData();
                    imgViews[id].setImageURI(photoUri);
                }
            });
}
