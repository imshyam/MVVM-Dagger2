package com.example.shyam.dagger2sample.model;

import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("title")
    private String title;
    @SerializedName("first")
    private String first;
    @SerializedName("last")
    private String last;

    public String getFullName() {
        return title + " " + first + " " + last;
    }
}
