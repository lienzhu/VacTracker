package com.example.vactracker.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vactracker.R;
import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.NewsAdapter;
import com.example.vactracker.ui.NewsService;
import com.example.vactracker.ui.data.Vaccine;
import com.example.vactracker.ui.newsdata.Article;
import com.example.vactracker.ui.newsdata.News;

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

public class HomeFragment extends Fragment {

    public static final String ARG_ITEM_ID = "HomeFragment";
    private static final String TAG = "Home Fragment";
    private HomeViewModel homeViewModel;
    private TextView text_home;
    private TextView tvNumberVaccines;
    private TextView tvNumberVaccinesClinical;
    private RecyclerView rvNews;
    private NewsAdapter mAdapter;
    private List<Article> article;

    private String url ="https://api.c3.ai/covid/api/1/therapeuticasset/fetch";

    private Integer numberVaccines;
    private Integer numberVaccinesClinical = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.tvActivityFeed);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        tvNumberVaccines = root.findViewById(R.id.tvNumberVaccines);
        tvNumberVaccinesClinical = root.findViewById(R.id.tvNumberVaccinesClinical);

        //API Methods - Vaccine Data
        //Retrofit converts the HTTP API into a Java interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.c3.ai")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "onBuild: SUCCESS");

        //String filter = "{ \"spec\": {\"filter\": \"therapyType == 'Vaccine'\"} }";
        String filter = "{   \"spec\": {     \"filter\": \"therapyType == 'Vaccine' && origin == 'Milken' \"   } }";

        //Call from the created DataService class can make a HTTP request to the remote C3.ai server.
        DataService service = retrofit.create(DataService.class);

        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), filter);
        Call<Vaccine> response = service.sendData(body);


        //Implementing enqueue method to resolve NetworkOnMainThreadException that would normally occur from using execute().
        response.enqueue(new Callback<Vaccine>() {
            @Override
            public void onResponse(Call<Vaccine> call, Response<Vaccine> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: SUCCESS");

                    Vaccine vaccineInfo = response.body();
                    numberVaccines = vaccineInfo.getCount();

                    for (int m = 0; m<numberVaccines; m++){
                        if (vaccineInfo.getObjs().get(m).getStageOfDevelopment().equals("Clinical")) {
                            numberVaccinesClinical = numberVaccinesClinical+1;
                        }
                    }

                    //numberVaccinesHumanTrials = vaccineInfo.getObjs().get(0).getStageOfDevelopment().equals("Pre-Clinical");
                    System.out.println("Total number of vaccines is: " + numberVaccines);
                    System.out.println("Developer: " + vaccineInfo.getObjs().get(1).getDeveloper());

                    //tvQuote.setText(quotes.getValue());
                    System.out.println(vaccineInfo.getObjs().get(0).getDescription());
                    System.out.println(vaccineInfo.getObjs().get(0).getStageOfDevelopment());

                    //Set the number of vaccines textview/display on the Home screen
                    tvNumberVaccines.setText(numberVaccines.toString());
                    tvNumberVaccinesClinical.setText(numberVaccinesClinical.toString());

                    //System.out.println(response.body().toString());

                } else {
                    Log.d(TAG, "onResponse: ERROR IS " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Vaccine> call, Throwable t) {
                Log.d(TAG, "onFailure: ON FAILURE IS:" + t.getLocalizedMessage());
            }
        });

        //API Methods - Newsfeed
        //Retrofit converts the HTTP API into a Java interface
        Retrofit retrofitNews = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "onBuild: SUCCESS");


        //Call from the created DataService class can make a HTTP request to the remote C3.ai server.
        NewsService serviceNews = retrofitNews.create(NewsService.class);
        Call<News> newsCall = serviceNews.getNewsData();

        //Implementing enqueue method to resolve NetworkOnMainThreadException that would normally occur from using execute().
        newsCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> newsCall, Response<News> responseNews) {
                if (responseNews.isSuccessful()) {
                    Log.d(TAG, "onResponse: SUCCESS");

                    List<Article> news = responseNews.body().getArticles();
                    //System.out.println("news results: " + news.get(0).getDescription());

                    mAdapter.setNews(news);


                } else {
                    Log.d(TAG, "onResponse: ERROR IS " + responseNews.body());
                }
            }

            @Override
            public void onFailure(Call<News> newsCall, Throwable t) {
                Log.d(TAG, "onFailure: ON FAILURE IS:" + t.getLocalizedMessage());
            }
        });

        //Activity Feed Layout code
        RecyclerView mRecyclerView = root.findViewById(R.id.rvNews);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NewsAdapter(this, new ArrayList<Article>());
        mRecyclerView.setAdapter(mAdapter);



        return root;
    }

}
