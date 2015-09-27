package com.droid.floatboat.collabcart.net;

import com.droid.floatboat.collabcart.models.Category;
import com.droid.floatboat.collabcart.models.Product;
import com.droid.floatboat.collabcart.models.User;
import com.droid.floatboat.collabcart.models.UserDetails;
import com.droid.floatboat.collabcart.models.Products;


import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface StoreEndpoints{


    @POST("/login")
    Call<UserDetails> loginUser(@Body User userEmail);

    @GET("api/{userId}/categories")
    Call<ArrayList<Category>> fetchCategories(@Path("userId") String userId);

    @GET("api/{userId}/categories/{categoryId}/products")
    Call<ArrayList<Product>> getProducts(@Path("userId") String userId, @Path("categoryId") int categoryId);



}

