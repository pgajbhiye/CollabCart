package com.droid.floatboat.collabcart.net;

import com.droid.floatboat.collabcart.config.Config;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Pallavi on 26-Sep-15.
 */
public class ApiConnector {

    private static ApiConnector connector = null;

    StoreEndpoints api;

    public ApiConnector(StoreEndpoints api) {
        this.api = api;
    }

    public StoreEndpoints getApi() {
        return api;
    }

    public static ApiConnector getInstance() {
        if (connector == null) {
            synchronized (ApiConnector.class) {
                if (connector == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Config.SERVER_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    StoreEndpoints api =
                            retrofit.create(StoreEndpoints.class);
                    connector = new ApiConnector(api);
                }
            }
        }
        return connector;
    }
}
