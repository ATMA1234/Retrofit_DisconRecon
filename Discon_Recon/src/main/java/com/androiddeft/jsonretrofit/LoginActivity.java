package com.androiddeft.jsonretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.androiddeft.jsonretrofit.Models.MRDATA;
import com.androiddeft.jsonretrofit.Models.MRDetails;
import com.androiddeft.jsonretrofit.api.RegisterAPI;
import com.androiddeft.jsonretrofit.helper.RetroClient;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androiddeft.jsonretrofit.Values.Constants.LOGIN_FAILURE;
import static com.androiddeft.jsonretrofit.Values.Constants.LOGIN_SUCCESS;

public class LoginActivity extends AppCompatActivity {
    ArrayList<MRDATA> mrdata;
    private ProgressDialog progressDialog;


    private final Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case LOGIN_SUCCESS:
                    progressDialog.dismiss();
                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(login);
                    Toast.makeText(LoginActivity.this, "Login Success!!", Toast.LENGTH_SHORT).show();
                    break;
                case LOGIN_FAILURE:
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Data not found!!", Toast.LENGTH_SHORT).show();
                    break;

            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Loading Data.. Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MRDETAILS("54003799", "354016070557564", "99999");
    }

    //get MR data
    public void MRDETAILS(String MRCODE, String DEVICEID, String PASSWORD) {
        RegisterAPI api = RetroClient.getApiService();
        api.getMRDetails(MRCODE, DEVICEID, PASSWORD).enqueue(new Callback<MRDetails>() {
            @Override
            public void onResponse(@NonNull Call<MRDetails> call, @NonNull Response<MRDetails> response) {
                if (response.isSuccessful()) {
                    MRDetails mrDetails = response.body();
                    mrdata = new ArrayList<>(Arrays.asList(mrDetails.getMR_data()));
                    mhandler.sendEmptyMessage(LOGIN_SUCCESS);
                } else
                    mhandler.sendEmptyMessage(LOGIN_FAILURE);
            }

            @Override
            public void onFailure(@NonNull Call<MRDetails> call, @NonNull Throwable t) {
                mhandler.sendEmptyMessage(LOGIN_FAILURE);
            }
        });

    }
}
