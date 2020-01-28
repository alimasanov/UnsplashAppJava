package com.alimasanov.unsplashappjava.server;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashClient {
    private static Retrofit retrofit = null;
    public static Retrofit getUnsplashClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HeaderInterceptor(Config.ACCESS_KEY)).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
