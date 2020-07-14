package com.example.vactracker.ui.vaccines;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vactracker.R;

public class VaccinesDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccines_detail_activity);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putInt(String.valueOf(VaccinesDetailFragment.EXTRA_MESSAGE), getIntent().getIntExtra(String.valueOf(VaccinesDetailFragment.EXTRA_MESSAGE),0));
            VaccinesDetailFragment fragment = new VaccinesDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();


        }
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
}
