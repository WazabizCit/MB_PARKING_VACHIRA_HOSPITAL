package com.example.mb_parking_vachira_hospital.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sub_data_action_mobile_getpromotion {

    @SerializedName("promotion_id")
    @Expose
    private Integer promotionId;
    @SerializedName("promotion_name")
    @Expose
    private String promotionName;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }


}
