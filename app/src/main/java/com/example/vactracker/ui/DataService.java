package com.example.vactracker.ui;

import com.example.vactracker.ui.data.Data;
import com.example.vactracker.ui.data.Vaccine;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DataService {

    //https://api.c3.ai/covid/api/1/therapeuticasset/fetch
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    //,"Content-Type: application/json")
    @POST("/covid/api/1/therapeuticasset/fetch")
    Call<Vaccine> sendData(@Body RequestBody body);

//    @FormUrlEncoded
//    @POST("/covid/api/1/therapeuticasset/fetch")
//    Call<Vaccine> sendData(
//            @Field("filter") String filter);
}
