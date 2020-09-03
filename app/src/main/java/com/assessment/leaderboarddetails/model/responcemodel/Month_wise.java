package com.assessment.leaderboarddetails.model.responcemodel;

import com.google.gson.annotations.SerializedName;

public class Month_wise {

    public int getJan() {
        return jan;
    }

    public void setJan(int jan) {
        this.jan = jan;
    }

    public int getFeb() {
        return feb;
    }

    public void setFeb(int feb) {
        this.feb = feb;
    }

    public int getMar() {
        return mar;
    }

    public void setMar(int mar) {
        this.mar = mar;
    }

    public int getApr() {
        return apr;
    }

    public void setApr(int apr) {
        this.apr = apr;
    }

    public int getMay() {
        return may;
    }

    public void setMay(int may) {
        this.may = may;
    }

    public int getJun() {
        return jun;
    }

    public void setJun(int jun) {
        this.jun = jun;
    }

    @SerializedName("jan")
    private int jan;

    @SerializedName("feb")
    private int feb;

    @SerializedName("mar")
    private int mar;

    @SerializedName("apr")
    private int apr;

    @SerializedName("may")
    private int may;

    @SerializedName("jun")
    private int jun;
}
