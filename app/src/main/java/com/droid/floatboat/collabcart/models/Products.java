package com.droid.floatboat.collabcart.models;

import com.droid.floatboat.collabcart.net.OnCompleteCallBack;
import com.droid.floatboat.collabcart.services.ProductsService;

import java.util.ArrayList;

/**
 * Created by Pallavi on 27-Sep-15.
 */
public class Products {


    private ArrayList<Product> products;


    public static void fetchProducts(String userId, int categoryId, OnCompleteCallBack onComplete) {
        ProductsService.fetchProducts(userId, categoryId, onComplete);

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Products setProducts(ArrayList<Product> products) {
        this.products = products;
        return this;
    }
}
