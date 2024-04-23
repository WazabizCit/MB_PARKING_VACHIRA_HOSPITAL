package com.example.mb_parking_vachira_hospital.model;



        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.List;

public class Result_action_save_in {
    @SerializedName("bool_status")
    @Expose
    private Boolean boolStatus;
    @SerializedName("str_message")
    @Expose
    private String strMessage;
    @SerializedName("datetime_request")
    @Expose
    private String datetimeRequest;
    @SerializedName("str_slip_header")
    @Expose
    private List<Object> strSlipHeader = null;
    @SerializedName("str_slip_footer")
    @Expose
    private List<Object> strSlipFooter = null;
    @SerializedName("data")
    @Expose
    private Sub_data_action_save_in data;

    public Boolean getBoolStatus() {
        return boolStatus;
    }

    public void setBoolStatus(Boolean boolStatus) {
        this.boolStatus = boolStatus;
    }

    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }

    public String getDatetimeRequest() {
        return datetimeRequest;
    }

    public void setDatetimeRequest(String datetimeRequest) {
        this.datetimeRequest = datetimeRequest;
    }

    public List<Object> getStrSlipHeader() {
        return strSlipHeader;
    }

    public void setStrSlipHeader(List<Object> strSlipHeader) {
        this.strSlipHeader = strSlipHeader;
    }

    public List<Object> getStrSlipFooter() {
        return strSlipFooter;
    }

    public void setStrSlipFooter(List<Object> strSlipFooter) {
        this.strSlipFooter = strSlipFooter;
    }

    public Sub_data_action_save_in getData() {
        return data;
    }

    public void setData(Sub_data_action_save_in data) {
        this.data = data;
    }

}

