package com.androiddeft.jsonretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.androiddeft.jsonretrofit.Activities.DisconnectionActivity;
import com.androiddeft.jsonretrofit.Activities.ReconnectionActivity;
import com.androiddeft.jsonretrofit.api.ApiService;
import com.androiddeft.jsonretrofit.Models.ReconnectionList;
import com.androiddeft.jsonretrofit.helper.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.btn_discon);
        button2 = findViewById(R.id.btn_recon);
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


    }


}
