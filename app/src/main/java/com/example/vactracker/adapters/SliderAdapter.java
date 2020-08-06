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
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3,
            R.drawable.slider4,

    };

    public String [] slide_descs = {
            "Welcome to CoVac! \n This app delivers authentic, up-to-date information on the COVID-19 vaccine development landscape.",
            "Track the progress of COVID-19 vaccines in development, the leading vaccine candidates and relevant news articles. ",
            "Dive into the details of each vaccine and clinical trial with our search features. ",
            "Support the global race to find a vaccine by donating. \n(All proceeds will contribute to the World Health Organisation's COVID-19 Response Fund)."
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
