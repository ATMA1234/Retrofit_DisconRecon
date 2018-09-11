package com.androiddeft.jsonretrofit.api;

import com.androiddeft.jsonretrofit.Models.DisconnectionList;
import com.androiddeft.jsonretrofit.Models.MRDetails;
import com.androiddeft.jsonretrofit.Models.ReconnectionList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("MRDetails")
    @FormUrlEncoded
    Call<MRDetails> getMRDetails(@Field("MRCode") String MRCODE,
                                 @Field("DeviceId") String DEVICEID,
                                 @Field("PASSWORD") String PASSWORD);

    @POST("DisConList")
    @FormUrlEncoded
    Call<DisconnectionList> getDisconData(@Field("MRCode") String MRCODE,
                                          @Field("Date") String DATE);

    @POST("ReConList")
    @FormUrlEncoded
    Call<ReconnectionList> getReconData(@Field("MRCode") String MRCODE,
                                        @Field("Date") String DATE);
}