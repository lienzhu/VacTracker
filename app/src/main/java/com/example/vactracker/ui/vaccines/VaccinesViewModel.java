package com.example.vactracker.ui.vaccines;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VaccinesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VaccinesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Vaccines fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}