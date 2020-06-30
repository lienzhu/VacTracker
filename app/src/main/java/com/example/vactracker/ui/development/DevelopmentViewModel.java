package com.example.vactracker.ui.development;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DevelopmentViewModel extends ViewModel {

    MutableLiveData<String> mText;

    public DevelopmentViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Development Fragment");
    }

    LiveData<String> getText(){
        return mText;
    }

}
