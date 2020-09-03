package com.assessment.leaderboarddetails.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.assessment.leaderboarddetails.R;
import com.assessment.leaderboarddetails.model.responcemodel.Month_wise;
import com.assessment.leaderboarddetails.model.responcemodel.Record;
import com.assessment.leaderboarddetails.utils.MonthAxisValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class BoardAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Record> boardDetailsList;
    Context context;
    private LineChart chart;
    String getsortby;
    public BoardAdapter(List<Record> boardDetailsList, Context context, String sortby) {
        this.boardDetailsList = boardDetailsList;
        this.context = context;
        this.getsortby = sortby;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
            holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (boardDetailsList != null && boardDetailsList.size() > 0) {
            return boardDetailsList.size();
        } else {
            return 0;
        }
    }
    public class ViewHolder extends BaseViewHolder {

       // ImageView ivThumbnail;
        TextView tvName,viewdetails,tvDesc, tvData,tvTotaldata;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.title);
            tvDesc = itemView.findViewById(R.id.desc);
            tvData = itemView.findViewById(R.id.data);
            tvTotaldata = itemView.findViewById(R.id.totaldata);
            viewdetails = itemView.findViewById(R.id.viewdetails);
        }

        @Override
        protected void clear() {
            tvName.setText("");
            tvData.setText("");
            tvDesc.setText("");
            tvTotaldata.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            try {
                Record record = boardDetailsList.get(position);

                if (record.getName() != null) {
                    tvName.setText(record.getName());
                }
                tvDesc.setText("------");

                if(getsortby.equalsIgnoreCase("total_sales"))
                {
                    try {
                        tvData.setText("Total Sales");
                        String total = NumberFormat.getIntegerInstance(Locale.US).format(Long.parseLong(record.getData().getTotal_sale().getTotal()));
                        tvTotaldata.setText(record.getCurrency()+total);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }else if(getsortby.equalsIgnoreCase("add_cart"))
                {
                    try {
                        tvData.setText("Add To Cart");
                        String total = NumberFormat.getIntegerInstance(Locale.US).format(Long.parseLong(record.getData().getAdd_to_cart().getTotal()));
                        tvTotaldata.setText(record.getCurrency()+total);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }else if(getsortby.equalsIgnoreCase("downloads"))
                {
                    try {
                        tvData.setText("Downloads");
                        String total = NumberFormat.getIntegerInstance(Locale.US).format(Long.parseLong(record.getData().getDownloads().getTotal()));
                        tvTotaldata.setText(record.getCurrency()+total);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }else if(getsortby.equalsIgnoreCase("user_sessions"))
                {
                    try {
                        tvData.setText("User Sessions");
                        String total = NumberFormat.getIntegerInstance(Locale.US).format(Long.parseLong(record.getData().getSessions().getTotal()));
                        tvTotaldata.setText(record.getCurrency()+total);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                viewdetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showViewDetailsDialog(record);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void showViewDetailsDialog(Record record) {

            LayoutInflater inflater = LayoutInflater.from(context);
            BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(context);
            final View dialogView = inflater.inflate(R.layout.dialog_view_details, null);
            mBottomSheetDialog.setContentView(dialogView);
            mBottomSheetDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnim;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            ((View) dialogView.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
            try {
                TextView dvName = dialogView.findViewById(R.id.title);
                TextView dvData = dialogView.findViewById(R.id.data);
                TextView dvTotaldata = dialogView.findViewById(R.id.totaldata);
                BottomNavigationView navigation = (BottomNavigationView) dialogView.findViewById(R.id.navigation);
                dvName.setText(record.getName());

                chart = dialogView.findViewById(R.id.chart1);
                chart.setBackgroundColor(Color.WHITE);
                chart.getDescription().setEnabled(false);
                chart.setTouchEnabled(true);
                chart.setDrawGridBackground(false);
                chart.setDrawBorders(false);
                chart.getAxisLeft().setEnabled(false);
                chart.getAxisRight().setEnabled(false);
                chart.getAxisRight().setDrawAxisLine(false);
                chart.getAxisRight().setDrawGridLines(false);
                XAxis xAxis = chart.getXAxis();
                xAxis.setDrawGridLines(false);
                chart.setDragEnabled(true);
                chart.setScaleEnabled(true);
                chart.setPinchZoom(true);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setGranularity(1f);
                xAxis.setValueFormatter(new MonthAxisValueFormatter(chart));

                if(getsortby.equalsIgnoreCase("total_sales"))
                {
                    navigation.getMenu().getItem(0).setCheckable(true);
                    navigation.setSelectedItemId(R.id.item0);
                    setTotalSales(dvData,dvTotaldata,record);
                }else if(getsortby.equalsIgnoreCase("add_cart"))
                {

                    navigation.getMenu().getItem(1).setCheckable(true);
                    navigation.setSelectedItemId(R.id.item1);
                    setAddToCart(dvData,dvTotaldata,record);
                }else if(getsortby.equalsIgnoreCase("downloads"))
                {
                    navigation.getMenu().getItem(2).setCheckable(true);
                    navigation.setSelectedItemId(R.id.item2);
                    setDownloas(dvData,dvTotaldata,record);
                }else if(getsortby.equalsIgnoreCase("user_sessions"))
                {
                    navigation.getMenu().getItem(3).setCheckable(true);
                    navigation.setSelectedItemId(R.id.item3);
                    setUserSessions(dvData,dvTotaldata,record);
                }
                navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item0:

                               setTotalSales(dvData,dvTotaldata,record);
                                return true;
                            case R.id.item1:

                                setAddToCart(dvData,dvTotaldata,record);

                                return true;
                            case R.id.item2:

                                setDownloas(dvData,dvTotaldata,record);
                                return true;
                            case R.id.item3:

                                setUserSessions(dvData,dvTotaldata,record);

                                return true;
                        }
                        return false;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            mBottomSheetDialog.show();

        }
        public void setTotalSales(TextView dvData,TextView dvTotaldata,Record record)
        {
            try {
                dvData.setText("Total Sales");
                String total = NumberFormat.getIntegerInstance(Locale.US).format(Long.parseLong(record.getData().getTotal_sale().getTotal()));
                dvTotaldata.setText(record.getCurrency()+total);
                setData(record.getData().getTotal_sale().getMonth_wise(),"Total Sales");
                chart.invalidate();
                chart.animateXY(1000,1000);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        public void setAddToCart(TextView dvData,TextView dvTotaldata,Record record)
        {
            try {
                dvData.setText("Add To Cart");
                String total = NumberFormat.getIntegerInstance(Locale.US).format(Long.parseLong(record.getData().getAdd_to_cart().getTotal()));
                dvTotaldata.setText(record.getCurrency()+total);
                setData(record.getData().getAdd_to_cart().getMonth_wise(),"Add To Cart");
                chart.invalidate();
                chart.animateXY(1000,1000);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        public void setDownloas(TextView dvData,TextView dvTotaldata,Record record)
        {
            try {
                dvData.setText("Downloads");
                String total = NumberFormat.getIntegerInstance(Locale.US).format(Long.parseLong(record.getData().getDownloads().getTotal()));
                dvTotaldata.setText(record.getCurrency()+total);
                setData(record.getData().getDownloads().getMonth_wise(),"Downloads");
                chart.invalidate();
                chart.animateXY(1000,1000);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
        public void setUserSessions(TextView dvData,TextView dvTotaldata,Record record)
        {
            try {
                dvData.setText("User Sessions");
                String total = NumberFormat.getIntegerInstance(Locale.US).format(Long.parseLong(record.getData().getSessions().getTotal()));
                dvTotaldata.setText(record.getCurrency()+total);
                setData(record.getData().getSessions().getMonth_wise(),"User Sessions");
                chart.invalidate();
                chart.animateXY(1000,1000);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        private void setData(Month_wise month_wise,String type) {

            try {
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(0,month_wise.getJan(),"JAN"));
                values.add(new Entry(1,month_wise.getFeb(),"FEB"));
                values.add(new Entry(2,month_wise.getMar(),"MAR"));
                values.add(new Entry(3,month_wise.getApr(),"APR"));
                values.add(new Entry(4,month_wise.getMay(),"MAY"));
                values.add(new Entry(5,month_wise.getJun(),"JUN"));

                LineDataSet set1;

                if (chart.getData() != null &&
                        chart.getData().getDataSetCount() > 0) {
                    set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
                    set1.setValues(values);
                    set1.notifyDataSetChanged();
                    chart.getData().notifyDataChanged();
                    chart.notifyDataSetChanged();
                } else {
                    // create a dataset and give it a type
                    set1 = new LineDataSet(values, type);
                    set1.setDrawIcons(false);

                    // draw dashed line
                //    set1.enableDashedLine(10f, 5f, 0f);

                    // black lines and points
                    set1.setColor(Color.LTGRAY);
                    set1.setCircleColor(Color.DKGRAY);

                    // line thickness and point size
                    set1.setLineWidth(2f);
                    set1.setCircleRadius(3f);

                    // draw points as solid circles
                    set1.setDrawCircleHole(false);

                    // customize legend entry
    //                set1.setFormLineWidth(1f);
    //                set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
    //                set1.setFormSize(15.f);

                    // text size of values
                    set1.setValueTextSize(9f);
                    // draw selection line as dashed
                    set1.enableDashedHighlightLine(10f, 5f, 0f);

                    // set the filled area
                    set1.setDrawFilled(true);
                    set1.setFillFormatter(new IFillFormatter() {
                        @Override
                        public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                            return chart.getAxisLeft().getAxisMinimum();
                        }
                    });

                    // set color of filled area
                    if (Utils.getSDKInt() >= 18) {
                        // drawables only supported on api level 18 and above
                        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fade_gray);
                        set1.setFillDrawable(drawable);
                    } else {
                        set1.setFillColor(Color.BLACK);
                    }

                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1); // add the data sets
                    // create a data object with the data sets
                    LineData data = new LineData(dataSets);
                    // set data
                    chart.setData(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    private int mCurrentPosition;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    protected abstract void clear();
    public void onBind(int position)
    {
        mCurrentPosition = position;
        clear();
    }
    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
