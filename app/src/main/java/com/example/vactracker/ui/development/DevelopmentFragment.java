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
import java.util.HashMap;
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
import java.util.Map;

import static java.lang.Double.valueOf;

public class DevelopmentFragment extends Fragment {

    TextView d;
    private static final String TAG = "Development Fragment";
    private TextView tvVaccineType;
    private TextView tvVaccineTypeValue;
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

        tvVaccineType = root.findViewById(R.id.tvVaccineType);
        tvVaccineTypeValue = root.findViewById(R.id.tvVaccineTypeValue);
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

        ArrayList<String> vaccineDevelopmentArray = new ArrayList<String>();

        //System.out.println("Size of db obj: " + mDb.objDAO().getObjs().size());

        //vaccineObject = mDb.objDAO().getObjs().get(position);
        for (int i = 0; i<mDb.objDAO().getObjs().size(); i++) {
            if(mDb.objDAO().getObjs().get(i).getNextSteps() !=null){
                if (mDb.objDAO().getObjs().get(i).getNextSteps().contains("Phase 3")){
                    vaccineDevelopmentArray.add(mDb.objDAO().getObjs().get(i).getDeveloper());
                    //System.out.println(mDb.objDAO().getObjs().get(i).getNextSteps());
                } else if (mDb.objDAO().getObjs().get(i).getNextSteps().contains("Phase 2")){
                    vaccineDevelopmentArray.add(mDb.objDAO().getObjs().get(i).getDeveloper());
                }
            }
        }

        System.out.println(vaccineDevelopmentArray);

        //Cleaning the display data and setting TextViews
        for (int n =0; n<vaccineDevelopmentArray.size();n++){
            String cleanTitle = vaccineDevelopmentArray.get(n).toString().split("(/)|(,)")[0];
            vaccineDevelopmentArray.set(n,cleanTitle);
        }

        tvCandidate1.setText(vaccineDevelopmentArray.get(0));
        tvCandidate2.setText(vaccineDevelopmentArray.get(1));
        tvCandidate3.setText(vaccineDevelopmentArray.get(2));
        tvCandidate4.setText(vaccineDevelopmentArray.get(3));
        tvCandidate5.setText(vaccineDevelopmentArray.get(4));

        //Types of vaccines
        int countDNA = 0; int countInactiveVirus =0;
        int countLiveVirus = 0; int countNonRepViralVector = 0; int countProtein = 0;
        int countRepViralVector = 0; int countRNA = 0; int countVirusLike = 0; int countUnknown = 0;

        for (int j = 0; j<mDb.objDAO().getObjs().size(); j++) {

            String vaccineType = mDb.objDAO().getObjs().get(j).getDescription();

                if (mDb.objDAO().getObjs().get(j).getDescription().contains("DNA")){
                    countDNA = countDNA + 1;

                } else if (mDb.objDAO().getObjs().get(j).getDescription().contains("Non-replicating")){
                    countNonRepViralVector = countNonRepViralVector + 1;
                } else if (mDb.objDAO().getObjs().get(j).getDescription().contains("virus-like")){
                    countVirusLike = countVirusLike + 1;
                } else if (mDb.objDAO().getObjs().get(j).getDescription().contains("Inactiv")){
                    countInactiveVirus = countInactiveVirus + 1;
                } else if (mDb.objDAO().getObjs().get(j).getDescription().contains("attenuated")){
                    countLiveVirus = countLiveVirus + 1;
                } else if (mDb.objDAO().getObjs().get(j).getDescription().contains("sub")) {
                    countProtein = countProtein + 1;
                } else if (mDb.objDAO().getObjs().get(j).getDescription().contains("RNA")){
                    countRNA = countRNA + 1;
                } else if (mDb.objDAO().getObjs().get(j).getDescription().contains("Replicating")){
                    countRepViralVector = countRepViralVector + 1;
                } else if (mDb.objDAO().getObjs().get(j).getDescription().contains("Unknown")){
                    countUnknown = countUnknown + 1;
                }

        }

//        ArrayList<Integer> vaccineTypeArray = new ArrayList<Integer>();
//        Collections.addAll(vaccineTypeArray, countDNA, countInactiveVirus, countLiveVirus,
//                countNonRepViralVector, countProtein, countRepViralVector,
//                countRNA,countVirusLike,countUnknown);
//        Collections.max(vaccineTypeArray);

        HashMap<String,Integer> vaccineMap = new HashMap<String,Integer>();
        vaccineMap.put("DNA-based",countDNA);
        vaccineMap.put("Inactivated Virus",countInactiveVirus);
        vaccineMap.put("Live Attenuated Virus",countLiveVirus);
        vaccineMap.put("Non-replicating Virus",countNonRepViralVector);
        vaccineMap.put("Protein Subunit",countProtein);
        vaccineMap.put("Replicating Viral Vector",countRepViralVector);
        vaccineMap.put("RNA-based",countRNA);
        vaccineMap.put("Virus-like Particle",countVirusLike);
        vaccineMap.put("Unknown",countUnknown);

        double sum = 0;
        int maxValueInMap=(Collections.max(vaccineMap.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Integer> entry : vaccineMap.entrySet()) { // Iterate through hashmap
                 sum = sum + entry.getValue(); //Sum of all vaccine type counts

            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey());     // Print the key with max value
                System.out.println(entry.getValue());
                System.out.println(sum);
                System.out.println(String.valueOf((entry.getValue()/sum)));
                double percentage = (entry.getValue()/sum)*100;
                int percentageInt = (int) percentage;

                tvVaccineType.setText(entry.getKey().toString());
                //tvVaccineTypeValue.setText(String.valueOf((entry.getValue()/sum)));
                tvVaccineTypeValue.setText(String.valueOf(percentageInt));

                progressBarCircle.setIndeterminate(false);
                progressBarCircle.setProgress(0); //fixing Android Studio progress bar bug
                progressBarCircle.setMax(100);
                progressBarCircle.setProgress(percentageInt);
            }
        }


        System.out.println("DNA: " + countDNA + "," +
                               "Non-replicating: " + countNonRepViralVector + "," +
                        "Virus-Like: " + countVirusLike + "," +
                        "Inactivated: " + countInactiveVirus + "," +
                        "Attentuated Live: " + countLiveVirus + "," +
                        "Protein: " + countProtein + "," +
                        "RNA: " + countRNA + "," +
                        "Replicating: " + countRepViralVector + "," +
                        "Unknown: " + countUnknown
                );


        return root;
    }

}
