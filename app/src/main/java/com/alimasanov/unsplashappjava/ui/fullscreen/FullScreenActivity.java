package com.alimasanov.unsplashappjava.ui.fullscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.alimasanov.unsplashappjava.R;
import com.squareup.picasso.Picasso;

public class FullScreenActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    private FullScreenViewModel fullScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        imageView = findViewById(R.id.full_image);
        button = findViewById(R.id.full_image_but);
        fullScreenViewModel = ViewModelProviders.of(this).get(FullScreenViewModel.class);

        //проверка на наличие фото в бд
        fullScreenViewModel.getContainsPhotoMut().observe(this, contains -> {
            if (contains){
                button.setBackgroundColor(Color.GREEN);
                button.setText(R.string.photo_contains);
            } else {
                button.setBackgroundColor(Color.GRAY);
                button.setText(R.string.photo_add_to_fav);
            }
        });

        //отправить id фото во viewmodel
        fullScreenViewModel.setPhotoID(getIntent().getStringExtra("photoID"));

        //получение фото
        fullScreenViewModel.getPhoto().observe(this, photo -> {
            Picasso.get()
                    .load(photo.getUrls().getRegular())
                    .error(R.drawable.ic_file_download_black_24dp)
                    .into(imageView);
        });
    }
}