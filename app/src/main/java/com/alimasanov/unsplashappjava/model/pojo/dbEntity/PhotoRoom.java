package com.alimasanov.unsplashappjava.model.pojo.dbEntity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UnsplashPhoto")
public class PhotoRoom {
    @PrimaryKey
    @NonNull
    String photoID;
    @ColumnInfo(name = "SmallURL")
    String smallURL;
    @ColumnInfo(name = "RegularURL")
    String regularURL;

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
