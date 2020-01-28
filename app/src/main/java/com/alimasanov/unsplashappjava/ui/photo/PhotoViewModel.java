package com.alimasanov.unsplashappjava.ui.photo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alimasanov.unsplashappjava.model.pojo.Photo;
import com.alimasanov.unsplashappjava.server.NetworkEndpoints;
import com.alimasanov.unsplashappjava.server.UnsplashClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoViewModel extends ViewModel {

    private NetworkEndpoints networkEndpoints = UnsplashClient
            .getUnsplashClient()
            .create(NetworkEndpoints.class);

    private MutableLiveData<List<Photo>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> err = new MutableLiveData<>();
    private Boolean b;
    List<Photo> list = new ArrayList<>();

    public MutableLiveData<Boolean> getErr() {
        err.setValue(b);
        return err;
    }

    public MutableLiveData<List<Photo>> getMutableLiveData() {
        for (int i = 0; i < 1; i++) {
            initData();
        }
        return mutableLiveData;
    }

    private void initData() {
        networkEndpoints.getRandomPhotos(10).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.body() != null) {
                    list.addAll(response.body());
                    mutableLiveData.setValue(list);
                } else {
                    b = false;
                }

            }
            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
//        return mutableLiveData;
//        return list;
    }
}