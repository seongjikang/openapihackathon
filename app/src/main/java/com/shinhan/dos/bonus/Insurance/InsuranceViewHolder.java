package com.shinhan.dos.bonus.Insurance;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shinhan.dos.bonus.R;

public class InsuranceViewHolder extends RecyclerView.ViewHolder {

    View insurance_item_box;
    TextView insurance_name_txt;
    TextView insurance_type_txt;
    TextView total_insurance_amount;
    TextView minus_tax_amount;



    public InsuranceViewHolder(View itemView){
        super(itemView);

        insurance_item_box = itemView.findViewById(R.id.insurance_item_box);
        insurance_name_txt = (TextView)itemView.findViewById(R.id.insurance_name_txt);;
        insurance_type_txt = (TextView)itemView.findViewById(R.id.insurance_type_txt);;
        total_insurance_amount = (TextView)itemView.findViewById(R.id.total_insurance_amount);;
        minus_tax_amount= (TextView)itemView.findViewById(R.id.minus_tax_amount);;

    }
}
