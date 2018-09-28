package com.androiddeft.jsonretrofit.api;

import com.androiddeft.jsonretrofit.Models.DisconnectionList;
import com.androiddeft.jsonretrofit.Models.MRDetails;
import com.androiddeft.jsonretrofit.Models.ReconnectionList;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterAPI {
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

//    @POST("DisConUpdate")
//    @FormUrlEncoded
//    Call<JsonResponse> Discon_Update(@Field("Acc_id") String ACC_ID,
//                                     @Field("Dis_Date") String DIS_DATE,
//                                     @Field("CURREAD") String CURRENT_READ,
//                                     @Field("Remarks") String REMARKS,
//                                     @Field("Comment") String COMMENT);


    @POST("DisConUpdate")
    @FormUrlEncoded
    void Discon_Update(
            @Field("Acc_id") String ACC_ID,
            @Field("Dis_Date") String DIS_DATE,
            @Field("CURREAD") String CURRENT_READ,
            @Field("Remarks") String REMARKS,
            @Field("Comment") String COMMENT,
            Callback<Response> callback);


//    @FormUrlEncoded
//    @POST("ReConUpdate")
//    void Recon_Update(
//            @Field("Acc_id") String ACC_ID,
//            @Field("Dis_Date") String DIS_DATE,
//            @Field("CURREAD") String CURRENT_READ,
//            @Field("Remarks") String REMARKS,
//            @Field("Comment") String COMMENT,
//            Callback<Response> callback);
}