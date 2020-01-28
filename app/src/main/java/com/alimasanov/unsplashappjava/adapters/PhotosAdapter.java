package com.alimasanov.unsplashappjava.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alimasanov.unsplashappjava.App;
import com.alimasanov.unsplashappjava.FullScreenActivity;
import com.alimasanov.unsplashappjava.R;
import com.alimasanov.unsplashappjava.database.UnsplashDAO;
import com.alimasanov.unsplashappjava.database.UnsplashDatabase;
import com.alimasanov.unsplashappjava.model.pojo.Photo;
import com.alimasanov.unsplashappjava.model.pojo.dbEntity.PhotoRoom;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private final List<Photo> photoList;

    public PhotosAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.ViewHolder holder, int position) {
        final Photo photo = photoList.get(position);
        final App app = new App();
        Picasso.get()
                .load(photo.getUrls().getThumb())
                .into(holder.imageView);

        holder.cardView.setOnLongClickListener(v -> {
            UnsplashDatabase db = app.getDb();

            PhotoRoom room = new PhotoRoom();
            room.setPhotoID(photo.getId());
            room.setRegularURL(photo.getUrls().getRegular());
            room.setSmallURL(photo.getUrls().getSmall());

            UnsplashDAO dao = db.unsplashDAO();
            dao.insertAll(room);
            Toast.makeText(app.getInstance(), "Фото добавлено в избранное", Toast.LENGTH_SHORT).show();
        return true;
        });

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(app.getInstance(), FullScreenActivity.class);
            intent.putExtra("photo", photo);
            app.getInstance().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        CardView cardView;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.card_image);
            cardView = view.findViewById(R.id.card_item);
        }
    }
}
