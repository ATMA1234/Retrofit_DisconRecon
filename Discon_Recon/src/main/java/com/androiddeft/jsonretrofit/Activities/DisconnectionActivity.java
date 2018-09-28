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

import com.androiddeft.jsonretrofit.Adapters.DisconAdapter;
import com.androiddeft.jsonretrofit.Database.DataHelper;
import com.androiddeft.jsonretrofit.Models.Discon_data;
import com.androiddeft.jsonretrofit.Models.DisconnectionList;
import com.androiddeft.jsonretrofit.R;
import com.androiddeft.jsonretrofit.api.RegisterAPI;
import com.androiddeft.jsonretrofit.helper.RetroClient;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androiddeft.jsonretrofit.Values.Constants.DISCON_LIST_FAILURE;
import static com.androiddeft.jsonretrofit.Values.Constants.DISCON_LIST_SUCCESS;

public class DisconnectionActivity extends AppCompatActivity {
    private ArrayList<Discon_data> disconData;
    private RecyclerView recyclerView;
    private DisconAdapter disconAdapter;
    private ProgressDialog progressDialog;
    DataHelper databaseHelper;
    private Dao<Discon_data, Integer> disconDao;

    private final Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case DISCON_LIST_SUCCESS:
                    progressDialog.dismiss();
                    insert_discon_data();
                    Toast.makeText(DisconnectionActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                    break;
                case DISCON_LIST_FAILURE:
                    progressDialog.dismiss();
                    Toast.makeText(DisconnectionActivity.this, "Data not found!!", Toast.LENGTH_SHORT).show();
                    break;

            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disconnection);
        ButterKnife.bind(this);

        databaseHelper = new DataHelper(this);
        databaseHelper.openDatabase();
//        try {
//            disconDao=databaseHelper.getDisconDao();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        progressDialog = new ProgressDialog(DisconnectionActivity.this);
        progressDialog.setMessage("Loading Data.. Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        DisconData("54003895", "2018-08-05");
    }

    //Request or post data
    public void DisconData(String MRCODE, String DATE) {
        RegisterAPI api = RetroClient.getApiService();
        api.getDisconData(MRCODE, DATE).enqueue(new Callback<DisconnectionList>() {
            @Override
            public void onResponse(@NonNull Call<DisconnectionList> call, @NonNull Response<DisconnectionList> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    DisconnectionList disconnectionList = response.body();
                    disconData = new ArrayList<>(Arrays.asList(disconnectionList.getDiscon_data()));
                    recyclerView = findViewById(R.id.recycler_view);
                    disconAdapter = new DisconAdapter(disconData);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(disconAdapter);
                    mhandler.sendEmptyMessage(DISCON_LIST_SUCCESS);
                } else mhandler.sendEmptyMessage(DISCON_LIST_FAILURE);
            }

            @Override
            public void onFailure(@NonNull Call<DisconnectionList> call, @NonNull Throwable t) {
                mhandler.sendEmptyMessage(DISCON_LIST_FAILURE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    // This is how, DatabaseHelper can be initialized for future use
    private DataHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DataHelper.class);
        }
        return databaseHelper;
    }

    public void insert_discon_data() {
        Discon_data discon_data = new Discon_data();
        try {
            final Dao<Discon_data, Integer> disconDao = getHelper().getDisconDao();
            disconDao.create(discon_data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


