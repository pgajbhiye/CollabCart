package com.droid.floatboat.collabcart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * @author Pallavi
 */

public class ChartActivity extends Activity {

    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYSeries mCurrentSeries;

    private XYSeriesRenderer mCurrentRenderer;
    LinearLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_layout);
        layout = (LinearLayout) findViewById(R.id.chart);
    }


    private void initChart() {
        mCurrentSeries = new XYSeries("Product Statistics");
        mDataset.addSeries(mCurrentSeries);
        mCurrentRenderer = new XYSeriesRenderer();
        mCurrentRenderer.setLineWidth(2);
        mCurrentRenderer.setPointStyle(PointStyle.CIRCLE);
        mRenderer.setPanEnabled(true, false);
        mRenderer.setXAxisMin(0);
        mRenderer.setXRoundedLabels(true);
        mRenderer.setAntialiasing(true);
        mRenderer.setShowLegend(true);
        mRenderer.setZoomEnabled(false, false);
        mRenderer.setShowGrid(true); // we show the grid
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setLabelsTextSize(getResources().getDimension(R.dimen.label_text_size));
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
        mCurrentRenderer.setColor(getResources().getColor(R.color.chart_bg));
        mCurrentRenderer.setColor(getResources().getColor(R.color.chart_color));
        mRenderer.addSeriesRenderer(mCurrentRenderer);
    }

    private void addfakeData() {
        mCurrentSeries.add(1, 2);
        mCurrentSeries.add(2, 3);
        mCurrentSeries.add(3, 2);
        mCurrentSeries.add(4, 5);
        mCurrentSeries.add(5, 4);
    }

    GraphicalView mChart;

    protected void onResume() {
        super.onResume();
        if (mChart == null) {
            initChart();
            addfakeData();
            mChart = ChartFactory.getLineChartView(this, mDataset, mRenderer);
            layout.addView(mChart);
        } else {
            mChart.repaint();
        }
    }
}


