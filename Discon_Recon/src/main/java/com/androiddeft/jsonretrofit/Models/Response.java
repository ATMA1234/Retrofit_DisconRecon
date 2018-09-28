package com.androiddeft.jsonretrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Response implements Serializable {
    @SerializedName("message")
    @Expose
    String MESSAGE="";
    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

}
