package com.alimasanov.unsplashappjava.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alimasanov.unsplashappjava.App;
import com.alimasanov.unsplashappjava.ui.fullscreen.FullScreenActivity;
import com.alimasanov.unsplashappjava.R;
import com.alimasanov.unsplashappjava.database.UnsplashDatabase;
import com.alimasanov.unsplashappjava.model.pojo.Photo;
import com.alimasanov.unsplashappjava.model.pojo.dbEntity.PhotoRoom;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private List<Photo> photoList;

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

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.ViewHolder holder, int position) {
        final Photo photo = photoList.get(position);
        Picasso.get()
                .load(photo.getUrls().getThumb())
                .into(holder.imageView);

        //Добавление фото в избранное
        //при наличии фото в базе выполнится метод onSuccess
        //иначе выполнится метод onFailure
        holder.cardView.setOnLongClickListener(v -> {
            UnsplashDatabase db = App.getInstance().getDb();
            PhotoRoom room = new PhotoRoom(photo);
            db.getUnsplashDAO().getPhotoById(room.getPhotoID())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<PhotoRoom>() {
                        @Override
                        public void onSuccess(PhotoRoom photoRoom) {
                            Toast.makeText(App.getInstance(), R.string.photo_contains, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Runnable run = () -> db.getUnsplashDAO().insertAll(room);

                            Thread t = new Thread(run);
                            t.start();
                            Toast.makeText(App.getInstance(), R.string.photo_added, Toast.LENGTH_SHORT).show();
                        }
                    });
            return true;
        });

        //Открытие фото в отдельнм окне
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), FullScreenActivity.class);
            intent.putExtra("photoID", photo.getId());
            v.getContext().startActivity(intent);
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

    public void clear() {
        photoList.clear();
    }

    public void update(List<Photo> photoList){
        this.photoList = photoList;
    }
}
