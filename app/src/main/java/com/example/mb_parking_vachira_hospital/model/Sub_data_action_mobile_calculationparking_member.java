package com.example.mb_parking_vachira_hospital.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_data_action_mobile_calculationparking_member {
    @SerializedName("member_message")
    @Expose
    private String memberMessage;
    @SerializedName("record_no")
    @Expose
    private Integer recordNo;
    @SerializedName("cardID")
    @Expose
    private String cardID;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("date_inSub")
    @Expose
    private String dateInSub;
    @SerializedName("date_outSub")
    @Expose
    private String dateOutSub;
    @SerializedName("intPrice")
    @Expose
    private Integer intPrice;
    @SerializedName("intDiscount")
    @Expose
    private Integer intDiscount;
    @SerializedName("intProIDLast")
    @Expose
    private Integer intProIDLast;
    @SerializedName("intPriceOverDate")
    @Expose
    private Integer intPriceOverDate;
    @SerializedName("date_in")
    @Expose
    private String dateIn;
    @SerializedName("date_out")
    @Expose
    private String dateOut;
    @SerializedName("hours")
    @Expose
    private Integer hours;
    @SerializedName("minutes")
    @Expose
    private Integer minutes;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("intLosscard")
    @Expose
    private Integer intLosscard;
    @SerializedName("member_cash_balance")
    @Expose
    private Integer memberCashBalance;
    @SerializedName("member_decreaseCashOrHour")
    @Expose
    private Integer memberDecreaseCashOrHour;
    @SerializedName("member_pro_minute_balance_use")
    @Expose
    private Integer memberProMinuteBalanceUse;
    @SerializedName("date_expire")
    @Expose
    private String dateExpire;
    @SerializedName("base64_img_member_in")
    @Expose
    private Object base64ImgMemberIn;
    @SerializedName("base64_img_member_out")
    @Expose
    private Object base64ImgMemberOut;

    public String getMemberMessage() {
        return memberMessage;
    }

    public void setMemberMessage(String memberMessage) {
        this.memberMessage = memberMessage;
    }

    public Integer getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(Integer recordNo) {
        this.recordNo = recordNo;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getDateInSub() {
        return dateInSub;
    }

    public void setDateInSub(String dateInSub) {
        this.dateInSub = dateInSub;
    }

    public String getDateOutSub() {
        return dateOutSub;
    }

    public void setDateOutSub(String dateOutSub) {
        this.dateOutSub = dateOutSub;
    }

    public Integer getIntPrice() {
        return intPrice;
    }

    public void setIntPrice(Integer intPrice) {
        this.intPrice = intPrice;
    }

    public Integer getIntDiscount() {
        return intDiscount;
    }

    public void setIntDiscount(Integer intDiscount) {
        this.intDiscount = intDiscount;
    }

    public Integer getIntProIDLast() {
        return intProIDLast;
    }

    public void setIntProIDLast(Integer intProIDLast) {
        this.intProIDLast = intProIDLast;
    }

    public Integer getIntPriceOverDate() {
        return intPriceOverDate;
    }

    public void setIntPriceOverDate(Integer intPriceOverDate) {
        this.intPriceOverDate = intPriceOverDate;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIntLosscard() {
        return intLosscard;
    }

    public void setIntLosscard(Integer intLosscard) {
        this.intLosscard = intLosscard;
    }

    public Integer getMemberCashBalance() {
        return memberCashBalance;
    }

    public void setMemberCashBalance(Integer memberCashBalance) {
        this.memberCashBalance = memberCashBalance;
    }

    public Integer getMemberDecreaseCashOrHour() {
        return memberDecreaseCashOrHour;
    }

    public void setMemberDecreaseCashOrHour(Integer memberDecreaseCashOrHour) {
        this.memberDecreaseCashOrHour = memberDecreaseCashOrHour;
    }

    public Integer getMemberProMinuteBalanceUse() {
        return memberProMinuteBalanceUse;
    }

    public void setMemberProMinuteBalanceUse(Integer memberProMinuteBalanceUse) {
        this.memberProMinuteBalanceUse = memberProMinuteBalanceUse;
    }

    public String getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(String dateExpire) {
        this.dateExpire = dateExpire;
    }

    public Object getBase64ImgMemberIn() {
        return base64ImgMemberIn;
    }

    public void setBase64ImgMemberIn(Object base64ImgMemberIn) {
        this.base64ImgMemberIn = base64ImgMemberIn;
    }

    public Object getBase64ImgMemberOut() {
        return base64ImgMemberOut;
    }

    public void setBase64ImgMemberOut(Object base64ImgMemberOut) {
        this.base64ImgMemberOut = base64ImgMemberOut;
    }

}
