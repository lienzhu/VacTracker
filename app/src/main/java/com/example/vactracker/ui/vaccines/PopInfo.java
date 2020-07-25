package com.example.vactracker.ui.vaccines;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.vactracker.R;

public class PopInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_info);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(800,1400);

        //Set an id to the layout
        ConstraintLayout currentLayout =
                (ConstraintLayout) findViewById(R.id.constraintlayout);


        currentLayout.setBackgroundColor(Color.GRAY);

        TextView popText = findViewById(R.id.pop_text);
        popText.setText("Product type: Vaccine's drug or platform class.\n\nDescription: Short description of the vaccine.\n\nDevelopers: Organisation(s) developing the vaccine.\n\nStage: The vaccine's current phase of clinical development.\n\nOrigin: The source of the data containing the vaccine's R&D details.\n\nOther diseases: Other diseases or pathogens for which the vaccine has undergone or is undergoing clinical development.\n\nFunding source: The organisations funding the vaccine's R&D.\n\nNext steps: Anticipated next steps for the vaccine's clinical development.");
    }
}
