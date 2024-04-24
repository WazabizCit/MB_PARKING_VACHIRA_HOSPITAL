package com.example.mb_parking_vachira_hospital.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_data_action_save_out {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time_in")
    @Expose
    private String timeIn;
    @SerializedName("time_out")
    @Expose
    private String timeOut;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("hours")
    @Expose
    private Integer hours;
    @SerializedName("minutes")
    @Expose
    private Integer minutes;
    @SerializedName("total_minute")
    @Expose
    private Integer totalMinute;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("parking_price")
    @Expose
    private Integer parkingPrice;
    @SerializedName("losscard")
    @Expose
    private Integer losscard;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("path_img_driver_mobile")
    @Expose
    private String pathImgDriverMobile;
    @SerializedName("path_img_license_mobile")
    @Expose
    private String pathImgLicenseMobile;
    @SerializedName("receipt_no")
    @Expose
    private String receiptNo;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getTotalMinute() {
        return totalMinute;
    }

    public void setTotalMinute(Integer totalMinute) {
        this.totalMinute = totalMinute;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getParkingPrice() {
        return parkingPrice;
    }

    public void setParkingPrice(Integer parkingPrice) {
        this.parkingPrice = parkingPrice;
    }

    public Integer getLosscard() {
        return losscard;
    }

    public void setLosscard(Integer losscard) {
        this.losscard = losscard;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPathImgDriverMobile() {
        return pathImgDriverMobile;
    }

    public void setPathImgDriverMobile(String pathImgDriverMobile) {
        this.pathImgDriverMobile = pathImgDriverMobile;
    }

    public String getPathImgLicenseMobile() {
        return pathImgLicenseMobile;
    }

    public void setPathImgLicenseMobile(String pathImgLicenseMobile) {
        this.pathImgLicenseMobile = pathImgLicenseMobile;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

}