package com.example.vactracker.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


import com.example.vactracker.R;
import com.example.vactracker.ui.datamap.Map;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private ConstraintLayout layoutLoading;
    private static final String TAG = "MapFragment";
    private GoogleMap googleMap;
    private MapService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        layoutLoading = view.findViewById(R.id.layoutLoading);
        layoutLoading.setVisibility(View.VISIBLE);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.nav_host_fragment)).commit();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<String> locationArray = new ArrayList<>();
        this.googleMap = googleMap;
        String url = null;

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

                    for (int i = 0; i < response.body().getCount(); i++) {
                        locationArray.add(mapInfo.getObjs().get(i).getLocation().getId().toString().replace("_", ", "));
                    }

                    for (int j = 0; j < locationArray.size(); j++) {
                        LatLng address = getLocationFromAddress(getActivity().getApplicationContext(), locationArray.get(j));

                        String[] latlong = "-33.8807699,150.99844460000003".split(",");
                        double latitude = Double.parseDouble(latlong[0]);
                        double longitude = Double.parseDouble(latlong[1]);
                        LatLng locationDef = new LatLng(latitude, longitude);

                        if (address == null) {
                            address = locationDef;
                        }
                        //Add marker for locations
                        googleMap.addMarker(new MarkerOptions()
                                .position(address)
                                .title(mapInfo.getObjs().get(j).getUrl())
                                .snippet(
                                        "\n- Vaccine Clinical Trial Details -"
                                                + "\n\nCity: " + locationArray.get(j)
                                                + "\nVaccine Trial Status: " + mapInfo.getObjs().get(j).getTrialStatus()
                                                + "\nPatient Setting: " + mapInfo.getObjs().get(j).getPatientSetting()
                                                + "\nCOVID-19 Status: " + mapInfo.getObjs().get(j).getCovid19Status()

                                                + "\n\nClick this view for more details. "

                                )
                        );

                        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                            @Override
                            public View getInfoWindow(Marker arg0) {
                                return null;
                            }

                            @Override
                            public View getInfoContents(Marker marker) {

                                LinearLayout info = new LinearLayout(getContext());
                                info.setOrientation(LinearLayout.VERTICAL);

                                TextView title = new TextView(getContext());
                                title.setTextColor(Color.TRANSPARENT);
                                title.setGravity(Gravity.CENTER);
                                title.setTypeface(null, Typeface.BOLD);
                                title.setTextSize(1);
                                title.setText(marker.getTitle());

                                TextView snippet = new TextView(getContext());
                                snippet.setTextColor(Color.DKGRAY);
                                snippet.setText(marker.getSnippet());

                                TextView id = new TextView(getContext());
                                id.setTextColor(Color.TRANSPARENT);
                                id.setText(marker.getId());

                                info.addView(title);
                                info.addView(snippet);
                                info.addView(id);

                                return info;
                            }
                        });
                        //Method to open new Clinical trial webpage with details
                        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                String url = marker.getTitle();
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);

                            }

                        });
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationDef));

                        //Map finished loading
                        layoutLoading.setVisibility(View.INVISIBLE);
                    }

                } else {
                    Log.d(TAG, "onResponse: ERROR IS " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d(TAG, "onFailure: ON FAILURE IS:" + t.getLocalizedMessage());
            }


        });
    }

    public void onInfoWindowClick(Marker marker) {
    }

    //Method to get LatLng from String Address
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        String[] latlong = "-33.8807699,150.99844460000003".split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);
        LatLng locationDef = new LatLng(latitude, longitude);

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 2);
            if (address == null) {
                return (locationDef);
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
            if (p1 == null) {
                p1 = locationDef;
            }
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        return p1;
    }


}