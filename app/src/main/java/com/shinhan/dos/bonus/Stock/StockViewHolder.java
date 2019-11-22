package com.shinhan.dos.bonus.Stock;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

public class StockViewHolder extends RecyclerView.ViewHolder {

    View stock_item_box;
    TextView stock_name;
    TextView stock_currentvalue;
    TextView stock_change_rate;
    TextView stock_subscribe1;
    TextView stock_subscribe2;



    public StockViewHolder(View itemView){
        super(itemView);

        stock_item_box = itemView.findViewById(R.id.stock_item_box);
        stock_name = (TextView)itemView.findViewById(R.id.stock_name);;
        stock_currentvalue = (TextView)itemView.findViewById(R.id.stock_currentvalue);;
        stock_change_rate = (TextView)itemView.findViewById(R.id.stock_change_rate);;
        stock_subscribe1= (TextView)itemView.findViewById(R.id.stock_subscribe1);;
        stock_subscribe2= (TextView)itemView.findViewById(R.id.stock_subscribe2);;

    }
}
