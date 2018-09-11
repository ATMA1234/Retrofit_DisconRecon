package com.androiddeft.jsonretrofit.helper;

import com.androiddeft.jsonretrofit.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
        private static String BASE_URL = "http://test_bc_service.hescomtrm.com/Retrofit_Discon_Recon.asmx/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}