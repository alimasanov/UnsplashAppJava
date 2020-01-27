package com.alimasanov.unsplashappjava.server;

import com.alimasanov.unsplashappjava.model.pojo.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkEndpoints {
    @GET("photos/random")
    public Call<ArrayList<Photo>> getRandomPhotos(
            @Query("count") int count
    );
}
