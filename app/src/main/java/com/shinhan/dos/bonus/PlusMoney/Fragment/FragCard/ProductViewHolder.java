package com.shinhan.dos.bonus.PlusMoney.Fragment.FragCard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ImageView product_img;
    TextView product_name;
    TextView product_subscribe;
    public ProductViewHolder(View itemView) {
        super(itemView);
        product_img = (ImageView)itemView.findViewById(R.id.card_img);
        product_name = (TextView)itemView.findViewById(R.id.product_name);
        product_subscribe = (TextView)itemView.findViewById(R.id.product_subscribe);

    }

}