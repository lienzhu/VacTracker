package com.example.vactracker.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.vactracker.AppDatabase;
import com.example.vactracker.R;
import com.example.vactracker.ui.NewsAdapter;
import com.example.vactracker.ui.NewsService;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.newsdata.Article;
import com.example.vactracker.ui.newsdata.News;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private TextView tvDate;

    private TextView tvVaccineName;
    private TextView tvVaccineDescription;
    private Button buttonVaccine1;
    private Button buttonVaccine2;
    private Button buttonVaccine3;
    private Button buttonVaccine4;

    private RecyclerView rvNews;
    private NewsAdapter mAdapter;
    private List<Article> article;

    private String url ="https://api.c3.ai/covid/api/1/therapeuticasset/fetch";

    private Integer numberVaccines;
    private Integer numberVaccinesClinical = 0;

    private Obj vaccineObject;
    private AppDatabase mDb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.tvActivityFeed);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        mDb = Room.databaseBuilder(getContext(), AppDatabase.class, "app-database").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

        //Set latest update date
        DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        Date date = new Date();
        String today = "Updated " + dateFormat.format(date);

        tvDate = root.findViewById(R.id.tvDate);
        tvDate.setText(today);

        tvNumberVaccines = root.findViewById(R.id.tvNumberVaccines);
        tvNumberVaccinesClinical = root.findViewById(R.id.tvNumberVaccinesClinical);

        tvNumberVaccines.setText(String.valueOf(mDb.objDAO().getTotal()));
        tvNumberVaccinesClinical.setText(String.valueOf(mDb.objDAO().getClinicalTotal()));

        tvVaccineName = root.findViewById(R.id.tvVaccineName);
        tvVaccineDescription = root.findViewById(R.id.tvVaccineDescription);
        buttonVaccine1 = root.findViewById(R.id.buttonVaccine1);
        buttonVaccine2 = root.findViewById(R.id.buttonVaccine2);
        buttonVaccine3 = root.findViewById(R.id.buttonVaccine3);
        buttonVaccine4 = root.findViewById(R.id.buttonVaccine4);

        tvVaccineName.setText("Oral Polio Vaccine (Phase III)");
        tvVaccineDescription.setText("The oral polio vaccine is an attenuated strain of the virus that causes poliomyelitis.");

        buttonVaccine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvVaccineName.setText("Oral Polio Vaccine (Phase II)");
                tvVaccineDescription.setText("The oral polio vaccine is an attenuated strain of the virus that causes poliomyelitis.");
            }
        });
        buttonVaccine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvVaccineName.setText("Bacille Calmette-Guerin (Phase II)");
                tvVaccineDescription.setText("The BCG is a live attenuated vaccine comprised of the bacteria that causes bovine tuberculosis.");
            }
        });
        buttonVaccine3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvVaccineName.setText("Measles-Mumps-Rubella Vaccine (Phase II)");
                tvVaccineDescription.setText("The measles-mumps-rubella (MMR) vaccine is comprised of live-attenuated strains of these viruses.");
            }
        });
        buttonVaccine4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvVaccineName.setText("IMM-101 (Phase III)");
                tvVaccineDescription.setText("IMM-101 is a vaccine comprised of heat-killed Mycobacterium obuense.");
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
