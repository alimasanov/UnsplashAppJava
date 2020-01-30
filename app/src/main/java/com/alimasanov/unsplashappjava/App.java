package com.alimasanov.unsplashappjava;

import android.app.Application;

import androidx.room.Room;

import com.alimasanov.unsplashappjava.database.UnsplashDatabase;

public class App extends Application {
    private static App instance;
    private UnsplashDatabase db;


    public static App getInstance() {
        return instance;
    }

    public UnsplashDatabase getDb() {
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(
                instance,
                UnsplashDatabase.class,
                "db").build();
    }
}
