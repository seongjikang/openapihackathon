package com.shinhan.dos.bonus.PlusMoney.Fragment.FragCard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{
        ArrayList<ProductData> productData;
        View.OnClickListener clickListener;

    public ProductAdapter(ArrayList<ProductData> cardData, View.OnClickListener clickListener) {
        this.productData = cardData;
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

        switch (position%5){
            case 0:
                holder.product_img.setImageResource(R.drawable.card1);
                break;
            case 1:
                holder.product_img.setImageResource(R.drawable.card2);
                break;
            case 2:
                holder.product_img.setImageResource(R.drawable.card3);
                break;
            case 3:
                holder.product_img.setImageResource(R.drawable.card4);
                break;
            case 4:
                holder.product_img.setImageResource(R.drawable.card5);
                break;
        }

        holder.product_name.setText(productData.get(position).cardName);
        if(productData.get(position).cardBonus.size()>0){
            if(productData.get(position).cardBonus.get(0).title.equals("")){
                holder.product_subscribe.setText(productData.get(position).cardBonus.get(0).content+ " ... 외 "+productData.get(position).cardBonus.size()+"가지 혜택");
            }else{
                holder.product_subscribe.setText(productData.get(position).cardBonus.get(0).title+ " ... 외 "+productData.get(position).cardBonus.size()+"가지 혜택");
            }

        }



    }

    @Override
    public int getItemCount() {
        return productData != null ? productData.size() : 0;
    }
}