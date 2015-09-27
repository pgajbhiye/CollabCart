package com.droid.floatboat.collabcart.services;

import android.util.Log;

import com.droid.floatboat.collabcart.data.Session;
import com.droid.floatboat.collabcart.models.User;
import com.droid.floatboat.collabcart.models.UserDetails;
import com.droid.floatboat.collabcart.net.ApiConnector;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class UserDetailsService {

    private static final String LOG_TAG = UserDetailsService.class.getName();

    public static void loginUser(User user, final OnCompleteCallBack onComplete) {

        Call<UserDetails> call = ApiConnector.getInstance().getApi().loginUser(user);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Response<UserDetails> response) {
                int statusCode = response.code();
                UserDetails user = response.body();
                Session.setUserDetails(user);
                onComplete.onComplete(true, statusCode);
                Log.d(LOG_TAG, "loginUser statusCode " + statusCode + " with response " + user);
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