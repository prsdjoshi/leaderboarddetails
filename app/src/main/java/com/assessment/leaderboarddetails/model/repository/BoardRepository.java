package com.assessment.leaderboarddetails.model.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.assessment.leaderboarddetails.model.responcemodel.AppResponce;
import com.assessment.leaderboarddetails.model.responcemodel.Record;
import com.assessment.leaderboarddetails.model.webservice.ApiService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardRepository {
    private ArrayList<Record> BoardDetails = new ArrayList<>();
    private MutableLiveData<List<Record>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public BoardRepository(Application application) {
        this.application = application;
    }

    @SuppressLint("LongLogTag")
    public MutableLiveData<List<Record>> getBoardDeatilsMutableLivedata(ApiService apiService)
    {
        Call<AppResponce> call = apiService.callApiToGetBoardDetails("https://plobalapps.s3-ap-southeast-1.amazonaws.com/dummy-app-data.json");
        call.enqueue(new Callback<AppResponce>() {
            @Override
            public void onResponse(Call<AppResponce> call, Response<AppResponce> response) {
                AppResponce responce=response.body();
                if(responce!=null&&responce.getData()!=null)
                {
                    BoardDetails = (ArrayList<Record>) responce.getData();
                    mutableLiveData.setValue(BoardDetails);
                }

            }

            @Override
            public void onFailure(Call<AppResponce> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }
}
