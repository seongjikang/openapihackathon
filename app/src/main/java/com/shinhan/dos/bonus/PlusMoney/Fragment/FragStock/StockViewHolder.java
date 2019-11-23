package com.shinhan.dos.bonus.PlusMoney.Fragment.FragStock;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

public class StockViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView trdprc;
    TextView invest_info;

    public StockViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.product_name);
        trdprc = (TextView) itemView.findViewById(R.id.trdprc);
        invest_info = (TextView) itemView.findViewById(R.id.invest_info);

    }

}
