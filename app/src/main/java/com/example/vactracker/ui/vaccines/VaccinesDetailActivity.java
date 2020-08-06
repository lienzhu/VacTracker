package com.example.vactracker.ui.vaccines;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.example.vactracker.AppDatabase;
import com.example.vactracker.R;
import com.example.vactracker.ui.data.Obj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VaccinesDetailActivity extends AppCompatActivity {

    private Obj vaccineObject;
    private AppDatabase mDb;
    private TextView title;
    public FloatingActionButton googleSearch;
    private ImageView stageDevIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccines_detail_activity);
        getSupportActionBar().hide();
        mDb = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(String.valueOf(VaccinesDetailFragment.EXTRA_MESSAGE), getIntent().getStringExtra(VaccinesDetailFragment.EXTRA_MESSAGE));
            VaccinesDetailFragment fragment = new VaccinesDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();

            Intent intent = getIntent();
            String id = intent.getStringExtra(VaccinesFragment.EXTRA_MESSAGE);
            vaccineObject = mDb.objDAO().getObj(id);
            if(vaccineObject.getStageOfDevelopment() == null) {
                ((TextView) findViewById(R.id.stage)).setText("Unknown");
            } else {
                ((TextView) findViewById(R.id.stage)).setText(vaccineObject.getStageOfDevelopment());
            }
            title = findViewById(R.id.title);
            title.setText(vaccineObject.getDeveloper().toString().split("(/)|(,)")[0]);

        }

        stageDevIcon = findViewById(R.id.stagedev_icon);

        if(vaccineObject.getStageOfDevelopment().equals("Pre-clincial") || vaccineObject.getStageOfDevelopment().equals("Pre-Clincial") || vaccineObject.getStageOfDevelopment().equals("Pre-clinical") || vaccineObject.getStageOfDevelopment().equals("Pre-Clinical")){
            stageDevIcon.setImageResource(getResources().getIdentifier("preclinical","drawable","com.example.vactracker"));
        } else {
            stageDevIcon.setImageResource(getResources().getIdentifier("clinical","drawable","com.example.vactracker"));
        }

        googleSearch = findViewById(R.id.google_search);
                googleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchVaccine(vaccineObject.getDeveloper());
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchVaccine(String name) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + name + " COVID-19 vaccine"));
        startActivity(intent);
    }
}
