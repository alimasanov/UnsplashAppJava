package com.alimasanov.unsplashappjava.model.pojo.dbEntity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.alimasanov.unsplashappjava.model.pojo.Photo;

@Entity(tableName = "UnsplashPhoto")
public class PhotoRoom {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String photoID;
    private String smallURL;
    private String regularURL;

    public PhotoRoom() {
    }

    public PhotoRoom(int id, String photoID, String smallURL, String regularURL) {
        this.id = id;
        this.photoID = photoID;
        this.smallURL = smallURL;
        this.regularURL = regularURL;
    }

    @Ignore
    public PhotoRoom(Photo photo){
        photoID = photo.getId();
        smallURL = photo.getUrls().getSmall();
        regularURL = photo.getUrls().getRegular();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getSmallURL() {
        return smallURL;
    }

    public void setSmallURL(String smallURL) {
        this.smallURL = smallURL;
    }

    public String getRegularURL() {
        return regularURL;
    }

    public void setRegularURL(String regularURL) {
        this.regularURL = regularURL;
    }
}
