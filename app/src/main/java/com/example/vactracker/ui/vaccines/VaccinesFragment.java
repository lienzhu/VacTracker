package com.example.vactracker.ui.vaccines;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.vactracker.AppDatabase;
import com.example.vactracker.R;
import com.example.vactracker.adapters.VaccineAdapter;
import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.data.Vaccine;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class VaccinesFragment extends Fragment {

    private VaccineAdapter vaccineAdapter;
    private static final String TAG = "Vaccine Fragment";
    private AppDatabase mDb;
    private RecyclerView mRvList;
//    private Button allButton, preclinicalButton, clinicalButton;



    public static String EXTRA_MESSAGE = "id";
    DataService service;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDb = Room.databaseBuilder(getContext(), AppDatabase.class, "app-database").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();



        //getting values from DB
        new GetObjDBTask().execute();
        //getting new values
        new GetObjTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_vaccines, container, false);

         mRvList = root.findViewById(R.id.list);
         final Button allButton = root.findViewById(R.id.all_button);
        final Button preclinicalButton = root.findViewById(R.id.preclinical_button);
        final Button clinicalButton = root.findViewById(R.id.clinical_button);

        allButton.setBackgroundResource(R.drawable.button_focus);
        clinicalButton.setBackgroundResource(R.drawable.button_unfocused);
        preclinicalButton.setBackgroundResource(R.drawable.button_unfocused);
        allButton.setTextColor(Color.WHITE);
        preclinicalButton.setTextColor(Color.DKGRAY);
        clinicalButton.setTextColor(Color.DKGRAY);

        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));

        vaccineAdapter = new VaccineAdapter(mDb.objDAO().getObjs());

        mRvList.setAdapter(vaccineAdapter);
        setHasOptionsMenu(true);

        preclinicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRvList.setAdapter(null);
                vaccineAdapter = new VaccineAdapter(mDb.objDAO().getPreclinicalObjs());

                mRvList.setAdapter(vaccineAdapter);

                allButton.setBackgroundResource(R.drawable.button_unfocused);
                clinicalButton.setBackgroundResource(R.drawable.button_unfocused);
                preclinicalButton.setBackgroundResource(R.drawable.button_focus);
                allButton.setTextColor(Color.DKGRAY);
                preclinicalButton.setTextColor(Color.WHITE);
                clinicalButton.setTextColor(Color.DKGRAY);
            }


        });

        clinicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRvList.setAdapter(null);
                vaccineAdapter = new VaccineAdapter(mDb.objDAO().getClinicalObjs());

                mRvList.setAdapter(vaccineAdapter);

                allButton.setBackgroundResource(R.drawable.button_unfocused);
                clinicalButton.setBackgroundResource(R.drawable.button_focus);
                preclinicalButton.setBackgroundResource(R.drawable.button_unfocused);
                allButton.setTextColor(Color.DKGRAY);
                preclinicalButton.setTextColor(Color.DKGRAY);
                clinicalButton.setTextColor(Color.WHITE);
            }
        });

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRvList.setAdapter(null);
                vaccineAdapter = new VaccineAdapter(mDb.objDAO().getObjs());

                mRvList.setAdapter(vaccineAdapter);

                allButton.setBackgroundResource(R.drawable.button_focus);
                clinicalButton.setBackgroundResource(R.drawable.button_unfocused);
                preclinicalButton.setBackgroundResource(R.drawable.button_unfocused);
                allButton.setTextColor(Color.WHITE);
                preclinicalButton.setTextColor(Color.DKGRAY);
                clinicalButton.setTextColor(Color.DKGRAY);
            }
        });


        return root;
    }

    private class GetObjTask extends AsyncTask<Void, Void, List<Obj>> {
        @Override
        protected List<Obj> doInBackground(Void... voids) {
            try {

                List<Obj> objs = mDb.objDAO().getObjs();

                return objs;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Obj> objs) {
            vaccineAdapter.setResults(objs);
        }
    }

    private class GetObjDBTask extends AsyncTask<Void, Void, List<Obj>> {

        @Override
        protected List<Obj> doInBackground(Void... voids) {
            return mDb.objDAO().getObjs();
        }

        @Override
        protected void onPostExecute(List<Obj> objs) {
            vaccineAdapter.setResults(objs);
        }
    }

    //Intent to launch the proceeding screen
    private void launchDetail(int position) {
        Intent intent = new Intent(getActivity(), VaccinesDetailActivity.class);
        intent.putExtra(String.valueOf(EXTRA_MESSAGE), position);
        startActivity(intent);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
       menuInflater.inflate(R.menu.search_menu,menu);
       MenuItem searchItem = menu.findItem(R.id.action_search);
       SearchView searchView = (SearchView) searchItem.getActionView();
       searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               vaccineAdapter.getFilter().filter(newText);
               return false;
           }
       });
        super.onCreateOptionsMenu(menu,menuInflater);
    }


}
