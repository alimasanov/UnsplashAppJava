package com.alimasanov.unsplashappjava.ui.fullscreen;

import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alimasanov.unsplashappjava.App;
import com.alimasanov.unsplashappjava.R;
import com.alimasanov.unsplashappjava.database.UnsplashDatabase;
import com.alimasanov.unsplashappjava.model.pojo.Photo;
import com.alimasanov.unsplashappjava.model.pojo.dbEntity.PhotoRoom;
import com.alimasanov.unsplashappjava.server.NetworkEndpoints;
import com.alimasanov.unsplashappjava.server.UnsplashClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullScreenViewModel extends ViewModel {

    private Photo photo;
    private String photoID;
    private NetworkEndpoints networkEndpoints = UnsplashClient
            .getUnsplashClient()
            .create(NetworkEndpoints.class);
    private MutableLiveData<Photo> photoMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> containsPhotoMut = new MutableLiveData<>();

    public MutableLiveData<Boolean> getContainsPhotoMut() {
        containsPhotoMut.setValue(true);
        containsPhoto();
        return containsPhotoMut;
    }

    public MutableLiveData<Photo> getPhoto() {
        loadPhoto();
        return photoMutableLiveData;
    }

    private void loadPhoto() {
        networkEndpoints.getPhotoById(photoID).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                photo = response.body();
                photoMutableLiveData.setValue(photo);
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });
    }

    private void containsPhoto() {
        UnsplashDatabase db = App.getInstance().getDb();

        db.getUnsplashDAO().getPhotoById(photoID)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<PhotoRoom>() {
                    @Override
                    public void onSuccess(PhotoRoom photoRoom) {
                        containsPhotoMut.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        containsPhotoMut.setValue(false);
                    }
                });
    }

    public void setPhotoID(String photoID){
        this.photoID = photoID;
    }
}
