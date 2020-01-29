package com.alimasanov.unsplashappjava.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alimasanov.unsplashappjava.model.pojo.Photo;
import com.alimasanov.unsplashappjava.model.pojo.dbEntity.PhotoRoom;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UnsplashDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PhotoRoom room);

    @Delete
    void delete(PhotoRoom room);

    @Query("SELECT * FROM UnsplashPhoto")
    Flowable<List<PhotoRoom>> getPhotoList();

    @Query("SELECT * FROM UnsplashPhoto WHERE photoID = :photoID")
    Flowable<PhotoRoom> getPhotoById(String photoID);
}
