package com.droid.floatboat.collabcart.Charts;

import com.droid.floatboat.collabcart.models.BarchartData;

import java.util.ArrayList;

/**
 * Created by naveen on 9/27/2015.
 */
public interface ChartHelper {
    public void loadData();
    public void setData(ArrayList<BarchartData> data);
}
