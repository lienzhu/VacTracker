package com.example.vactracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.vactracker.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;

    }

    public int[] slide_images = {
//            R.drawable.npcdefault,
//            R.drawable.appscreenshotadventure,
//            R.drawable.appscreenshotfeatured,
//            R.drawable.appscreenshotprofile,

    };

    public String [] slide_descs = {
            "Welcome to CoVac - a mobile application aimed at providing authentic, up-to-date information relating to COVID-19 vaccine development.",
            "See progress made within COVID-19 vaccine development, the leading candidates and relevant news articles. ",
            "See further details regarding each vaccine and its development process.",
            "Support progress within this field by donating (all proceeds will contribute to the Word Health Organisation's COVID-19 Response Fund)."
    };




    @Override
    public int getCount() {
        return slide_descs.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView icon = view.findViewById(R.id.slidericon);
        TextView text = view.findViewById(R.id.slidertext);

        icon.setImageResource(slide_images[position]);
        text.setText(slide_descs[position]);

        container.addView(view);

        return view;



    }

    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((ConstraintLayout)object);
    }



}
