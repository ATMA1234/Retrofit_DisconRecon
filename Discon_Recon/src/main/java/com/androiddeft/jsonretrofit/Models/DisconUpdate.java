package com.androiddeft.jsonretrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisconUpdate {
    @SerializedName("Acc_id")
    @Expose
    private String ACCOUNT_ID;

    @SerializedName("Dis_Date")
    @Expose
    private String DIS_DATE;

    @SerializedName("CURREAD")
    @Expose
    private String CURRENT_READ;

    @SerializedName("Remarks")
    @Expose
    private String REMARKS;

    @SerializedName("Comment")
    @Expose
    private String COMMENT;

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getDIS_DATE() {
        return DIS_DATE;
    }

    public void setDIS_DATE(String DIS_DATE) {
        this.DIS_DATE = DIS_DATE;
    }

    public String getCURRENT_READ() {
        return CURRENT_READ;
    }

    public void setCURRENT_READ(String CURRENT_READ) {
        this.CURRENT_READ = CURRENT_READ;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
    }
}
