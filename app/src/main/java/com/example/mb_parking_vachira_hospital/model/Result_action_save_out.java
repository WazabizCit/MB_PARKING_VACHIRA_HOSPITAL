package com.example.mb_parking_vachira_hospital.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result_action_save_out {
    @SerializedName("bool_status")
    @Expose
    private Boolean boolStatus;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Sub_data_action_save_out data;
    @SerializedName("slipHeader")
    @Expose
    private List<Object> slipHeader = null;
    @SerializedName("slipFooter")
    @Expose
    private List<Object> slipFooter = null;

    public Boolean getBoolStatus() {
        return boolStatus;
    }

    public void setBoolStatus(Boolean boolStatus) {
        this.boolStatus = boolStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Sub_data_action_save_out getData() {
        return data;
    }

    public void setData(Sub_data_action_save_out data) {
        this.data = data;
    }

    public List<Object> getSlipHeader() {
        return slipHeader;
    }

    public void setSlipHeader(List<Object> slipHeader) {
        this.slipHeader = slipHeader;
    }

    public List<Object> getSlipFooter() {
        return slipFooter;
    }

    public void setSlipFooter(List<Object> slipFooter) {
        this.slipFooter = slipFooter;
    }

}