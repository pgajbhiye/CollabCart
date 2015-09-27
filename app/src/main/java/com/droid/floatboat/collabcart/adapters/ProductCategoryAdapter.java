package com.droid.floatboat.collabcart.adapters;


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
import com.droid.floatboat.collabcart.models.CardViewData;

import java.util.HashMap;
import java.util.List;


/**
 * @author Pallavi
 */

public class ProductCategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private static final String LOG_TAG = ProductCategoryAdapter.class.getName();
    public static HashMap<String, Integer> categoryMap = new HashMap<String, Integer>();
    private List<CardViewData> list;
    private Context context;
    private ItemClickListener itemClickListener;

    public ProductCategoryAdapter(Context context, List<CardViewData> list,ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener = itemClickListener;
    }


    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_category, null);
        CategoryHolder categoryHolder = new CategoryHolder(layoutView);
        categoryHolder.setItemClickListener(itemClickListener);
        return categoryHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(CategoryHolder categoryHolder, int position) {
        categoryHolder.categoryName.setText(list.get(position).getCategoryName());
        categoryHolder.categoryPhoto.setImageResource(list.get(position).getCategoryPhoto());

    }


}

class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView categoryName;
    ImageView categoryPhoto;
    ItemClickListener itemClickListener;

    public CategoryHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryName = (TextView) itemView.findViewById(R.id.category_name);
        categoryPhoto = (ImageView) itemView.findViewById(R.id.category_photo);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onItemClick(getPosition());
    }
}



