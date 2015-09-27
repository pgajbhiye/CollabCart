package com.droid.floatboat.collabcart.Charts;

import com.droid.floatboat.collabcart.models.BarchartData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by naveen on 9/27/2015.
 */
public class BarchartHelper implements ChartHelper {

    private static final String LOG_TAG = BarchartHelper.class.getName();

    protected BarChart mChart;
    private String legend;
    ChartDataProvider chartDataProvider;

    public BarchartHelper(BarChart mChart, String legend, ChartDataProvider chartDataProvider) {
        this.mChart = mChart;
        this.legend = legend;
        this.chartDataProvider = chartDataProvider;

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);


        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        // mChart.setDrawXLabels(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);

        YAxis leftAxis = mChart.getAxisLeft();
        //leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(8, false);
        //leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        loadData();
    }

    @Override
    public void loadData() {
        chartDataProvider.onData();
    }

    @Override
    public void setData(ArrayList<BarchartData> chartData) {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        int i = 0;
        for (BarchartData vcData : chartData) {
            xVals.add(vcData.getX());
            yVals.add(new BarEntry(vcData.getY(), i));
            i++;
        }

        BarDataSet set1 = new BarDataSet(yVals, this.legend);
        set1.setBarSpacePercent(35f);
        set1.setDrawValues(false);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
        //data.setValueTypeface(mTf);

        mChart.setData(data);
        mChart.invalidate();
    }
}
