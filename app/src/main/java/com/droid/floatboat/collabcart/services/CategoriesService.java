package com.droid.floatboat.collabcart.services;

import android.util.Log;

import com.droid.floatboat.collabcart.data.Session;
import com.droid.floatboat.collabcart.models.Categories;
import com.droid.floatboat.collabcart.models.Category;
import com.droid.floatboat.collabcart.net.ApiConnector;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class CategoriesService {

    private static final String LOG_TAG = CategoriesService.class.getName();

    public static void fetchCategories(String userId, final OnCompleteCallBack onComplete) {

        Call<ArrayList<Category>> call = ApiConnector.getInstance().getApi().fetchCategories(userId);
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Response<ArrayList<Category>> response) {
                int statusCode = response.code();
                ArrayList<Category> categories = response.body();
                Session.setCategories(new Categories().setCategories(categories));
                onComplete.onComplete(true, statusCode);
                Log.d(LOG_TAG, "Fetch Categories statusCode " + statusCode + " with response " + categories);
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                onComplete.onComplete(false, 500);
                Log.e(LOG_TAG, "Failed to login" + t);
            }
        });
    }

}