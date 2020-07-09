package com.example.vactracker.ui.vaccines;

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

import com.example.vactracker.R;
import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.data.Vaccine;
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

    public static final String ARG_ITEM_ID = "item_id";
    private Obj vaccineObject;
    private static final String TAG = "Vaccine Detail";

    DataService service;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(ARG_ITEM_ID)){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.c3.ai")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            String filter = "{ \"spec\": {\"filter\": \"therapyType == 'Vaccine'\"} }";

            //Call from the created DataService class can make a HTTP request to the remote C3.ai server.
            service = retrofit.create(DataService.class);

            RequestBody body = RequestBody.create(MediaType.parse("text/plain"), filter);
            Call<Vaccine> response = service.sendData(body);
            Log.d(TAG, "onCreate: 1");

            //Implementing enqueue method to resolve NetworkOnMainThreadException that would normally occur from using execute().
            response.enqueue(new Callback<Vaccine>() {
                @Override
                public void onResponse(Call< Vaccine > call, Response<Vaccine> response) {
                    if (response.isSuccessful()) {
                        List<Obj> objs = response.body().getObjs();
                        for(Obj obj : objs) {
                            if(obj.getId().equals(getArguments().getString(ARG_ITEM_ID))) {
                                vaccineObject = obj;
                                Log.d(TAG, "onResponse: 2");
                                break;
                            }
                        }
                        updateUi();
                        Log.d(TAG, "onResponse: 3");


                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Vaccine> call, Throwable t) {
                    Log.d(TAG, "onFailure: failed");
                }
            });
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.vaccines_detail, container, false);
        updateUi();
        return root;
    }

    private void updateUi() {
        View rootView = getView();
        if(rootView != null && vaccineObject != null) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            ((TextView) rootView.findViewById(R.id.product_type)).setText(vaccineObject.getProductType());
            ((TextView) rootView.findViewById(R.id.description)).setText(vaccineObject.getDescription());
            ((TextView) rootView.findViewById(R.id.developer)).setText(vaccineObject.getDeveloper());
            ((TextView) rootView.findViewById(R.id.stage)).setText(vaccineObject.getStageOfDevelopment());
            ((TextView) rootView.findViewById(R.id.origin)).setText(vaccineObject.getOrigin());
            ((TextView) rootView.findViewById(R.id.target)).setText(vaccineObject.getTarget());
            ((TextView) rootView.findViewById(R.id.id)).setText(vaccineObject.getId());

            ((TextView) rootView.findViewById(R.id.version)).setText(String.valueOf(vaccineObject.getVersion()));
            ((TextView) rootView.findViewById(R.id.clinical)).setText(vaccineObject.getClinicalTrialsOtherDiseases());
            ((AppCompatActivity) rootView.getContext()).setTitle(vaccineObject.getId());
        }
    }
}