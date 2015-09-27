package com.droid.floatboat.collabcart.data;


import com.droid.floatboat.collabcart.collbcartsdk.CollabCart;
import com.droid.floatboat.collabcart.models.BarchartData;
import com.droid.floatboat.collabcart.models.Categories;
import com.droid.floatboat.collabcart.models.Products;
import com.droid.floatboat.collabcart.models.UserDetails;

import java.util.ArrayList;

public class Session {

    private static UserDetails userDetails;
    private static Categories categories;
    private static Products products;
    private static CollabCart collabCart;
    private static ArrayList<BarchartData> productViewsData, productPurchaseData;

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

    public static void setProductViewsData(ArrayList<BarchartData> pvData){
        productViewsData = pvData;
    }

    public static ArrayList<BarchartData> getProductViewsData(){
        return productViewsData;
    }

    public static void setProductPurchaseData(ArrayList<BarchartData> pvData){
        productPurchaseData = pvData;
    }

    public static ArrayList<BarchartData> getProductPurchaseData(){
        return productPurchaseData;
    }

    public static void destroy(){
        if(collabCart!=null){
            collabCart.disconnect();
        }
    }

    public static CollabCart getCollabCart(){
        return collabCart;
    }

}