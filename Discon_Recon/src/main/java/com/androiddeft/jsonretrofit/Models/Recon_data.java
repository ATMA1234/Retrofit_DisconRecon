package com.androiddeft.jsonretrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Recon_data implements Serializable {

    @DatabaseField(columnName = "ACCT_ID")
    @SerializedName("ACCT_ID")
    @Expose
    private String ACCOUNT_ID;
    @DatabaseField(columnName = "ARREARS")
    @SerializedName("ARREARS")
    @Expose
    private String ARREARS;
    @DatabaseField(columnName = "RE_DATE")
    @SerializedName("RE_DATE")
    @Expose
    private String RE_DATE;
    @DatabaseField(columnName = "PREVREAD")
    @SerializedName("PREVREAD")
    @Expose
    private String PREVREAD;
    @DatabaseField(columnName = "CONSUMER_NAME")
    @SerializedName("CONSUMER_NAME")
    @Expose
    private String CONSUMER_NAME;
    @DatabaseField(columnName = "ADD1")
    @SerializedName("ADD1")
    @Expose
    private String ADDRESS;
    @DatabaseField(columnName = "LAT")
    @SerializedName("LAT")
    @Expose
    private String LAT;
    @DatabaseField(columnName = "LON")
    @SerializedName("LON")
    @Expose
    private String LON;
    @DatabaseField(columnName = "MTR_READ")
    @SerializedName("MTR_READ")
    @Expose
    private String MTR_READ;
    @DatabaseField(columnName = "COMMENTS")
    @SerializedName("COMMENTS")
    @Expose
    private String COMMENTS;
    @DatabaseField(columnName = "REMARKS")
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

    public String getRE_DATE() {
        return RE_DATE;
    }

    public void setRE_DATE(String RE_DATE) {
        this.RE_DATE = RE_DATE;
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
