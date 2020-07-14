package com.example.vactracker.ui;

import com.example.vactracker.ui.data.Vaccine;
import com.example.vactracker.ui.newsdata.Article;
import com.example.vactracker.ui.newsdata.News;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface NewsService {

        //https://api.c3.ai/covid/api/1/therapeuticasset/fetch
        @GET("/v2/everything?q=vaccine&apiKey=016f26fafad34868a0d9e6162477de4c")
        Call<News> getNewsData();

        //Call<News>
}
