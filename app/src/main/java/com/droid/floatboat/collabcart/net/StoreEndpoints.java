package com.droid.floatboat.collabcart.net;

import com.droid.floatboat.collabcart.models.BarchartData;
import com.droid.floatboat.collabcart.models.Category;
import com.droid.floatboat.collabcart.models.Product;
import com.droid.floatboat.collabcart.models.User;
import com.droid.floatboat.collabcart.models.UserDetails;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface StoreEndpoints{


    @POST("/login")
    Call<UserDetails> loginUser(@Body User userEmail);

    @GET("api/{userId}/categories")
    Call<ArrayList<Category>> fetchCategories(@Path("userId") String userId);

    @GET("api/{userId}/categories/{categoryId}/products")
    Call<ArrayList<Product>> getProducts(@Path("userId") String userId, @Path("categoryId") int categoryId);

    @GET("api/{userId}/products/viewed/{productId}")
    Call<ArrayList<BarchartData>> getProductViews(@Path("userId") String userId, @Path("productId") int productId);

    @GET("api/{userId}/products/purchased/{productId}")
    Call<ArrayList<BarchartData>> getProductPurchases(@Path("userId") String userId, @Path("productId") int productId);

}

