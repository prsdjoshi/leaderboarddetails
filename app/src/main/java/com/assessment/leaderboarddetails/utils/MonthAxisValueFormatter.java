package com.assessment.leaderboarddetails.utils;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by philipp on 02/06/16.
 */
public class MonthAxisValueFormatter extends ValueFormatter
{

    private final LineChart chart;
    private final ArrayList<Object> list_x_axis_name;

    public MonthAxisValueFormatter(LineChart chart) {
        list_x_axis_name = new ArrayList<>();
        list_x_axis_name.add("JAN");
        list_x_axis_name.add("FEB");
        list_x_axis_name.add("MAR");
        list_x_axis_name.add("APR");
        list_x_axis_name.add("MAY");
        list_x_axis_name.add("JUN");
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value) {

        try {
            if(value>=0)
            {
                if (value <= list_x_axis_name.size() - 1) {
                    return String.valueOf(list_x_axis_name.get((int) value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



}
