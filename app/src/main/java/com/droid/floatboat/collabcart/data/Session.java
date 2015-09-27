package com.droid.floatboat.collabcart.data;


import com.droid.floatboat.collabcart.collbcartsdk.CollabCart;
import com.droid.floatboat.collabcart.models.Categories;
import com.droid.floatboat.collabcart.models.Products;
import com.droid.floatboat.collabcart.models.UserDetails;

public class Session {

    private static UserDetails userDetails;
    private static Categories categories;
    private static Products products;
    private static CollabCart collabCart;

    public static void setUserDetails(UserDetails loginUser) {
        userDetails = loginUser;
    }

    public static UserDetails getUserDetails() {
        return userDetails;
    }

    public static String getUserId() {
        return userDetails.getGuid();
    }

    public static void setCategories(Categories clist) {
        categories = clist;
    }

    public static Categories getCategories() {
        return categories;
    }


    public static Products getProducts() {
        return products;
    }

    public static void setProducts(Products products) {
        Session.products = products;
    }

    public static CollabCart initiateCollabCart(){
        collabCart = new CollabCart();
        collabCart.connect();
        return collabCart;
    }

    public static void destroy(){
        if(collabCart!=null){
            collabCart.disconnect();
        }
    }

}