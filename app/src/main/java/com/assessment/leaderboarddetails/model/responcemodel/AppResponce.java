package com.assessment.leaderboarddetails.model.responcemodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppResponce
{

    @SerializedName("apps")
    private List<Record> appsdata;

    public void setData(List<Record> data){
        this.appsdata = data;
    }
    public List<Record> getData(){
        return this.appsdata;
    }
}