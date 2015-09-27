package com.droid.floatboat.collabcart.models;

import com.google.gson.annotations.SerializedName;

public class Category {


    @SerializedName("name")
    private String categoryName;

    @SerializedName("id")
    private int categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
