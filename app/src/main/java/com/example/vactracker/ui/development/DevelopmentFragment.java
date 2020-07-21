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
import androidx.room.Room;

import com.example.vactracker.AppDatabase;
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

    private ArrayList vaccineDevelopmentArray;

    private Integer vaccineTypeCount = 0;
    private Integer vaccineSize = 0;

    private Obj vaccineObject;
    private AppDatabase mDb;


    DevelopmentViewModel developmentViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        developmentViewModel = ViewModelProviders.of(this).get(DevelopmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_development, container,false);

        mDb = Room.databaseBuilder(getContext(), AppDatabase.class, "app-database").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

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

        System.out.println(mDb.objDAO().getObjs().get(0).getNextSteps());

        ArrayList<String> vaccineDevelopmentArray = new ArrayList<String>();

        //Test
        if(mDb.objDAO().getObjs().get(0).getNextSteps().contains("Phase 2")){
            System.out.println("Contains Phase 2");
        }

        //vaccineObject = mDb.objDAO().getObjs().get(position);
        for (int i = 0; i<2; i++) {
            if(mDb.objDAO().getObjs().get(i).getNextSteps().contains("Phase 2")){
                vaccineDevelopmentArray.add(mDb.objDAO().getObjs().get(i).getDeveloper());
            }

        }

        System.out.println(vaccineDevelopmentArray);



        return root;
    }

}
