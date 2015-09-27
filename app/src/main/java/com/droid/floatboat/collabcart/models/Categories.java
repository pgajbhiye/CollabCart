package com.droid.floatboat.collabcart.models;


import com.droid.floatboat.collabcart.config.Config;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;
import com.droid.floatboat.collabcart.services.CategoriesService;

import java.util.ArrayList;

public class Categories {

    private ArrayList<Category> categories;


    public static void fetchCategories(String userId, OnCompleteCallBack onComplete) {
        CategoriesService.fetchCategories(userId, onComplete);

    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public Categories setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        return this;
    }

    public int mapCategoryByTag(String tag) {
        //iterate over arraylsit and match click id
        for (Category c : categories) {
            if (c.getCategoryName().equals(tag)) {
                return c.getCategoryId();
            }
        }
        return Config.DEFAULT_CATEGORY;


    }
}
