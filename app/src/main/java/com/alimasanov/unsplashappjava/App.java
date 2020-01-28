package com.alimasanov.unsplashappjava;

import android.app.Application;

import androidx.room.Room;

import com.alimasanov.unsplashappjava.database.UnsplashDatabase;

public class App extends Application {
    private UnsplashDatabase db;
    private App instance;

    public App getInstance() {
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
