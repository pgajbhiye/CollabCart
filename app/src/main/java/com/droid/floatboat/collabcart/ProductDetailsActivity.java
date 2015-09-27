package com.droid.floatboat.collabcart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.droid.floatboat.collabcart.Charts.BarchartHelper;
import com.droid.floatboat.collabcart.Charts.ChartDataProvider;
import com.droid.floatboat.collabcart.data.Session;
import com.droid.floatboat.collabcart.interfaces.ItemClickListener;
import com.droid.floatboat.collabcart.models.BarchartData;
import com.droid.floatboat.collabcart.models.Product;
import com.droid.floatboat.collabcart.models.User;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;
import com.droid.floatboat.collabcart.services.ProductStatsService;
import com.droid.floatboat.collabcart.utils.CartUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author Pallavi
 */

public class ProductDetailsActivity extends AppCompatActivity {
    private final static String LOG_TAG = ProductDetailsActivity.class.getName();

    protected BarChart mViewCountChart, mPurchaseCountChart;
    protected BarchartHelper mViewCountChartHelper, mPurchaseCountChartHelper;
    private Product product;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            index = bundle.getInt("productIndex", 0);
        }

        product = Session.getProducts().getProducts().get(index);

        mViewCountChart = (BarChart) findViewById(R.id.viewcountchart);
        mPurchaseCountChart = (BarChart) findViewById(R.id.purchasecountchart);
        ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(this)
                .load(product.getImageUrl()+ "?width=130&height=130&trim=0&quality=80")
                        // .placeholder(R.drawable.nopreview)
                        // .error(R.drawable.nopreview)
                .into(image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(product.getProductName()); //ad product name here
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.app_color));
        collapsingToolbar.setContentScrimColor(this.getResources().getColor(R.color.app_color));

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.productviews);
        ab.setDisplayHomeAsUpEnabled(true);

        loadViewsCountChart();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailsActivity.this, "fab clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void loadPurchaseCountChart() {
        mPurchaseCountChartHelper = new BarchartHelper(mPurchaseCountChart, "Purchases per Day", new ChartDataProvider() {
            @Override
            public void onData() {
                ProductStatsService.fetchPurchaseCount(Session.getUserId(), product.getProductId(), new OnCompleteCallBack() {
                    @Override
                    public void onComplete(boolean success, int statusCode) {
                        if (success) {
                            ArrayList<BarchartData> purchaseData = Session.getProductPurchaseData();
                            if (purchaseData != null)
                                mPurchaseCountChartHelper.setData(purchaseData);
                        }
                        Log.d(LOG_TAG, "Fetch purchase count Chart data success" + success + " statusCode " + statusCode);
                    }
                });
            }
        });
    }

    private void loadViewsCountChart() {
        mViewCountChartHelper = new BarchartHelper(mViewCountChart, "Views per Day", new ChartDataProvider() {
            @Override
            public void onData() {
                ProductStatsService.fetchViewsCount(Session.getUserId(), product.getProductId(), new OnCompleteCallBack() {
                    @Override
                    public void onComplete(boolean success, int statusCode) {
                        if (success) {
                            ArrayList<BarchartData> viewCountsData = Session.getProductViewsData();
                            if (viewCountsData != null)
                                mViewCountChartHelper.setData(viewCountsData);
                        }
                        Log.d(LOG_TAG, "Fetch View count Chart data success" + success + " statusCode " + statusCode);
                        loadPurchaseCountChart();
                    }
                });
            }
        });
    }


}

