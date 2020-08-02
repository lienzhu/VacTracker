package com.example.vactracker;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.vactracker.ui.DataService;
import com.example.vactracker.ui.data.Obj;
import com.example.vactracker.ui.data.Vaccine;
import com.example.vactracker.ui.settings.Settings;
import com.example.vactracker.ui.settings.SettingsActivity;
import com.example.vactracker.ui.settings.TermsOfUse;
import com.example.vactracker.ui.vaccines.VaccinesDetailActivity;
import com.example.vactracker.ui.vaccines.VaccinesFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
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


        mapServiceCheck();

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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

                //API Method
                //Retrofit converts the HTTP API into a Java interface
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.c3.ai")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.d(TAG, "onBuild: SUCCESS");

                String filter = "{   \"spec\": {     \"filter\": \"therapyType == 'Vaccine' && target == 'COVID-19'\"} }";

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

                //Updating product type attributes in the database
                for (int j = 0; j<mDb.objDAO().getObjs().size(); j++) {

                    String vaccineType = mDb.objDAO().getObjs().get(j).getDescription();

                    if (vaccineType.contains("DNA")){
                        mDb.objDAO().updateProductType("DNA-based",mDb.objDAO().getObjs().get(j).getId());
                    } else if (vaccineType.contains("Non-replicating")){
                        mDb.objDAO().updateProductType("Non-replicating viral vector",mDb.objDAO().getObjs().get(j).getId());
                    } else if (vaccineType.contains("virus-like")){
                        mDb.objDAO().updateProductType("Virus-like particle",mDb.objDAO().getObjs().get(j).getId());
                    } else if (vaccineType.contains("Inactiv")){
                        mDb.objDAO().updateProductType("Inactivated virus",mDb.objDAO().getObjs().get(j).getId());
                    } else if (vaccineType.contains("attenuated")){
                        mDb.objDAO().updateProductType("Live attenuated virus",mDb.objDAO().getObjs().get(j).getId());
                    } else if (vaccineType.contains("subunit")) {
                        mDb.objDAO().updateProductType("Protein Subunit",mDb.objDAO().getObjs().get(j).getId());
                    } else if (vaccineType.contains("RNA")){
                        mDb.objDAO().updateProductType("RNA-based",mDb.objDAO().getObjs().get(j).getId());
                    } else if (vaccineType.contains("Replicating")){
                        mDb.objDAO().updateProductType("Replicating viral vector",mDb.objDAO().getObjs().get(j).getId());
                    } else if (vaccineType.contains("Unknown")){
                        mDb.objDAO().updateProductType("Unknown",mDb.objDAO().getObjs().get(j).getId());
                    }

                }
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

    public boolean mapServiceCheck(){

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS){
            //everything is fine
            Log.d(TAG, "mapServiceCheck: Google Play Services is working");
            return true;
        }else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //Error occured by fixable
            Log.d(TAG, "mapServiceCheck: Google Play Services is not working, but we can fix it");

        }else {
            Log.d(TAG, "Can't make map requests");
        }
        return false;
    }

}
