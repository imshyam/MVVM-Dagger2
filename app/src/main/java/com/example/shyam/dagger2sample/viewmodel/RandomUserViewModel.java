package com.example.shyam.dagger2sample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.shyam.dagger2sample.model.UserList;
import com.example.shyam.dagger2sample.service.UserRepository;

import javax.inject.Inject;

public class RandomUserViewModel extends ViewModel {

    private final LiveData<UserList> userListLiveDataObservable;

    @Inject
    public RandomUserViewModel(@NonNull UserRepository userRepository) {
        userListLiveDataObservable = userRepository.getUserDetailList(10);
    }


    public LiveData<UserList> getUserDetailsLiveDataObservable() {
        return userListLiveDataObservable;
    }
}
