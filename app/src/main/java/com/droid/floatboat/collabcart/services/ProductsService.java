package com.droid.floatboat.collabcart.services;

import android.util.Log;

import com.droid.floatboat.collabcart.data.Session;
import com.droid.floatboat.collabcart.models.Product;
import com.droid.floatboat.collabcart.models.Products;
import com.droid.floatboat.collabcart.net.ApiConnector;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class ProductsService {

    private static final String LOG_TAG = ProductsService.class.getName();

    public static void fetchProducts(String userId, int categoryId, final OnCompleteCallBack onComplete) {

        Call<ArrayList<Product>> call = ApiConnector.getInstance().getApi().getProducts(userId, categoryId);
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Response<ArrayList<Product>> response) {
                int statusCode = response.code();
                ArrayList<Product> products = response.body();
                Session.setProducts(new Products().setProducts(products));
                onComplete.onComplete(true, statusCode);
                Log.d(LOG_TAG, "Products statusCode " + statusCode );
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