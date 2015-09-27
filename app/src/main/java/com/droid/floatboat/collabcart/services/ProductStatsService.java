package com.droid.floatboat.collabcart.services;

import android.util.Log;

import com.droid.floatboat.collabcart.data.Session;
import com.droid.floatboat.collabcart.models.BarchartData;
import com.droid.floatboat.collabcart.net.ApiConnector;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by naveen on 9/27/2015.
 */
public class ProductStatsService {
    private static final String LOG_TAG = ProductStatsService.class.getName();

    public static void fetchViewsCount(String userId, int productId, final OnCompleteCallBack onComplete) {

        Call<ArrayList<BarchartData>> call = ApiConnector.getInstance().getApi().getProductViews(userId, productId);
        call.enqueue(new Callback<ArrayList<BarchartData>>() {
            @Override
            public void onResponse(Response<ArrayList<BarchartData>> response) {
                int statusCode = response.code();
                ArrayList<BarchartData> productViews = response.body();
                Session.setProductViewsData(productViews);
                onComplete.onComplete(true, statusCode);
                Log.d(LOG_TAG, "Products statusCode " + statusCode);
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                onComplete.onComplete(false, 500);
                Log.e(LOG_TAG, "Failed to fetch products" + t.getMessage());
            }
        });
    }

    public static void fetchPurchaseCount(String userId, int productId, final OnCompleteCallBack onComplete) {

        Call<ArrayList<BarchartData>> call = ApiConnector.getInstance().getApi().getProductPurchases(userId, productId);
        call.enqueue(new Callback<ArrayList<BarchartData>>() {
            @Override
            public void onResponse(Response<ArrayList<BarchartData>> response) {
                int statusCode = response.code();
                ArrayList<BarchartData> productPurchases = response.body();
                Session.setProductPurchaseData(productPurchases);
                onComplete.onComplete(true, statusCode);
                Log.d(LOG_TAG, "Products statusCode " + statusCode);
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                onComplete.onComplete(false, 500);
                Log.e(LOG_TAG, "Failed to fetch products" + t.getMessage());
            }
        });
    }
}
