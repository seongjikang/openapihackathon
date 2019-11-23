package com.shinhan.dos.bonus.PlusMoney.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{
        ArrayList<ProductData> productData;
        View.OnClickListener clickListener;

    public ProductAdapter(ArrayList<ProductData> authorDatas, View.OnClickListener clickListener) {
        this.productData = authorDatas;
        this.clickListener = clickListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_item, parent, false);

        ProductViewHolder viewHolder = new ProductViewHolder(view);
        view.setOnClickListener(clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        holder.product_name.setText(productData.get(position).product_name);
        holder.product_subscribe.setText(productData.get(position).product_subscribe);


    }

    @Override
    public int getItemCount() {
        return productData != null ? productData.size() : 0;
    }
}