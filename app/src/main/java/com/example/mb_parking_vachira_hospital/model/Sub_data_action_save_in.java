package com.example.mb_parking_vachira_hospital.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_data_action_save_in {

    @SerializedName("cardid")
    @Expose
    private String cardid;
    @SerializedName("license_plate")
    @Expose
    private String licensePlate;
    @SerializedName("str_cartype")
    @Expose
    private String strCartype;
    @SerializedName("str_cashiername")
    @Expose
    private String strCashiername;
    @SerializedName("date_in")
    @Expose
    private String dateIn;
    @SerializedName("datetime_in")
    @Expose
    private String datetimeIn;
    @SerializedName("record_no")
    @Expose
    private Integer recordNo;

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStrCartype() {
        return strCartype;
    }

    public void setStrCartype(String strCartype) {
        this.strCartype = strCartype;
    }

    public String getStrCashiername() {
        return strCashiername;
    }

    public void setStrCashiername(String strCashiername) {
        this.strCashiername = strCashiername;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDatetimeIn() {
        return datetimeIn;
    }

    public void setDatetimeIn(String datetimeIn) {
        this.datetimeIn = datetimeIn;
    }

    public Integer getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(Integer recordNo) {
        this.recordNo = recordNo;
    }
}
