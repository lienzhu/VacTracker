package com.example.vactracker.ui.vaccines;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.vactracker.AppDatabase;
import com.example.vactracker.R;
import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.data.Vaccine;
import com.example.vactracker.ui.home.HomeFragment;
import com.example.vactracker.ui.support.SupportViewModel;

import java.text.NumberFormat;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class VaccinesDetailFragment extends Fragment {

    public static String EXTRA_MESSAGE = "id";
    private Obj vaccineObject;
    private static final String TAG = "Vaccine Detail";
    private AppDatabase mDb;

    DataService service;

    public VaccinesDetailFragment() {

    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.vaccines_detail, container, false);

//        if(getArguments().containsKey(String.valueOf(VaccinesFragment.EXTRA_MESSAGE))){
//            new GetVaccineDBTask().execute(getArguments().getString(String.valueOf(EXTRA_MESSAGE)));
//            Log.d(TAG, "onCreate: Success");
//        }
        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra(VaccinesFragment.EXTRA_MESSAGE);
        vaccineObject = mDb.objDAO().getObj(id);
        System.out.println(vaccineObject.getDeveloper());
        ((TextView) root.findViewById(R.id.product_type)).setText(vaccineObject.getProductType());
        ((TextView) root.findViewById(R.id.description)).setText(vaccineObject.getDescription());
        ((TextView) root.findViewById(R.id.developer)).setText(vaccineObject.getDeveloper());
        ((TextView) root.findViewById(R.id.stage)).setText(vaccineObject.getStageOfDevelopment());
        ((TextView) root.findViewById(R.id.origin)).setText(vaccineObject.getOrigin());

        ((TextView) root.findViewById(R.id.funding_source)).setText(vaccineObject.getFundingSources());

        ((TextView) root.findViewById(R.id.next_steps)).setText(vaccineObject.getNextSteps());
        ((TextView) root.findViewById(R.id.clinical)).setText(vaccineObject.getClinicalTrialsOtherDiseases());

        Log.d(TAG, "onCreateView: Success 2");
        return root;
    }

//    private void updateUi() {
//        View rootView = getView();
//        if(rootView != null && vaccineObject != null) {
//            ((TextView) rootView.findViewById(R.id.product_type)).setText(vaccineObject.getProductType());
//            ((TextView) rootView.findViewById(R.id.description)).setText(vaccineObject.getDescription());
//            ((TextView) rootView.findViewById(R.id.developer)).setText(vaccineObject.getDeveloper());
//            ((TextView) rootView.findViewById(R.id.stage)).setText(vaccineObject.getStageOfDevelopment());
//            ((TextView) rootView.findViewById(R.id.origin)).setText(vaccineObject.getOrigin());
//            ((TextView) rootView.findViewById(R.id.target)).setText(vaccineObject.getTarget());
//            ((TextView) rootView.findViewById(R.id.id)).setText(vaccineObject.getId());
//
//            ((TextView) rootView.findViewById(R.id.version)).setText(String.valueOf(vaccineObject.getVersion()));
//            ((TextView) rootView.findViewById(R.id.clinical)).setText(vaccineObject.getClinicalTrialsOtherDiseases());
//            ((AppCompatActivity) rootView.getContext()).setTitle(vaccineObject.getId());
//        }
//    }

//    private class GetVaccineDBTask extends AsyncTask<String, Void, Obj> {
//        @Override
//        protected Obj doInBackground(String... ids) {
//            return mDb.objDAO().getObj(ids[0]);
//        }
//
//        @Override
//        protected void onPostExecute(Obj obj) {
//            vaccineObject = obj;
//            updateUi();
//        }
//    }
}