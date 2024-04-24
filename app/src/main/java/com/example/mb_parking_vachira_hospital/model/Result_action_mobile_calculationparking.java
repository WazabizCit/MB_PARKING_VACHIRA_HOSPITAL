package com.example.mb_parking_vachira_hospital.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_action_mobile_calculationparking {
    @SerializedName("bool_status")
    @Expose
    private Boolean boolStatus;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isMember")
    @Expose
    private Boolean isMember;
    @SerializedName("member")
    @Expose
    private Sub_data_action_mobile_calculationparking_member member;
    @SerializedName("visitor")
    @Expose
    private Sub_data_action_mobile_calculationparking_visitor visitor;

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

    public Boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(Boolean isMember) {
        this.isMember = isMember;
    }

    public Sub_data_action_mobile_calculationparking_member getMember() {
        return member;
    }

    public void setMember(Sub_data_action_mobile_calculationparking_member member) {
        this.member = member;
    }

    public Sub_data_action_mobile_calculationparking_visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Sub_data_action_mobile_calculationparking_visitor visitor) {
        this.visitor = visitor;
    }



}



