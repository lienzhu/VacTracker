package com.example.vactracker.ui.vaccines;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.vactracker.AppDatabase;

import com.example.vactracker.R;
import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Obj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VaccinesDetailFragment extends Fragment {

    public static String EXTRA_MESSAGE = "id";
    private Obj vaccineObject;
    private static final String TAG = "Vaccine Detail";
    private AppDatabase mDb;

    DataService service;

    public VaccinesDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDb = Room.databaseBuilder(getContext(), AppDatabase.class, "app-database").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.vaccines_detail, container, false);


        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra(VaccinesFragment.EXTRA_MESSAGE);
        vaccineObject = mDb.objDAO().getObj(id);
        System.out.println(vaccineObject.getDeveloper());

        //populating fields
        if (vaccineObject.getProductType() == null) {
            ((TextView) root.findViewById(R.id.product_type)).setText("Unknown");
        } else {
            ((TextView) root.findViewById(R.id.product_type)).setText(vaccineObject.getProductType());
        }

        if (vaccineObject.getDescription() == null) {
            ((TextView) root.findViewById(R.id.description)).setText("Unknown");
        } else {
            ((TextView) root.findViewById(R.id.description)).setText(vaccineObject.getDescription());
        }

        if (vaccineObject.getDeveloper() == null) {
            ((TextView) root.findViewById(R.id.developer)).setText("Unknown");
        } else {
            ((TextView) root.findViewById(R.id.developer)).setText(vaccineObject.getDeveloper());
        }


        if (vaccineObject.getOrigin() == null) {
            ((TextView) root.findViewById(R.id.origin)).setText("Unknown");
        } else {
            ((TextView) root.findViewById(R.id.origin)).setText(vaccineObject.getOrigin());
        }

        if (vaccineObject.getFundingSources() == null) {
            ((TextView) root.findViewById(R.id.funding_source)).setText("Unknown");
        } else {
            ((TextView) root.findViewById(R.id.funding_source)).setText(vaccineObject.getFundingSources());
        }

        if (vaccineObject.getNextSteps() == null) {
            ((TextView) root.findViewById(R.id.next_steps)).setText("Unknown");
        } else {

            ((TextView) root.findViewById(R.id.next_steps)).setText(vaccineObject.getNextSteps());
        }

        if (vaccineObject.getStageOfDevelopment() == null) {
            ((TextView) root.findViewById(R.id.next_steps2)).setText("Unknown");
        } else {
            ((TextView) root.findViewById(R.id.next_steps2)).setText(vaccineObject.getStageOfDevelopment());
        }


        if (vaccineObject.getClinicalTrialsOtherDiseases() == null) {
            ((TextView) root.findViewById(R.id.clinical_button)).setText("Unknown");
        } else {
            ((TextView) root.findViewById(R.id.clinical_button)).setText(vaccineObject.getClinicalTrialsOtherDiseases());
        }


        getActivity().setTitle(vaccineObject.getDeveloper());


        Log.d(TAG, "onCreateView: Success 2");
        return root;
    }


    private class GetVaccineDBTask extends AsyncTask<String, Void, Obj> {
        @Override
        protected Obj doInBackground(String... ids) {
            return mDb.objDAO().getObj(ids[0]);
        }

        @Override
        protected void onPostExecute(Obj obj) {
            vaccineObject = obj;

        }
    }
}