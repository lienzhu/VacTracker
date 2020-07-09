package com.example.vactracker.ui.vaccines;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vactracker.R;
import com.example.vactracker.ui.support.SupportViewModel;

public class VaccinesDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.vaccines_detail, container, false);


        return root;
    }
}