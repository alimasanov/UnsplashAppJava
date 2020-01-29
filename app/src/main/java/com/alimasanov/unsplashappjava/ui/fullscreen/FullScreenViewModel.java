package com.alimasanov.unsplashappjava.ui.fullscreen;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alimasanov.unsplashappjava.model.pojo.Photo;
import com.alimasanov.unsplashappjava.server.NetworkEndpoints;
import com.alimasanov.unsplashappjava.server.UnsplashClient;

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

    public void setPhotoID(String photoID){
        this.photoID = photoID;
    }
}
