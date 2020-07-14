package com.example.vactracker.ui.development;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vactracker.R;
import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.data.Vaccine;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import java.lang.reflect.Type;

public class DevelopmentFragment extends Fragment {

    TextView d;
    private static final String TAG = "Development Fragment";
    private TextView tvCandidate1;
    private TextView tvCandidate2;
    private TextView tvCandidate3;
    private TextView tvCandidate4;
    private TextView tvCandidate5;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;
    private ProgressBar progressBar4;
    private ProgressBar progressBar5;
    private ProgressBar progressBarCircle;


    private Integer vaccineTypeCount = 0;
    private Integer vaccineSize = 0;

    DevelopmentViewModel developmentViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        developmentViewModel = ViewModelProviders.of(this).get(DevelopmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_development, container,false);

        d = root.findViewById(R.id.text_development);

        developmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                d.setText(s);
            }
        });

        tvCandidate1 = root.findViewById(R.id.tvCandidate1);
        tvCandidate2 = root.findViewById(R.id.tvCandidate2);
        tvCandidate3 = root.findViewById(R.id.tvCandidate3);
        tvCandidate4 = root.findViewById(R.id.tvCandidate4);
        tvCandidate5 = root.findViewById(R.id.tvCandidate5);
        progressBar1 = root.findViewById(R.id.progressBar1);
        progressBar2 = root.findViewById(R.id.progressBar2);
        progressBar3 = root.findViewById(R.id.progressBar3);
        progressBar4 = root.findViewById(R.id.progressBar4);
        progressBar5 = root.findViewById(R.id.progressBar5);
        progressBarCircle = root.findViewById(R.id.progressBarCircle);
        progressBarCircle.setIndeterminate(false);
        progressBarCircle.setProgress(34);

        //API Methods
        //Retrofit converts the HTTP API into a Java interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.c3.ai")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "onBuild: SUCCESS");

        String filter = "{ \"spec\": {\"filter\": \"therapyType == 'Vaccine'\"} }";
        //String filter = "{   \"spec\": {     \"filter\": \"therapyType == 'Vaccine' && origin == 'Milken' && contains(nextSteps, 'Phase 2') \"   } }";
        //String filter = "{   \"spec\": {     \"filter\": \"therapyType == 'Vaccine' && target == 'COVID-19' && exists(nextSteps) \"   } }";
        //String filter = "{   \"spec\": {     \"filter\": \"therapyType == 'Vaccine' && target == 'COVID-19'\"} }";
        //Call from the created DataService class can make a HTTP request to the remote C3.ai server.
        DataService service = retrofit.create(DataService.class);

        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), filter);
        Call<Vaccine> response = service.sendData(body);


        //Implementing enqueue method to resolve NetworkOnMainThreadException that would normally occur from using execute().
        response.enqueue(new Callback<Vaccine>() {
            @Override
            public void onResponse(Call<Vaccine> call, Response<Vaccine> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: SUCCESS");

                    Vaccine vaccineInfo = response.body();

//                    JSONArray sortedJsonArray = new JSONArray();
//                    List<JSONObject> jsonList = new ArrayList<JSONObject>();
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        jsonList.add(jsonArray.getJSONObject(i));
//                    }

                    //List<String> names = Arrays.asList("Kevin,Hans,Fritz,Han Solo,Peter".split(","));
//                    Type listType = new TypeToken<List<Vaccine>>() {}.getType();
//                    List<Vaccine> myVaccineList = new Gson().fromJson(vaccineInfo, listType);
//                    Collections.sort(myVaccineList, new MyComparator());


//                    for (int m = 0; m<vaccineInfo.getCount(); m++) {
//                        List<String> names = Arrays.asList(response.body().getObjs().get(m).toString());
//                    }

                    //Set upper limit of i to vaccineInfo.getCount() to search the entire response body for Phases
                    //But as a cheatcode for now, will just pick the first 5 results
                    for (int i = 0; i<5; i++) {

//                        if (vaccineInfo.getObjs().get(vaccineSize).getNextSteps().contains("Phase 3")){
////                            System.out.println("Phase 3 detected");
////                        };
                        tvCandidate1.setText(vaccineInfo.getObjs().get(i).getNextSteps());

                    }

                    System.out.println("Product type: " + vaccineInfo.getObjs().get(0).getProductType());

                    for (int m = 0; m<5; m++) {
                        if(vaccineInfo.getObjs().get(m).getProductType().equals("Protein Subunit")) {
                            vaccineTypeCount = vaccineTypeCount + 1;
                        }
                    }

                    System.out.println("Developer: " + vaccineInfo.getObjs().get(1).getDeveloper());

                    System.out.println("Sample 1 vaccine info: " + vaccineInfo.getObjs().get(0).getDescription());
                    System.out.println("Sample 1 vaccine info: " +vaccineInfo.getObjs().get(0).getStageOfDevelopment());
                    System.out.println("Sample 1 vaccine info: " +vaccineInfo.getObjs().get(0).getNextSteps());
                    System.out.println("Sample 2 vaccine info: " +vaccineInfo.getObjs().get(1).getNextSteps());


                } else {
                    Log.d(TAG, "onResponse: ERROR IS " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Vaccine> call, Throwable t) {
                Log.d(TAG, "onFailure: ON FAILURE IS:" + t.getLocalizedMessage());
            }
        });



        return root;
    }

//    public class MyComparator implements Comparator<Vaccine> {
//        @Override
//        public int compare(Vaccine o1, Vaccine o2) {
//            return o1.getObjs().get(0).getNextSteps().compareTo(o2.getObjs().get(0).getNextSteps());
//        }
//    }
}
