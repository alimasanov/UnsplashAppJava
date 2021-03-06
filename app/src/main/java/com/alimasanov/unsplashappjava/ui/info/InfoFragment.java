package com.alimasanov.unsplashappjava.ui.info;

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

import com.alimasanov.unsplashappjava.R;

public class InfoFragment extends Fragment {

    private TextView textInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        textInfo = root.findViewById(R.id.text_info);

        textInfo.setText(R.string.text_info);

        return root;
    }
}