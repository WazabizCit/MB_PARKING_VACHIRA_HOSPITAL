package com.example.mb_parking_vachira_hospital.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_data_action_mobile_savepromotion {


    @SerializedName("promotion_id")
    @Expose
    private Integer promotionId;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
}
