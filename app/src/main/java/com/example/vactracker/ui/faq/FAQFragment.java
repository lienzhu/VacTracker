package com.example.vactracker.ui.faq;

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

public class FAQFragment extends Fragment {

    TextView f;

   FAQViewModel faqViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        faqViewModel = ViewModelProviders.of(this).get(FAQViewModel.class);
        View root = inflater.inflate(R.layout.fragment_faq, container,false);

        f = root.findViewById(R.id.text_faq);

        faqViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                f.setText(s);
            }
        });

        return root;
    }
}
