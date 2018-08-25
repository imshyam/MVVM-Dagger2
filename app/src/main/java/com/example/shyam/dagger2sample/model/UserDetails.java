package com.example.shyam.dagger2sample.model;

import com.google.gson.annotations.SerializedName;

public class UserDetails {
    @SerializedName("name")
    private Name name;
    @SerializedName("email")
    private String email;
    @SerializedName("gender")
    private String gender;
    @SerializedName("phone")
    private String phone;

    public String getName() {
        return name.getFullName();
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
