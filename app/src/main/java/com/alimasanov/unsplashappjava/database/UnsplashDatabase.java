package com.alimasanov.unsplashappjava.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alimasanov.unsplashappjava.model.pojo.dbEntity.PhotoRoom;

@Database(entities = {PhotoRoom.class}, version = 1, exportSchema = false)
public abstract class UnsplashDatabase extends RoomDatabase {
    public abstract UnsplashDAO getUnsplashDAO();
}
