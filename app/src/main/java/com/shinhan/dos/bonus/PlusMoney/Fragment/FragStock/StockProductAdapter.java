package com.shinhan.dos.bonus.PlusMoney.Fragment.FragStock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class StockProductAdapter extends RecyclerView.Adapter<StockViewHolder>{
        ArrayList<StockRecommandData> stockData;
        View.OnClickListener clickListener;

public StockProductAdapter(ArrayList<StockRecommandData> stockData, View.OnClickListener clickListener) {
        this.stockData = stockData;
        this.clickListener = clickListener;
        }

@Override
public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_stock_item, parent, false);

        StockViewHolder viewHolder = new StockViewHolder(view);
        view.setOnClickListener(clickListener);
        return viewHolder;
        }

@Override
public void onBindViewHolder(StockViewHolder holder, int position) {

        holder.name.setText(stockData.get(position).name);
        holder.trdprc.setText(stockData.get(position).trdprc+"원");
        holder.invest_info.setText(stockData.get(position).invest_info);

        }

@Override
public int getItemCount() {
        return stockData != null ? stockData.size() : 0;
        }
}
