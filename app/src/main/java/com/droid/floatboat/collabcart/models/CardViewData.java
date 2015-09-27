package com.droid.floatboat.collabcart.models;


public class CardViewData {

    private int categoryPhoto;
    private String categoryName;
    private String categoryTag;

    public CardViewData() {

    }

    public int getCategoryPhoto() {
        return categoryPhoto;
    }

    public CardViewData setCategoryPhoto(int categoryPhoto) {
        this.categoryPhoto = categoryPhoto;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }


    public CardViewData setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;

    }

    public String getCategoryTag() {
        return categoryTag;
    }

    public CardViewData setCategoryTag(String categoryTag) {
        this.categoryTag = categoryTag;
        return this;
    }
}




