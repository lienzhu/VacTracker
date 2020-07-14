package com.example.vactracker.ui.vaccines;

import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.vactracker.AppDatabase;
import com.example.vactracker.DAO.ObjDAO;
import com.example.vactracker.R;
import com.example.vactracker.adapters.VaccineAdapter;
import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.data.Vaccine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class VaccinesFragment extends Fragment {

    private VaccineAdapter vaccineAdapter;
    private static final String TAG = "Vaccine Fragment";
    private AppDatabase mDb;
    private RecyclerView mRvList;

    public static final Integer EXTRA_MESSAGE = 0;
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
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        VaccineAdapter.RecyclerViewClickListener listener = new VaccineAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                launchDetail(position);
            }
        };

        vaccineAdapter = new VaccineAdapter(mDb.objDAO().getObjs(),listener);

        mRvList.setAdapter(vaccineAdapter);

        return root;
    }

    private class GetObjTask extends AsyncTask<Void, Void, List<Obj>> {
        @Override
        protected List<Obj> doInBackground(Void... voids) {
            try {

                //API Methods
                //Retrofit converts the HTTP API into a Java interface
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.c3.ai")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.d(TAG, "onBuild: SUCCESS");

                String filter = "{ \"spec\": {\"filter\": \"therapyType == 'Vaccine'\"} }";

                //Call from the created DataService class can make a HTTP request to the remote C3.ai server.
                service = retrofit.create(DataService.class);

                RequestBody body = RequestBody.create(MediaType.parse("text/plain"), filter);
                Call<Vaccine> call = service.sendData(body);
                Response<Vaccine> response = call.execute();
                List<Obj> objs = response.body().getObjs();

                //replacing all values in DB
                mDb.objDAO().deleteAll(mDb.objDAO().getObjs().toArray(new Obj[mDb.objDAO().getObjs().size()]));
                mDb.objDAO().insertAll(objs.toArray(new Obj[objs.size()]));
                return objs;
            } catch (IOException e) {
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
}
