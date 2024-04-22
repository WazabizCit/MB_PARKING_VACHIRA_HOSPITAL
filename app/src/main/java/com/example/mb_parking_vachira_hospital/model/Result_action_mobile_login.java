package com.example.mb_parking_vachira_hospital.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_action_mobile_login {


    @SerializedName("bool_status")
    @Expose
    private Boolean boolStatus;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Sub_data_action_mobile_login data;

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

    public Sub_data_action_mobile_login getData() {
        return data;
    }

    public void setData(Sub_data_action_mobile_login data) {
        this.data = data;
    }

}
