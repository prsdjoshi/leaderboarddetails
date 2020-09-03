package com.assessment.leaderboarddetails.model.responcemodel;

import com.google.gson.annotations.SerializedName;

public class Record
{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMoney_format() {
        return money_format;
    }

    public void setMoney_format(String money_format) {
        this.money_format = money_format;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("currency")
    private String currency;

    @SerializedName("money_format")
    private String money_format;

    @SerializedName("data")
    private Data data;


}