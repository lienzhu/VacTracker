package com.example.vactracker.ui.vaccines;

import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.content.Intent;
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

import com.example.vactracker.R;
import com.example.vactracker.adapters.VaccineAdapter;
import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Vaccine;

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
    private TextView random;
    private static final String TAG = "Vaccine Repository";


    DataService service;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        vaccineAdapter = new VaccineAdapter();

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
        Call<Vaccine> response = service.sendData(body);


        //Implementing enqueue method to resolve NetworkOnMainThreadException that would normally occur from using execute().
        response.enqueue(new Callback<Vaccine>() {
            @Override
            public void onResponse(Call< Vaccine > call, Response<Vaccine> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: SUCCESS");

                    Vaccine vaccineInfo = response.body();
                    //tvQuote.setText(quotes.getValue());
                    System.out.println(vaccineInfo.getObjs().get(0).getDescription());
                    System.out.println(response.body().toString());
                    vaccineAdapter.setResults(vaccineInfo.getObjs());

                } else {
                    Log.d(TAG, "onResponse: ERROR IS " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Vaccine> call, Throwable t) {
                Log.d(TAG, "onFailure: ON FAILURE IS:" + t.getLocalizedMessage());
            }
        });



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_vaccines, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(vaccineAdapter);
        return root;
    }
}
