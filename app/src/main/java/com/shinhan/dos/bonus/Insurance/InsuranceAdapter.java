package com.shinhan.dos.bonus.Insurance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceViewHolder> {
        ArrayList<InsuranceData> datas;
        View.OnClickListener clickListener;

public InsuranceAdapter(ArrayList<InsuranceData> datas, View.OnClickListener clickListener) {
        this.datas = datas;
        this.clickListener = clickListener;
        }


@Override
public InsuranceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_insurance_item, parent, false);

        InsuranceViewHolder viewHolder = new InsuranceViewHolder(view);
        view.setOnClickListener(clickListener);
        return viewHolder;
        }

@Override
public void onBindViewHolder(InsuranceViewHolder holder, int position) {


        holder.insurance_name_txt.setText(datas.get(position).lifename);
        holder.insurance_type_txt.setText(datas.get(position).description);
        holder.total_insurance_amount.setText(String.valueOf(datas.get(position).amount));
        if(datas.get(position).isDeduction.equals("0")){ // 공제 대상이 아닌경우
        holder.insurance_item_box.setBackgroundResource(R.drawable.bg_insurance_list_t);
        }else{
        holder.insurance_item_box.setBackgroundResource(R.drawable.bg_stock_list_f);
        }

        }

@Override
public int getItemCount() {
        return datas != null ? datas.size() : 0;
        }



}
