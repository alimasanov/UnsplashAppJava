package com.alimasanov.unsplashappjava.server;

import com.alimasanov.unsplashappjava.model.pojo.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkEndpoints {

    @GET("photos/random")
    Call<List<Photo>> getRandomPhotos(@Query("count") Integer count);

    @GET("photos/{id}")
    Call<Photo> getPhotoById(@Path("id") String id);
}
