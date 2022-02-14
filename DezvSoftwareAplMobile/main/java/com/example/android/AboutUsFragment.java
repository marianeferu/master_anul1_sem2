package com.example.android;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutUsFragment extends Fragment{

    ImageView imageView;
//    Button button;
    ObjectAnimator objectAnimator;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);

        imageView = view.findViewById(R.id.car_animation);
//        button = view.findViewById(R.id.btn_move);

        objectAnimator = ObjectAnimator.ofFloat(imageView, "x", 600); // x = move left to right / y top to bottom

        objectAnimator.setDuration(4000);
        objectAnimator.start();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                objectAnimator.setDuration(4000);
//                objectAnimator.start();
//            }
//        });

        return view;

    }



}


