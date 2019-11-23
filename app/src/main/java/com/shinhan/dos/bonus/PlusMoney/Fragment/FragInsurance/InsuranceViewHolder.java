package com.shinhan.dos.bonus.PlusMoney.Fragment.FragInsurance;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

public class InsuranceViewHolder extends RecyclerView.ViewHolder {

    TextView intyNm;
    TextView inInfo;

    public InsuranceViewHolder(View itemView) {
        super(itemView);

        intyNm = (TextView) itemView.findViewById(R.id.intyNm);
        inInfo = (TextView) itemView.findViewById(R.id.inInfo);

    }
}