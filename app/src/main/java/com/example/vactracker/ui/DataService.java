package com.example.vactracker.ui;

import com.example.vactracker.ui.data.Vaccine;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DataService {

    //https://api.c3.ai/covid/api/1/therapeuticasset/fetch
    //Headers to dictate the format of the incoming data (we want json format)
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("/covid/api/1/therapeuticasset/fetch")
    Call<Vaccine> sendData(@Body RequestBody body);

//    @FormUrlEncoded
//    @POST("/covid/api/1/therapeuticasset/fetch")
//    Call<Vaccine> sendData(
//            @Field("filter") String filter);
}
