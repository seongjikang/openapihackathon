package com.shinhan.dos.bonus.Insurance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.shinhan.dos.bonus.R;
import com.shinhan.dos.bonus.Stock.StockAdapter;
import com.shinhan.dos.bonus.Stock.StockData;

import java.util.ArrayList;

public class InsuranceInquiryActivity extends AppCompatActivity {

    private RecyclerView insurancekRecyclerview;
    private InsuranceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<InsuranceData> insuranceData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_inquiry);
        insurancekRecyclerview = (RecyclerView) findViewById(R.id.recyclerview_insurance);

        //레이아웃 매니저 설정
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        insurancekRecyclerview.setLayoutManager(linearLayoutManager);


        insuranceData.add(new InsuranceData(0, "신한생명", "무배당 신한인터넷 정기보험", 230000, 11000));
        insuranceData.add(new InsuranceData(0, "신한생명", "무배당 신한인터넷 정기보험", 230000, 11000));
        insuranceData.add(new InsuranceData(0, "신한생명", "무배당 신한인터넷 정기보험", 230000, 11000));
        adapter = new InsuranceAdapter(insuranceData, clickEvent);
        insurancekRecyclerview.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    public View.OnClickListener clickEvent = new View.OnClickListener() {
        public void onClick(View v) {
            final int itemPosition = insurancekRecyclerview.getChildPosition(v);
            //Toast.makeText(getContext(), itemPosition + "번 리스트 클릭!!", Toast.LENGTH_SHORT).show();


        }
    };
}