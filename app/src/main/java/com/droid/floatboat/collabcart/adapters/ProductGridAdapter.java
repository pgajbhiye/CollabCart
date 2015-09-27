package com.droid.floatboat.collabcart.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droid.floatboat.collabcart.R;
import com.droid.floatboat.collabcart.interfaces.ItemClickListener;
import com.droid.floatboat.collabcart.models.Product;
import com.droid.floatboat.collabcart.models.Products;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Pallavi
 */
public class ProductGridAdapter extends RecyclerView.Adapter<ProductHolder> {


    private Context context;
    ArrayList<Product> product;
    private ItemClickListener itemClickListener;

    public ProductGridAdapter(Context context, Products products, ItemClickListener itemClickListener) {
        this.context = context;
        product = products != null ? products.getProducts() : null;
        this.itemClickListener = itemClickListener;

    }

    public void updateProductsList(Products products){
        if(product!=null){
            product.clear();
        }
        product = products != null ? products.getProducts() : null;
        this.notifyDataSetChanged();

    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.products, null);
        ProductHolder productHolder = new ProductHolder(layoutView);
        productHolder.setItemClickListener(itemClickListener);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(ProductHolder productHolder, int position) {
        if (getItemCount() > 0) {
            productHolder.productName.setText(product.get(position).getProductName() + "   " + product.get(position).getMinPrice());
            Log.d("", "url " + product.get(position).getImageUrl() + "?width=168&height=145&trim=0&quality=80");
            Picasso.with(context)
                    .load(product.get(position).getImageUrl() + "?width=168&height=145&trim=0&quality=80")
                            // .placeholder(R.drawable.nopreview)
                    .noFade()
                            // .error(R.drawable.nopreview)
                    .into(productHolder.productPhoto);
        }else{
            productHolder.noPreview.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return product != null ? product.size() : 0;
    }
}


class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView productName;
    ImageView productPhoto;
    TextView noPreview;
    ItemClickListener itemClickListener;

    public ProductHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        productName = (TextView) itemView.findViewById(R.id.product_name);
        productPhoto = (ImageView) itemView.findViewById(R.id.product_photo);
        noPreview = (TextView) itemView.findViewById(R.id.nopreview);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onItemClick(getPosition());
    }
}