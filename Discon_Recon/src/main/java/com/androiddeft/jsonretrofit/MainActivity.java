package com.androiddeft.jsonretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androiddeft.jsonretrofit.Activities.DisconnectionActivity;
import com.androiddeft.jsonretrofit.Activities.ReconnectionActivity;
import com.androiddeft.jsonretrofit.Database.DataHelper;
import com.androiddeft.jsonretrofit.api.RegisterAPI;
import com.androiddeft.jsonretrofit.helper.RetroClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3;
    DataHelper databaseHelper;
    private static final String BASE_URL = "http://test_bc_service.hescomtrm.com/Retrofit_Discon_Recon.asmx/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DataHelper(this);
        databaseHelper.openDatabase();

        button1 = findViewById(R.id.btn_discon);
        button2 = findViewById(R.id.btn_recon);
        button3 = findViewById(R.id.btn_discon_update);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent discon = new Intent(MainActivity.this, DisconnectionActivity.class);
                startActivity(discon);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recon = new Intent(MainActivity.this, ReconnectionActivity.class);
                startActivity(recon);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UPDATE("5429763000", "2018-08-05", "2000", "By Fuse", "reconnected");
                Update();
            }
        });
    }

    //Discon_update
//    public void UPDATE(String ACC_ID, String DIS_DATE, String CURRENT_READ, String REMARKS, String COMMENT) {
//        RegisterAPI apiService = RetroClient.getApiService();
//        apiService.Discon_Update(ACC_ID, DIS_DATE, CURRENT_READ, REMARKS, COMMENT).enqueue(new Callback<DisconUpdate>() {
//            @Override
//            public void onResponse(@NonNull Call<DisconUpdate> call, @NonNull Response<DisconUpdate> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                } else Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(@NonNull Call<DisconUpdate> call, @NonNull Throwable t) {
//                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//  }


    private void Update() {
//        RestAdapter adapter = new RestAdapter.Builder()
//                .setEndpoint(BASE_URL)
//                .build();
//        RegisterAPI api = adapter.create(RegisterAPI.class);

        RegisterAPI api = RetroClient.getApiService();
        api.Discon_Update("5429763000", "2018-08-05", "2000", "By Fuse", "reconnected",
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader;
                        String output = "";
                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}