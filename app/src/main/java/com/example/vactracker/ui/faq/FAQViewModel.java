package com.example.vactracker.ui.faq;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FAQViewModel extends ViewModel {

    MutableLiveData<String> mText;

    public FAQViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("FAQ Fragment");
    }

    LiveData<String> getText(){
        return mText;
    }

}