package com.example.shyam.dagger2sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserList {
    @SerializedName("results")
    private List<UserDetails> userDetailsList;

    public List<UserDetails> getList() {
        return userDetailsList;
    }
}
