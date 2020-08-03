package com.example.vactracker.ui.settings;

import android.annotation.SuppressLint;
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
                Bundle args = new Bundle();
                args.putString("Key","Q3");
                x.setArguments(args);
                x.setTargetFragment(Settings.this, 1);
                x.show(getFragmentManager(), "Faqpopup1");


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
                TermsOfUse x = new TermsOfUse();
                x.setTargetFragment(Settings.this, 1);
                x.show(getFragmentManager(), "Faqpopup1");

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Privacy x = new Privacy();
                x.setTargetFragment(Settings.this, 1);
                x.show(getFragmentManager(), "Faqpopup1");

            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutUs x = new AboutUs();
                x.setTargetFragment(Settings.this, 1);
                x.show(getFragmentManager(), "Faqpopup1");

            }
        });

//        b6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


     return root;
    }
}