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
            "Welcome to TimeQuest! \n\nTimeQuest is a RPG-style history learning game. \n\nYou are a time traveller who has the ability to travel to different points in humanity's history to learn about a variety of ancient civilisations and undertake their trials to prove your worth!",
            "On the Adventure page, explore the different civilisations available to you through written and visual material - you will also be able to write notes. \n\nWhen you're ready, take the trial! The trial consist of 10 questions. If you get 7+ correct, you will win an item drop (or several).",
            "On the Featured page, you will be able to answer some quickfire True or False questions on general history. \n\nA random civilisation will also be displayed below, along with its item drops and the notes you have written for the specific civilisation.",
            "On your Profile page, customise your character and equip items that you have collected throughout your journey. \n\nSome information such as your quiz accuracy and a shortcut to all your written notes will also be available. \n\nBest of luck in your adventures, traveller! "
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

//        ImageView screenshot = view.findViewById(R.id.screenshot);
//        TextView instructionText = view.findViewById(R.id.instructionText);
//
//        screenshot.setImageResource(slide_images[position]);
//        instructionText.setText(slide_descs[position]);

        container.addView(view);

        return view;



    }

    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((ConstraintLayout)object);
    }



}
