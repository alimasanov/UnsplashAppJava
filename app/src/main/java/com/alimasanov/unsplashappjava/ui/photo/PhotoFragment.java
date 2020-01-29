package com.alimasanov.unsplashappjava.ui.photo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alimasanov.unsplashappjava.App;
import com.alimasanov.unsplashappjava.R;
import com.alimasanov.unsplashappjava.adapters.PhotosAdapter;
import com.alimasanov.unsplashappjava.model.pojo.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotoFragment extends Fragment {

    private PhotoViewModel photoViewModel;
    private View root;
    private RecyclerView rv;
    private SwipeRefreshLayout swipeContainer;
    private PhotosAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photoViewModel =
                ViewModelProviders.of(this).get(PhotoViewModel.class);
        root = inflater.inflate(R.layout.fragment_photo, container, false);
        rv = root.findViewById(R.id.photo_rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeContainer = root.findViewById(R.id.swipe_container);

        //обновление данных при свайпе вниз
        swipeContainer.setOnRefreshListener(() -> photoViewModel
                .getUpdatedMutableLiveData()
                .observe(Objects.requireNonNull(getActivity()), photos -> {
                    adapter = new PhotosAdapter(photos);
                    rv.setAdapter(adapter);
                    swipeContainer.setRefreshing(false);
                    Toast.makeText(getActivity(), R.string.photo_updated, Toast.LENGTH_SHORT).show();
        }));
        swipeContainer.setColorSchemeColors(Color.RED,
                Color.GREEN,
                Color.MAGENTA,
                Color.BLUE);

        //уведомление об ошибке при загрузке данных с сервера
        photoViewModel.getErr().observe(Objects.requireNonNull(getActivity()), err ->{
            if(!err) {
                Toast.makeText(getActivity(), R.string.err_load, Toast.LENGTH_SHORT).show();
            }
        });

        //инициализация списка фото
        photoViewModel.getMutableLiveData().observe(Objects.requireNonNull(getActivity()), photos -> {
            adapter = new PhotosAdapter(photos);
            rv.setAdapter(adapter);
        });

        return root;
    }
}