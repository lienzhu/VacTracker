package com.example.vactracker.ui.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vactracker.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
//            Bundle arguments = new Bundle();
//            arguments.putString(DetailFragment.ARG_ITEM_ID, getIntent().getStringExtra(DetailFragment.ARG_ITEM_ID));
            Settings fragment = new Settings();
//            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();
        }
    }
}
