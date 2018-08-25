package com.example.shyam.dagger2sample.service;

import com.example.shyam.dagger2sample.model.UserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ApiService  {

    @GET("api/")
    Call<UserList> userlist(@Query("results") String results);

}
