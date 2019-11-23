package com.shinhan.dos.bonus.PlusMoney.Fragment.FragInsurance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class InsuranceProductAdapter extends RecyclerView.Adapter<InsuranceViewHolder>{
        ArrayList<InsuranceRecommandData> inData;
        View.OnClickListener clickListener;

public InsuranceProductAdapter(ArrayList<InsuranceRecommandData> inData, View.OnClickListener clickListener) {
        this.inData = inData;
        this.clickListener = clickListener;
        }

@Override
public InsuranceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_insurance_item, parent, false);

        InsuranceViewHolder viewHolder = new InsuranceViewHolder(view);
        view.setOnClickListener(clickListener);
        return viewHolder;
        }

@Override
public void onBindViewHolder(InsuranceViewHolder holder, int position) {

        holder.intyNm.setText(inData.get(position).intyNm);
        holder.inInfo.setText(inData.get(position).inInfo);



        }

@Override
public int getItemCount() {
        return inData != null ? inData.size() : 0;
        }
}
