package com.assessment.leaderboarddetails.model.responcemodel;

import com.google.gson.annotations.SerializedName;

public class Data {

    public TotalData getDownloads() {
        return downloads;
    }

    public void setDownloads(TotalData downloads) {
        this.downloads = downloads;
    }

    public TotalData getSessions() {
        return sessions;
    }

    public void setSessions(TotalData sessions) {
        this.sessions = sessions;
    }

    public TotalData getTotal_sale() {
        return total_sale;
    }

    public void setTotal_sale(TotalData total_sale) {
        this.total_sale = total_sale;
    }

    public TotalData getAdd_to_cart() {
        return add_to_cart;
    }

    public void setAdd_to_cart(TotalData add_to_cart) {
        this.add_to_cart = add_to_cart;
    }

    @SerializedName("downloads")
    private TotalData downloads;
    @SerializedName("sessions")
    private TotalData sessions;
    @SerializedName("total_sale")
    private TotalData total_sale;
    @SerializedName("add_to_cart")
    private TotalData add_to_cart;
}
