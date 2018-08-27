package com.example.shyam.dagger2sample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.shyam.dagger2sample.model.UserList;
import com.example.shyam.dagger2sample.service.UserRepository;

public class RandomUserViewModel extends ViewModel {

    private final LiveData<UserList> userListLiveDataObservable;

    RandomUserViewModel() {
        userListLiveDataObservable = UserRepository.getInstance().getUserDetailList(10);
    }


    public LiveData<UserList> getUserDetailsLiveDataObservable() {
        return userListLiveDataObservable;
    }
}