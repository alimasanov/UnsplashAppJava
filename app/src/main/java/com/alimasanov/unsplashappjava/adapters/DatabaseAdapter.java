package com.alimasanov.unsplashappjava.adapters;

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
import com.alimasanov.unsplashappjava.R;
import com.alimasanov.unsplashappjava.database.UnsplashDatabase;
import com.alimasanov.unsplashappjava.model.pojo.Photo;
import com.alimasanov.unsplashappjava.model.pojo.Urls;
import com.alimasanov.unsplashappjava.model.pojo.dbEntity.PhotoRoom;
import com.alimasanov.unsplashappjava.ui.fullscreen.FullScreenActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder> {

    private List<PhotoRoom> list;

    public DatabaseAdapter(List<PhotoRoom> list) {
        this.list = list;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoRoom photoRoom = list.get(position);
        Urls urls = new Urls(photoRoom);
        Photo photo = new Photo(photoRoom, urls);

        Picasso.get()
                .load(photoRoom.getSmallURL())
                .into(holder.imageView);

        //Открытие фото в отдельнм окне
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), FullScreenActivity.class);
            intent.putExtra("photoID", photoRoom.getPhotoID());
            v.getContext().startActivity(intent);
        });

        //Удаление фото из избранного
        holder.cardView.setOnLongClickListener(v -> {
            UnsplashDatabase db = App.getInstance().getDb();
            db.getUnsplashDAO().delete(photoRoom);
            Toast.makeText(App.getInstance(), R.string.photo_deleted, Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.card_image);
            cardView = view.findViewById(R.id.card_item);
        }
    }
}
