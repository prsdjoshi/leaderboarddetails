package com.assessment.leaderboarddetails.view;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.assessment.leaderboarddetails.R;
import com.assessment.leaderboarddetails.model.responcemodel.Record;
import com.assessment.leaderboarddetails.utils.Tools;
import com.assessment.leaderboarddetails.viewmodel.BoardViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class BoardDetailsActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefresh;
    RecyclerView mRecyclerView;
    public BoardViewModel boardViewModel;
    public BoardAdapter boardAdapter;
    private Button btn_sortby;
    private BottomSheetDialog mBottomSheetDialog;
    private String getsortby = "total_sales";
    List<Record> recordList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_details);
        initializationViews();
        boardViewModel = ViewModelProviders.of(this).get(BoardViewModel.class);
        getBoardDeatilsList();
        swipeRefresh.setOnRefreshListener(() -> {
            getBoardDeatilsList();
        });
        btn_sortby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSortByDialog();
            }
        });
    }


    private void initializationViews() {
        Tools.setSystemBarColor(this, R.color.white);
        Tools.setSystemBarLight(this);
        swipeRefresh = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.blogRecyclerView);
        btn_sortby = findViewById(R.id.sortby);
    }
    private void getBoardDeatilsList() {
        try {
            swipeRefresh.setRefreshing(true);
            boardViewModel.getAllBoardDetails().observe(this, new Observer<List<Record>>() {
                @SuppressLint("LongLogTag")
                @Override
                public void onChanged(List<Record> records) {
                    swipeRefresh.setRefreshing(false);
                    recordList.clear();
                    recordList.addAll(records);
                    prepareRecycleview(records);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareRecycleview(List<Record> recordList) {
        try {
            boardAdapter = new BoardAdapter(recordList, BoardDetailsActivity.this,getsortby);
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            }
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(boardAdapter);
            boardAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showSortByDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        mBottomSheetDialog = new BottomSheetDialog(this);
        final View dialogView = inflater.inflate(R.layout.dialog_bottomsheet_sortby, null);
        mBottomSheetDialog.setContentView(dialogView);
        mBottomSheetDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnim;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ((View) dialogView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        try {
            RadioGroup l_sort = dialogView.findViewById(R.id.l_sort);

            if (getsortby.equals("total_sales")) {
                l_sort.check(R.id.rb_totalsales);
            } else if (getsortby.equals("add_cart")) {
                l_sort.check(R.id.rb_addcart);
            } else if (getsortby.equals("downloads")) {
                l_sort.check(R.id.rb_downloads);
            } else if (getsortby.equals("user_sessions")) {
                l_sort.check(R.id.rb_session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBottomSheetDialog.show();
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked

        switch (view.getId()) {
            case R.id.rb_totalsales:
                try {
                    if (checked) {
                        getsortby = "total_sales";
                        prepareRecycleview(recordList);
                        mBottomSheetDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rb_addcart:
                try {
                    if (checked) {
                        getsortby = "add_cart";
                        prepareRecycleview(recordList);
                        mBottomSheetDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.rb_downloads:
                try {
                    if (checked) {
                        getsortby = "downloads";
                        prepareRecycleview(recordList);
                        mBottomSheetDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rb_session:
                try {
                    if (checked) {
                        getsortby = "user_sessions";
                        prepareRecycleview(recordList);
                        mBottomSheetDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
