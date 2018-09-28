package com.androiddeft.jsonretrofit.helper;

import com.androiddeft.jsonretrofit.api.RegisterAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static final String BASE_URL = "http://test_bc_service.hescomtrm.com/Retrofit_Discon_Recon.asmx/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RegisterAPI getApiService() {
        return getRetrofitInstance().create(RegisterAPI.class);
    }
}