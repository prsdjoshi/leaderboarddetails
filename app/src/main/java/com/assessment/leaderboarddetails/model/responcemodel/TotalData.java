package com.assessment.leaderboarddetails.model.responcemodel;

import com.google.gson.annotations.SerializedName;

public class TotalData {

    @SerializedName("total")
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Month_wise getMonth_wise() {
        return month_wise;
    }

    public void setMonth_wise(Month_wise month_wise) {
        this.month_wise = month_wise;
    }

    @SerializedName("month_wise")
    private Month_wise month_wise;


}
