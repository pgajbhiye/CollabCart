package com.droid.floatboat.collabcart;

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
import com.droid.floatboat.collabcart.models.Products;
import com.droid.floatboat.collabcart.net.OnCompleteCallBack;
import com.droid.floatboat.collabcart.utils.CartUtils;

/**
 * @author Pallavi
 */

public class ProductsListActivity extends ActionBarActivity {

    private static final String LOG_TAG = ProductsListActivity.class.getName();
    private int categoryId;
    int categoryIdFromBundle = 0;
    RecyclerView recyclerView;
    ProductGridAdapter gridAdapter;

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

        // The number of Columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        gridAdapter = new ProductGridAdapter(this,Session.getProducts());
        recyclerView.setAdapter(gridAdapter);


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
                    gridAdapter.updateProductsList(Session.getProducts());
                    if (success) {
                        categoryId = categoryIdFromBundle;
                    }else{
                        Toast.makeText(ProductsListActivity.this,"Unable to fetch products",Toast.LENGTH_SHORT).show();
                    }
                    Log.d(LOG_TAG, "Fetch Products success?" + success + " statusCode " + statusCode);

                }
            });
        }

    }

}
