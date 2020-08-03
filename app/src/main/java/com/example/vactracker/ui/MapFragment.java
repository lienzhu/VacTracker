package com.example.vactracker.ui;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.vactracker.R;
import com.example.vactracker.ui.datamap.Map;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


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


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "MapFragment";
    private MapView googleMap;
    private MapService service;
    private ArrayList<String> locationArray = new ArrayList<>();

    public ArrayList<String> getLocationArray() { return this.locationArray; }
    public void setLocationArray(ArrayList<String> locationArray) { this.locationArray = locationArray; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getMapData();

    // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.nav_host_fragment)).commit();
            }
        });
    }

    public void getMapData(){
        //ArrayList<String> arrayList = getLocationArray();

        //API Methods
        //Retrofit converts the HTTP API into a Java interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.c3.ai")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "onBuild: SUCCESS");

        String filter = "{   \"spec\": {     \"filter\": \"(contains(treatmentType, 'Vaccine'))\"} }";
        //Call from the created DataService class can make a HTTP request to the remote C3.ai server.

        service = retrofit.create(MapService.class);

        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), filter);
        Call<Map> response = service.sendMapData(body);

        response.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: SUCCESS");
                    Map mapInfo = response.body();

                    //Check response output for City/Country
                    System.out.println("Location Info: " + mapInfo.getObjs().get(0).getLocation().getId());

                    for (int i =0; i<response.body().getCount();i++){
                        locationArray.add(mapInfo.getObjs().get(i).getLocation().getId().toString().replace("_",", "));

                    }
                    System.out.println(locationArray.get(1));
                    setLocationArray(locationArray);
                    //System.out.println("Response list is: " + arrayList);
                    System.out.println("Response list is: " + getLocationArray());

                } else {
                    Log.d(TAG, "onResponse: ERROR IS " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d(TAG, "onFailure: ON FAILURE IS:" + t.getLocalizedMessage());
            }


        });
        //return arrayList;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        getMapData();
        //ArrayList<String> mapArray = getMapData();

        GoogleMap mMap = googleMap;

        System.out.println("Map Ready location: " + locationArray);
//        System.out.println("Map Ready location: " + locationArray.get(0));

        for (int j =0; j<locationArray.size(); j++) {
            LatLng address = getLocationFromAddress(getActivity().getApplicationContext(), locationArray.get(j));
            //LatLng address = getLocationFromAddress(getActivity().getApplicationContext(), "Street Number, Street, Suburb, State, Postcode");
            if (address == null) {
                String[] latlong = "-33.8807699,150.99844460000003".split(",");
                double latitude = Double.parseDouble(latlong[0]);
                double longitude = Double.parseDouble(latlong[1]);
                LatLng locationDef = new LatLng(latitude, longitude);
                address = locationDef;
            }
            mMap.addMarker(new MarkerOptions().position(address).title("City: " + locationArray.get(j)));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(address));
        }
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        String[] latlong =  "-33.8807699,150.99844460000003".split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);
        LatLng locationDef = new LatLng(latitude, longitude);

        try {
            // May throw an IOException
            //address = coder.getFromLocationName(strAddress, 5);
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return (locationDef);
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );
            if (p1 == null) {
                p1=locationDef;
            }
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        System.out.println(p1);
        return p1;
    }

}