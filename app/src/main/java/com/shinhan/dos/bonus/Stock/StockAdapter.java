package com.shinhan.dos.bonus.Stock;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<StockViewHolder> {
    ArrayList<ArrayList<StockData>> datas;
    View.OnClickListener clickListener;

    public StockAdapter(ArrayList<ArrayList<StockData>> datas, View.OnClickListener clickListener) {
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



        holder.stock_name.setText(datas.get(0).get(position).name);
        if(datas.get(0).get(position).uv_diff_ratio.charAt(0)=='-'){

            holder.stock_change_rate.setTextColor(ContextCompat.getColor(holder.stock_change_rate.getContext(), R.color.colorStockDown));
            //holder.stock_change_rate.setTextColor(Color.parseColor((R.color.colorStockDown)));
            holder.stock_currentvalue.setTextColor(ContextCompat.getColor(holder.stock_currentvalue.getContext(), R.color.colorStockDown));
            //holder.stock_currentvalue.setTextColor(Color.parseColor(String.valueOf(R.color.colorStockDown)));


        }
        holder.stock_change_rate.setText(datas.get(0).get(position).uv_diff_ratio+"%");
        holder.stock_currentvalue.setText(String.valueOf(datas.get(0).get(position).evlt_amt));
        holder.stock_subscribe1.setText(String.valueOf(datas.get(0).get(position).news_titl));
        //holder.stock_subscribe1.setText(String.valueOf(datas.get(position).stock_subscribe2));
        if(datas.get(0).get(position).deduction==0){ // 공제 대상이 아닌경우
            holder.stock_item_box.setBackgroundResource(R.drawable.bg_stock_list_f);
        }else{
            holder.stock_item_box.setBackgroundResource(R.drawable.bg_stock_list_t);
        }

    }

    @Override
    public int getItemCount() {
        return datas.get(0) != null ? datas.get(0).size() : 0;
    }



}
