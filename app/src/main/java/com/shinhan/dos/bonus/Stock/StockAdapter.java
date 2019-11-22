package com.shinhan.dos.bonus.Stock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<StockViewHolder> {
    ArrayList<StockData> datas;
    View.OnClickListener clickListener;

    public StockAdapter(ArrayList<StockData> datas, View.OnClickListener clickListener) {
        this.datas = datas;
        this.clickListener = clickListener;
    }


    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_stock_item, parent, false);

        StockViewHolder viewHolder = new StockViewHolder(view);
        view.setOnClickListener(clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {


        holder.stock_name.setText(datas.get(position).stock_name);
        holder.stock_change_rate.setText(datas.get(position).stock_change_rate);
        holder.stock_currentvalue.setText(String.valueOf(datas.get(position).stock_currentvalue));
        holder.stock_subscribe1.setText(String.valueOf(datas.get(position).stock_subscribe1));
        holder.stock_subscribe1.setText(String.valueOf(datas.get(position).stock_subscribe2));
        if(datas.get(position).is_stock_valid==0){ // 공제 대상이 아닌경우
            holder.stock_item_box.setBackgroundResource(R.drawable.bg_stock_list_f);
        }else{
            holder.stock_item_box.setBackgroundResource(R.drawable.bg_stock_list_t);
        }

    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }



}
