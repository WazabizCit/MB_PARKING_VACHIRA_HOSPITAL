package com.example.mb_parking_vachira_hospital.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_data_action_mobile_get_cartype {

    @SerializedName("cartype_id")
    @Expose
    private Integer cartypeId;
    @SerializedName("cartype_name")
    @Expose
    private String cartypeName;

    public Integer getCartypeId() {
        return cartypeId;
    }

    public void setCartypeId(Integer cartypeId) {
        this.cartypeId = cartypeId;
    }

    public String getCartypeName() {
        return cartypeName;
    }

    public void setCartypeName(String cartypeName) {
        this.cartypeName = cartypeName;
    }
}
