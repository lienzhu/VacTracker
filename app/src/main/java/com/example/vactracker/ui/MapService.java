package com.example.vactracker.ui;

import com.example.vactracker.ui.datamap.Location;
import com.example.vactracker.ui.datamap.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MapService {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("/covid/api/1/clinicaltrial/fetch")
    Call<Map> sendMapData(@Body RequestBody body);

}
