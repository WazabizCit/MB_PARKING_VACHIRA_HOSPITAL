package com.example.mb_parking_vachira_hospital.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_action_mobile_checkcardin {

    @SerializedName("cardid")
    @Expose
    private String cardid;
    @SerializedName("str_message")
    @Expose
    private String strMessage;
    @SerializedName("bool_status")
    @Expose
    private Boolean boolStatus;
    @SerializedName("isMember")
    @Expose
    private Boolean isMember;
    @SerializedName("datetime_request")
    @Expose
    private String datetimeRequest;
    @SerializedName("member")
    @Expose
    private Object member;

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }

    public Boolean getBoolStatus() {
        return boolStatus;
    }

    public void setBoolStatus(Boolean boolStatus) {
        this.boolStatus = boolStatus;
    }

    public Boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(Boolean isMember) {
        this.isMember = isMember;
    }

    public String getDatetimeRequest() {
        return datetimeRequest;
    }

    public void setDatetimeRequest(String datetimeRequest) {
        this.datetimeRequest = datetimeRequest;
    }

    public Object getMember() {
        return member;
    }

    public void setMember(Object member) {
        this.member = member;
    }

}