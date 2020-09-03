package com.assessment.leaderboarddetails.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.assessment.leaderboarddetails.MyApplication;
import com.assessment.leaderboarddetails.model.responcemodel.Record;
import com.assessment.leaderboarddetails.model.repository.BoardRepository;
import com.assessment.leaderboarddetails.model.webservice.ApiService;

import java.util.List;

public class BoardViewModel extends AndroidViewModel {

    BoardRepository boardRepository;
    public BoardViewModel(@NonNull Application application) {
        super(application);
        boardRepository = new BoardRepository(application);

    }

    @SuppressLint("LongLogTag")
    public LiveData<List<Record>> getAllBoardDetails()
    {
        MyApplication myApplication =MyApplication.create(getApplication());
        ApiService userService =myApplication.getApiService();
        return boardRepository.getBoardDeatilsMutableLivedata(userService);
    }
}
