package com.alimasanov.unsplashappjava.ui.fullscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ImageView;

import com.alimasanov.unsplashappjava.R;
import com.squareup.picasso.Picasso;

public class FullScreenActivity extends AppCompatActivity {

    private ImageView imageView;
    private FullScreenViewModel fullScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        imageView = findViewById(R.id.full_image);
        fullScreenViewModel = ViewModelProviders.of(this).get(FullScreenViewModel.class);

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