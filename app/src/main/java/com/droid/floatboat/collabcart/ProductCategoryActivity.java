package com.droid.floatboat.collabcart;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.droid.floatboat.collabcart.adapters.ProductCategoryAdapter;
import com.droid.floatboat.collabcart.data.Session;
import com.droid.floatboat.collabcart.interfaces.ItemClickListener;
import com.droid.floatboat.collabcart.models.CardViewData;
import com.droid.floatboat.collabcart.models.Categories;
import com.droid.floatboat.collabcart.models.Products;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;
import com.droid.floatboat.collabcart.utils.CartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pallavi
 */

public class ProductCategoryActivity extends Activity {

    private static final String LOG_TAG = ProductCategoryActivity.class.getName();
    private static List<CardViewData> list = new ArrayList<CardViewData>();
    int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_categories);
        CartUtils.showProgress(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        setListing();
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(ProductCategoryActivity.this, "Clicked " + pos, Toast.LENGTH_SHORT).show();
                categoryId = Session.getCategories().mapCategoryByTag(list.get(pos).getCategoryTag());
                //send categoryId in bundle to load each products page

            }
        };

        ProductCategoryAdapter productCategoryAdapter = new ProductCategoryAdapter(this, list, itemClickListener);
        recyclerView.setAdapter(productCategoryAdapter);

        Categories.fetchCategories(Session.getUserId(), new OnCompleteCallBack() {
            @Override
            public void onComplete(boolean success, int statusCode) {
                CartUtils.hideProgress();
                if (success) {

                    Products.fetchProducts(Session.getUserId(), categoryId, new OnCompleteCallBack() {
                        @Override
                        public void onComplete(boolean success, int statusCode) {
                            Log.d(LOG_TAG, "Fetch Products success" + success + " statusCode " + statusCode);

                        }
                    });


                }
                Log.d(LOG_TAG, "Fetch Categories success" + success + " statusCode " + statusCode);
            }
        });


    }

    public void setListing() {
        list.add(new CardViewData().setCategoryName("Apparels").setCategoryTag("FemaleShirt").setCategoryPhoto(R.drawable.images));
        list.add(new CardViewData().setCategoryName("Electronics").setCategoryTag("FemaleJeans").setCategoryPhoto(R.drawable.electronic));
        list.add(new CardViewData().setCategoryName("Beauty").setCategoryTag("FemaleShoes").setCategoryPhoto(R.drawable.beauty));
        list.add(new CardViewData().setCategoryName("Electronics").setCategoryTag("MaleShirt").setCategoryPhoto(R.drawable.electronic));
        list.add(new CardViewData().setCategoryName("Utilities").setCategoryTag("MaleJeans").setCategoryPhoto(R.drawable.images));
        list.add(new CardViewData().setCategoryName("Luxury").setCategoryTag("MaleShoes").setCategoryPhoto(R.drawable.electronic));
    }

}
