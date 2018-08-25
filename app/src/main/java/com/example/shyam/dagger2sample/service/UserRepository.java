package com.example.shyam.dagger2sample.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.shyam.dagger2sample.model.UserList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {

    private ApiService apiService;
    private static UserRepository userRepository;

    private UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.apiService = retrofit.create(ApiService.class);
    }

    public static UserRepository getInstance() {
        if(userRepository == null) {
            userRepository = new UserRepository();
        }
        return new UserRepository();
    }

    public LiveData<UserList> getUserDetailList(int noOfUsers) {
        final MutableLiveData<UserList> liveData = new MutableLiveData<>();

        apiService.userlist(String.valueOf(noOfUsers)).enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });

        return liveData;
    }

}
