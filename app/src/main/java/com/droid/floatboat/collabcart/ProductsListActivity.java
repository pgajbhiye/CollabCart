package com.droid.floatboat.collabcart;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.droid.floatboat.collabcart.adapters.ProductGridAdapter;
import com.droid.floatboat.collabcart.data.Session;
import com.droid.floatboat.collabcart.interfaces.ItemClickListener;
import com.droid.floatboat.collabcart.models.Product;
import com.droid.floatboat.collabcart.models.Products;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;
import com.droid.floatboat.collabcart.utils.CartUtils;

/**
 * @author Pallavi
 */

public class ProductsListActivity extends ActionBarActivity {

    private static final String LOG_TAG = ProductsListActivity.class.getName();
    private int productId;
    int categoryIdFromBundle = 0;
    int categoryId = 0;
    RecyclerView recyclerView;
    ProductGridAdapter gridAdapter;
    ItemClickListener itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_categories);
        Bundle bundle;
        if ((bundle = getIntent().getExtras()) != null) {
            categoryIdFromBundle = bundle.getInt("categoryId", 0);
        }

        Log.d(LOG_TAG, "Passed categoryId " + categoryIdFromBundle);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);

itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                int selectedProduct = pos;
                Intent intent = new Intent(ProductsListActivity.this, ProductDetailsActivity.class);
                intent.putExtra("productIndex", selectedProduct);
                startActivity(intent);
            }
        };


        // The number of Columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (categoryIdFromBundle != categoryId) {
            CartUtils.showProgress(this);
            Products.fetchProducts(Session.getUserId(), categoryIdFromBundle, new OnCompleteCallBack() {
                @Override
                public void onComplete(boolean success, int statusCode) {
                    CartUtils.hideProgress();
                    gridAdapter = new ProductGridAdapter(ProductsListActivity.this, Session.getProducts(), itemClickListener);
                    recyclerView.setAdapter(gridAdapter);

                   // gridAdapter.updateProductsList(Session.getProducts());

                    if (success) {
                        categoryId = categoryIdFromBundle;
                    }
                    Log.d(LOG_TAG, "Fetch Products success?" + success + " statusCode " + statusCode);

                }
            });
        }

    }

}
