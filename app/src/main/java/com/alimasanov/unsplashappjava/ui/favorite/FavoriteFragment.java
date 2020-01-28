package com.alimasanov.unsplashappjava.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alimasanov.unsplashappjava.R;
import com.alimasanov.unsplashappjava.adapters.DatabaseAdapter;

import java.util.Objects;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel dashboardViewModel;
    private RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        rv = root.findViewById(R.id.fav_rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        dashboardViewModel.getListPhoto().observe(Objects.requireNonNull(getActivity()), photos -> {
            DatabaseAdapter databaseAdapter = new DatabaseAdapter(photos);
            rv.setAdapter(databaseAdapter);
        });

        return root;
    }
}