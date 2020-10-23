package com.example.crimereporting.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crimereporting.R;
import com.example.crimereporting.lost.LostAndFound;

public class lost extends Fragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_lost, container, false);
        if(container!=null){
            container.removeAllViews();
        }

        Intent intent = new Intent(getActivity(), LostAndFound.class);
        startActivity(intent);

        return view;
    }

}



