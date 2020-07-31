package com.example.vactracker.ui.settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.vactracker.R;


public class Settings extends Fragment {

    Button b1, b2, b3, b4, b5,b6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        b1 = root.findViewById(R.id.b1);
        b2 = root.findViewById(R.id.b2);
        b3 = root.findViewById(R.id.b3);
        b4 = root.findViewById(R.id.b4);
        b5 = root.findViewById(R.id.b5);
        b6 = root.findViewById(R.id.b6);

        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Language x = new Language();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.layout.fragment_settings, x, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


     return root;
    }
}