package com.androiddeft.jsonretrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MRDATA {
    @SerializedName("MRCODE")
    @Expose
    private String MRCODE;

    @SerializedName("MRNAME")
    @Expose
    private String MRNAME;

    public String getMRCODE() {
        return MRCODE;
    }

    public void setMRCODE(String MRCODE) {
        this.MRCODE = MRCODE;
    }

    public String getMRNAME() {
        return MRNAME;
    }

    public void setMRNAME(String MRNAME) {
        this.MRNAME = MRNAME;
    }

    public String getSUBDIVCODE() {
        return SUBDIVCODE;
    }

    public void setSUBDIVCODE(String SUBDIVCODE) {
        this.SUBDIVCODE = SUBDIVCODE;
    }

    public String getDISRECONN_VER() {
        return DISRECONN_VER;
    }

    public void setDISRECONN_VER(String DISRECONN_VER) {
        this.DISRECONN_VER = DISRECONN_VER;
    }

    @SerializedName("SUBDIVCODE")
    @Expose
    private String SUBDIVCODE;

    @SerializedName("DISRECONN_VER")
    @Expose
    private String DISRECONN_VER;





}
