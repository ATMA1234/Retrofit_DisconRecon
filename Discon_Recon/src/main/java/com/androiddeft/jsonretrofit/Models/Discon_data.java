package com.androiddeft.jsonretrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discon_data {

    @SerializedName("ACCT_ID")
    @Expose
    private String ACCOUNT_ID;

    @SerializedName("ARREARS")
    @Expose
    private String ARREARS;

    @SerializedName("DIS_DATE")
    @Expose
    private String DIS_DATE;

    @SerializedName("PREVREAD")
    @Expose
    private String PREVREAD;

    @SerializedName("CONSUMER_NAME")
    @Expose
    private String CONSUMER_NAME;

    @SerializedName("ADD1")
    @Expose
    private String ADDRESS;

    @SerializedName("LAT")
    @Expose
    private String LAT;

    @SerializedName("LON")
    @Expose
    private String LON;

    @SerializedName("MTR_READ")
    @Expose
    private String MTR_READ;

    @SerializedName("COMMENTS")
    @Expose
    private String COMMENTS;

    @SerializedName("REMARKS")
    @Expose
    private String REMARKS;

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getARREARS() {
        return ARREARS;
    }

    public void setARREARS(String ARREARS) {
        this.ARREARS = ARREARS;
    }

    public String getDIS_DATE() {
        return DIS_DATE;
    }

    public void setDIS_DATE(String DIS_DATE) {
        this.DIS_DATE = DIS_DATE;
    }

    public String getPREVREAD() {
        return PREVREAD;
    }

    public void setPREVREAD(String PREVREAD) {
        this.PREVREAD = PREVREAD;
    }

    public String getCONSUMER_NAME() {
        return CONSUMER_NAME;
    }

    public void setCONSUMER_NAME(String CONSUMER_NAME) {
        this.CONSUMER_NAME = CONSUMER_NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLON() {
        return LON;
    }

    public void setLON(String LON) {
        this.LON = LON;
    }

    public String getMTR_READ() {
        return MTR_READ;
    }

    public void setMTR_READ(String MTR_READ) {
        this.MTR_READ = MTR_READ;
    }

    public String getCOMMENTS() {
        return COMMENTS;
    }

    public void setCOMMENTS(String COMMENTS) {
        this.COMMENTS = COMMENTS;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }
}