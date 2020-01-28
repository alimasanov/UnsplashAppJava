package com.alimasanov.unsplashappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.alimasanov.unsplashappjava.model.pojo.Photo;
import com.squareup.picasso.Picasso;

public class FullScreenActivity extends AppCompatActivity {

    private Photo photo = new Photo();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        imageView = findViewById(R.id.full_image);
        photo = (Photo) getIntent().getSerializableExtra("photo");

        Picasso.get()
                .load(photo.getUrls().getRegular())
                .into(imageView);
    }
}
