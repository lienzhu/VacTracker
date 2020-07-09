package com.example.vactracker.ui.vaccines;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.vactracker.ui.data.Vaccine;

public class VaccinesViewModel extends AndroidViewModel {


    private LiveData<Vaccine> vaccineLiveData;

    public VaccinesViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {

    }

    public LiveData<Vaccine> getVaccineLiveData() {

        return vaccineLiveData;
    }
}