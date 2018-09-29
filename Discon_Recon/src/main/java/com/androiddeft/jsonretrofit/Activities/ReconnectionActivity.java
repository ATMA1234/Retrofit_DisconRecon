package com.androiddeft.jsonretrofit.Activities;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.androiddeft.jsonretrofit.Adapters.ReconAdapter;
import com.androiddeft.jsonretrofit.Database.DatabaseHelper;
import com.androiddeft.jsonretrofit.Models.Discon_data;
import com.androiddeft.jsonretrofit.Models.Recon_data;
import com.androiddeft.jsonretrofit.Models.ReconnectionList;
import com.androiddeft.jsonretrofit.R;
import com.androiddeft.jsonretrofit.api.RegisterAPI;
import com.androiddeft.jsonretrofit.helper.RetroClient;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androiddeft.jsonretrofit.Values.Constants.RECON_FAILURE;
import static com.androiddeft.jsonretrofit.Values.Constants.RECON_SUCCESS;

public class ReconnectionActivity extends AppCompatActivity {
    private ArrayList<Recon_data> reconData;
    private RecyclerView recyclerView;
    private ReconAdapter reconAdapter;
    private ProgressDialog progressDialog;
    DatabaseHelper databaseHelper;


    private final Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case RECON_SUCCESS:
                    progressDialog.dismiss();
                    insert_recon_data();
                    Toast.makeText(ReconnectionActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                    break;
                case RECON_FAILURE:
                    progressDialog.dismiss();
                    Toast.makeText(ReconnectionActivity.this, "Data not found!!", Toast.LENGTH_SHORT).show();
                    break;

            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconnection);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();
        progressDialog = new ProgressDialog(ReconnectionActivity.this);
        progressDialog.setMessage("Loading Data.. Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ReconData("54003895", "2018-08-05");
    }

    //get data
    public void ReconData(String MRCODE, String DATE) {
        RegisterAPI api = RetroClient.getApiService();
        api.getReconData(MRCODE, DATE).enqueue(new Callback<ReconnectionList>() {
            @Override
            public void onResponse(@NonNull Call<ReconnectionList> call, @NonNull Response<ReconnectionList> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    ReconnectionList reconnectionList = response.body();
                    reconData = new ArrayList<>(Arrays.asList(reconnectionList.getRecon_data()));
                    recyclerView = findViewById(R.id.recycler_view);
                    reconAdapter = new ReconAdapter(reconData);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(reconAdapter);
                    mhandler.sendEmptyMessage(RECON_SUCCESS);
                } else mhandler.sendEmptyMessage(RECON_FAILURE);
            }

            @Override
            public void onFailure(@NonNull Call<ReconnectionList> call, @NonNull Throwable t) {
                mhandler.sendEmptyMessage(RECON_FAILURE);
            }
        });

    }

    //**********************************************************************************************************
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    // This is how, DatabaseHelper can be initialized for future use ***********************************************
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public void insert_recon_data() {
        Recon_data recon_data = new Recon_data();
        try {
            final Dao<Recon_data, Integer> reconDao = getHelper().getReconDao();
            reconDao.create(recon_data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}