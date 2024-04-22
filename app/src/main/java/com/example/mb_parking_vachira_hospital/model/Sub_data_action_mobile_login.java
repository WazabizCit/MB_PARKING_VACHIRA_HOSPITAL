package com.example.mb_parking_vachira_hospital.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_data_action_mobile_login {



    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_no_record")
    @Expose
    private Integer userNoRecord;
    @SerializedName("datetime_login")
    @Expose
    private String datetimeLogin;
    @SerializedName("cardname")
    @Expose
    private String cardname;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("tel")
    @Expose
    private String tel;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserNoRecord() {
        return userNoRecord;
    }

    public void setUserNoRecord(Integer userNoRecord) {
        this.userNoRecord = userNoRecord;
    }

    public String getDatetimeLogin() {
        return datetimeLogin;
    }

    public void setDatetimeLogin(String datetimeLogin) {
        this.datetimeLogin = datetimeLogin;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
