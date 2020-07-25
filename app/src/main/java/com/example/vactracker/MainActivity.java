package com.example.vactracker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.data.Vaccine;
import com.example.vactracker.ui.vaccines.VaccinesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    private AppBarConfiguration mAppBarConfiguration;


    private DataService service;
    private AppDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDb = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

        new MainActivity.GetObjTask().execute();
        Log.d(TAG, "onCreate: GetObjTask executed");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_development, R.id.nav_vaccines, R.id.nav_support, R.id.nav_faq)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private class GetObjTask extends AsyncTask<Void, Void, List<Obj>> {
        @Override
        protected List<Obj> doInBackground(Void... voids) {
            try {

                //API Methods
                //Retrofit converts the HTTP API into a Java interface
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.c3.ai")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.d(TAG, "onBuild: SUCCESS");

                String filter = "{   \"spec\": {     \"filter\": \"therapyType == 'Vaccine' && target == 'COVID-19'\"} }";
                //String filter = "{   \"spec\": {     \"filter\": \"therapyType == 'Vaccine' \"} }";
                //Call from the created DataService class can make a HTTP request to the remote C3.ai server.
                service = retrofit.create(DataService.class);

                RequestBody body = RequestBody.create(MediaType.parse("text/plain"), filter);
                Call<Vaccine> call = service.sendData(body);
                Response<Vaccine> response = call.execute();
                List<Obj> objs = response.body().getObjs();

                //replacing all values in DB
                mDb.objDAO().deleteAll(mDb.objDAO().getObjs().toArray(new Obj[mDb.objDAO().getObjs().size()]));
                Log.d(TAG, "doInBackground: Deleted");
                mDb.objDAO().insertAll(objs.toArray(new Obj[objs.size()]));
                Log.d(TAG, "doInBackground: Added");
                return objs;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Obj> objs) {

        }
    }


}
