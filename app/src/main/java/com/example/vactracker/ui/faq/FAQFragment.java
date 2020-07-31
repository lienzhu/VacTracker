package com.example.vactracker.ui.faq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.vactracker.R;

public class FAQFragment extends Fragment {

    TextView f;
    Button faq1, faq2, faq3, faq4, faq5, faq6, faq7;


   FAQViewModel faqViewModel;

   DialogFragment myDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

     //   faqViewModel = ViewModelProviders.of(this).get(FAQViewModel.class);
        View root = inflater.inflate(R.layout.fragment_faq, container,false);



        f = root.findViewById(R.id.text_development);
        faq1 = root.findViewById(R.id.tvQ1);
        faq2 = root.findViewById(R.id.tvQ2);
        faq3 = root.findViewById(R.id.tvQ3);
        faq4 = root.findViewById(R.id.tvQ4);
        faq5 = root.findViewById(R.id.tvQ5);
        faq6 = root.findViewById(R.id.tvQ6);
        faq7 = root.findViewById(R.id.tvQ7);



        faq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Faqpopup1 popup = new Faqpopup1();
                Bundle args = new Bundle();
                args.putString("Key","Q1");
                popup.setArguments(args);
                popup.setTargetFragment(FAQFragment.this, 1);
          /**      popup.tvFAQ.setText("What is the VacTracker?");
                popup.tvAnswer.setText("The VACTracker App is one-stop-shop for all information relating to the development of the COVID-19 vaccine. \n " +
                        "The app compiles information from credible news outlets to provide users with access to authentic updates. " +
                        "\nOur goal is to solve the problem of COVID-19 vaccine information being segregated all over the internet, where reliable sources aren’t always easily accessible. ");
               **/
                popup.show(getFragmentManager(), "Faqpopup1");
            }
        });


        faq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Faqpopup1 popup = new Faqpopup1();
                Bundle args = new Bundle();
                args.putString("Key","Q2");
                popup.setArguments(args);
                popup.setTargetFragment(FAQFragment.this, 1);
                popup.show(getFragmentManager(), "Faqpopup1");

            }
        });

        faq3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Faqpopup1 popup = new Faqpopup1();
                Bundle args = new Bundle();
                args.putString("Key","Q3");
                popup.setArguments(args);
                popup.setTargetFragment(FAQFragment.this, 1);
                popup.show(getFragmentManager(), "Faqpopup1");

            }
        });

        faq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Faqpopup1 popup = new Faqpopup1();
                Bundle args = new Bundle();
                args.putString("Key","Q4");
                popup.setArguments(args);
                popup.setTargetFragment(FAQFragment.this, 1);
                popup.show(getFragmentManager(), "Faqpopup1");

            }


        });
        faq5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Faqpopup1 popup = new Faqpopup1();
                Bundle args = new Bundle();
                args.putString("Key","Q5");
                popup.setArguments(args);
                popup.setTargetFragment(FAQFragment.this, 1);
                popup.show(getFragmentManager(), "Faqpopup1");

            }
        });

        faq6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Faqpopup1 popup = new Faqpopup1();
                Bundle args = new Bundle();
                args.putString("Key","Q6");
                popup.setArguments(args);
                popup.setTargetFragment(FAQFragment.this, 1);
                popup.show(getFragmentManager(), "Faqpopup1");

            }
        });

        faq7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Faqpopup1 popup = new Faqpopup1();
                Bundle args = new Bundle();
                args.putString("Key","Q7");
                popup.setArguments(args);
                popup.setTargetFragment(FAQFragment.this, 1);
                popup.show(getFragmentManager(), "Faqpopup1");

            }
        });
        return root;
    }
}


/**
        faqViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                f.setText(s);
            }
        });

        return root;
    }
 **/

