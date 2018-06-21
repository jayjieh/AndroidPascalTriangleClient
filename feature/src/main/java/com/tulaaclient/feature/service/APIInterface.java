package com.tulaaclient.feature.service;

import com.tulaaclient.feature.pojo.PascalPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("?")
    Call<PascalPojo> doGetResults(@Query("num") String page);
}
