package com.droid.floatboat.collabcart.models;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    private int productId;
    @SerializedName("name")
    private String productName;
    private int minPrice;
    private int maxPrice;

    @SerializedName("defaultImage")
    private ImagePOJO image;

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ImagePOJO getImage() {
        return image;
    }

    public String getImageUrl(){
        if(image!=null){
            return image.getImageUrl();
        }
        return null;
    }

    public void setImage(ImagePOJO image) {
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    private class ImagePOJO {

        @SerializedName("url")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

  }