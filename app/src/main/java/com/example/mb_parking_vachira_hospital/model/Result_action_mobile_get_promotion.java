package com.example.mb_parking_vachira_hospital.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result_action_mobile_get_promotion {


    @SerializedName("bool_status")
    @Expose
    private Boolean boolStatus;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Sub_data_action_mobile_getpromotion> data;

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

    public List<Sub_data_action_mobile_getpromotion> getData() {
        return data;
    }

    public void setData(List<Sub_data_action_mobile_getpromotion> data) {
        this.data = data;
    }

}
