package com.alimasanov.unsplashappjava.ui.favorite;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alimasanov.unsplashappjava.App;
import com.alimasanov.unsplashappjava.model.pojo.dbEntity.PhotoRoom;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class FavoriteViewModel extends ViewModel {

    private List<PhotoRoom> arrayList = new ArrayList<>();
    private MutableLiveData<List<PhotoRoom>> listPhoto = new MutableLiveData<>();

    public MutableLiveData<List<PhotoRoom>> getListPhoto() {
        initData();
        return listPhoto;
    }

    @SuppressLint("CheckResult")
    private void initData() {
        App.getInstance().getDb().getUnsplashDAO().getPhotoList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PhotoRoom>>() {
                    @Override
                    public void accept(List<PhotoRoom> list) throws Exception {
                        listPhoto.setValue(list);
                    }
                });
    }
}