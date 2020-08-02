package com.example.vactracker.ui.development;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.vactracker.AppDatabase;
import com.example.vactracker.R;
import com.example.vactracker.ui.MapFragment;
import com.example.vactracker.ui.data.Obj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.Map;

import static java.lang.Double.valueOf;

public class DevelopmentFragment extends Fragment {

    TextView d;

    private Button mapButton;

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


//        developmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                d.setText(s);
//            }
//        });

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

        mapButton = root.findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Intent intent = new Intent(getActivity(), MapActivity.class);
//               startActivity(intent);

//                NavHostFragment.findNavController(DevelopmentFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);


                Fragment nextFrag= new MapFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.nav_host_fragment, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

                //getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);



            }
        });


        ArrayList<String> vaccineDevelopmentArray = new ArrayList<String>();
        ArrayList<Integer> vaccineDevelopmentArrayPhase = new ArrayList<Integer>();
//System.out.println("Size of db obj: " + mDb.objDAO().getObjs().size());

//vaccineObject = mDb.objDAO().getObjs().get(position);
        for (int i = 0; i<mDb.objDAO().getObjs().size(); i++) {
            if (mDb.objDAO().getObjs().get(i).getNextSteps() != null) {
                if (mDb.objDAO().getObjs().get(i).getNextSteps().contains("Phase 4")) {
                    vaccineDevelopmentArray.add(mDb.objDAO().getObjs().get(i).getDeveloper());
                    vaccineDevelopmentArrayPhase.add(8);
                    //System.out.println(mDb.objDAO().getObjs().get(i).getNextSteps());
                } else if ((mDb.objDAO().getObjs().get(i).getNextSteps().contains("Phase 3"))) {
                    vaccineDevelopmentArray.add(mDb.objDAO().getObjs().get(i).getDeveloper());
                    vaccineDevelopmentArrayPhase.add(6);
                } else if (mDb.objDAO().getObjs().get(i).getNextSteps().contains("Phase 2/3")) {
                    vaccineDevelopmentArray.add(mDb.objDAO().getObjs().get(i).getDeveloper());
                    vaccineDevelopmentArrayPhase.add(6);
                } else if (mDb.objDAO().getObjs().get(i).getNextSteps().contains("Phase 2")) {
                    vaccineDevelopmentArray.add(mDb.objDAO().getObjs().get(i).getDeveloper());
                    vaccineDevelopmentArrayPhase.add(4);
                }
            }
        }

        System.out.println(vaccineDevelopmentArray);
        System.out.println(vaccineDevelopmentArrayPhase);

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

        progressBar1.setMax(8);
        progressBar1.setProgress(0);
        progressBar2.setMax(8);
        progressBar2.setProgress(0);
        progressBar3.setMax(8);
        progressBar3.setProgress(0);
        progressBar4.setMax(8);
        progressBar4.setProgress(0);
        progressBar5.setMax(8);
        progressBar5.setProgress(0);

        progressBar1.setProgress(vaccineDevelopmentArrayPhase.get(0));
        progressBar2.setProgress(vaccineDevelopmentArrayPhase.get(1));
        progressBar3.setProgress(vaccineDevelopmentArrayPhase.get(2));
        progressBar4.setProgress(vaccineDevelopmentArrayPhase.get(3));
        progressBar5.setProgress(vaccineDevelopmentArrayPhase.get(4));

        //Types of vaccines
        int countDNA = mDb.objDAO().getCountProductType("DNA-based");
        int countInactiveVirus = mDb.objDAO().getCountProductType("Inactivated virus");;
        int countLiveVirus = mDb.objDAO().getCountProductType("Live attenuated virus");
        int countNonRepViralVector = mDb.objDAO().getCountProductType("Non-replicating viral vector");
        int countProtein = mDb.objDAO().getCountProductType("Protein Subunit");
        int countRepViralVector = mDb.objDAO().getCountProductType("Replicating viral vector");
        int countRNA = mDb.objDAO().getCountProductType("RNA-based");
        int countVirusLike = mDb.objDAO().getCountProductType("Virus-like particle");
        int countUnknown = mDb.objDAO().getCountProductType("Unknown");


        System.out.println(mDb.objDAO().getObjs().get(0).getProductType());

        System.out.println("DNA types: " + countDNA);

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

        //Sum all the vaccine type counts
        double sum = countDNA + countInactiveVirus + countLiveVirus + countNonRepViralVector +
                countProtein + countRepViralVector + countRNA + countVirusLike + countUnknown;
        System.out.println(sum);

        int maxValueInMap=(Collections.max(vaccineMap.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Integer> entry : vaccineMap.entrySet()) { // Iterate through hashmap
                 //sum = sum + entry.getValue(); //Sum of all vaccine type counts

            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey());     // Print the key with max value
                System.out.println(entry.getValue());
                System.out.println("Sum is: " + sum);
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

        System.out.println(vaccineMap);

        return root;
    }

}
