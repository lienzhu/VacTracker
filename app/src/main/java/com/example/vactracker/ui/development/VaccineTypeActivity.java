package com.example.vactracker.ui.development;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vactracker.R;

import static android.view.View.GONE;

public class VaccineTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;

    private TextView type1;
    private TextView type2;
    private TextView type3;
    private TextView type4;
    private TextView type5;
    private TextView type6;
    private TextView type7;
    private TextView type8;
    private TextView type9;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_type);

        //Identify ids
        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
        tv2 = findViewById(R.id.tv2);
        tv2.setOnClickListener(this);
        tv3 = findViewById(R.id.tv3);
        tv3.setOnClickListener(this);
        tv4 = findViewById(R.id.tv4);
        tv4.setOnClickListener(this);
        tv5 = findViewById(R.id.tv5);
        tv5.setOnClickListener(this);
        tv6 = findViewById(R.id.tv6);
        tv6.setOnClickListener(this);
        tv7 = findViewById(R.id.tv7);
        tv7.setOnClickListener(this);
        tv8 = findViewById(R.id.tv8);
        tv8.setOnClickListener(this);
        tv9 = findViewById(R.id.tv9);
        tv9.setOnClickListener(this);

        type1 = findViewById(R.id.type1);
        type2 = findViewById(R.id.type2);
        type3 = findViewById(R.id.type3);
        type4 = findViewById(R.id.type4);
        type5 = findViewById(R.id.type5);
        type6 = findViewById(R.id.type6);
        type7 = findViewById(R.id.type7);
        type8 = findViewById(R.id.type8);
        type9 = findViewById(R.id.type9);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                type1.setVisibility(View.VISIBLE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                type4.setVisibility(View.GONE);
                type5.setVisibility(View.GONE);
                type6.setVisibility(View.GONE);
                type7.setVisibility(View.GONE);
                type8.setVisibility(View.GONE);
                type9.setVisibility(View.GONE);
                break;

            case R.id.tv2:
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.VISIBLE);
                type3.setVisibility(View.GONE);
                type4.setVisibility(View.GONE);
                type5.setVisibility(View.GONE);
                type6.setVisibility(View.GONE);
                type7.setVisibility(View.GONE);
                type8.setVisibility(View.GONE);
                type9.setVisibility(View.GONE);
                break;

            case R.id.tv3:
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.VISIBLE);
                type4.setVisibility(View.GONE);
                type5.setVisibility(View.GONE);
                type6.setVisibility(View.GONE);
                type7.setVisibility(View.GONE);
                type8.setVisibility(View.GONE);
                type9.setVisibility(View.GONE);
                break;
            case R.id.tv4:
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                type4.setVisibility(View.VISIBLE);
                type5.setVisibility(View.GONE);
                type6.setVisibility(View.GONE);
                type7.setVisibility(View.GONE);
                type8.setVisibility(View.GONE);
                type9.setVisibility(View.GONE);
                break;
            case R.id.tv5:
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                type4.setVisibility(View.GONE);
                type5.setVisibility(View.VISIBLE);
                type6.setVisibility(View.GONE);
                type7.setVisibility(View.GONE);
                type8.setVisibility(View.GONE);
                type9.setVisibility(View.GONE);
                break;
            case R.id.tv6:
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                type4.setVisibility(View.GONE);
                type5.setVisibility(View.GONE);
                type6.setVisibility(View.VISIBLE);
                type7.setVisibility(View.GONE);
                type8.setVisibility(View.GONE);
                type9.setVisibility(View.GONE);
                break;
            case R.id.tv7:
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                type4.setVisibility(View.GONE);
                type5.setVisibility(View.GONE);
                type6.setVisibility(View.GONE);
                type7.setVisibility(View.VISIBLE);
                type8.setVisibility(View.GONE);
                type9.setVisibility(View.GONE);
                break;
            case R.id.tv8:
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                type4.setVisibility(View.GONE);
                type5.setVisibility(View.GONE);
                type6.setVisibility(View.GONE);
                type7.setVisibility(View.GONE);
                type8.setVisibility(View.VISIBLE);
                type9.setVisibility(View.GONE);
                break;
            case R.id.tv9:
                type1.setVisibility(View.GONE);
                type2.setVisibility(View.GONE);
                type3.setVisibility(View.GONE);
                type4.setVisibility(View.GONE);
                type5.setVisibility(View.GONE);
                type6.setVisibility(View.GONE);
                type7.setVisibility(View.GONE);
                type8.setVisibility(View.GONE);
                type9.setVisibility(View.VISIBLE);
                break;
        }
    }
}