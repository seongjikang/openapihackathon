package com.shinhan.dos.bonus.Stock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.shinhan.dos.bonus.R;

import java.util.ArrayList;

public class StockInquiryActivity extends AppCompatActivity {

    private RecyclerView stockRecyclerview;
    private StockAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<StockData> stockData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_inquiry);

        stockRecyclerview = (RecyclerView)findViewById(R.id.recyclerview_stock);

        //레이아웃 매니저 설정
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        stockRecyclerview.setLayoutManager(linearLayoutManager);


        stockData.add(new StockData(0, "코카콜라", 1000, "+20%", "설명이다 어쩌구 저쩌구", "설명이다 어쩌구 저쩌구 얼씨구 절씨구 "));
        stockData.add(new StockData(1, "코카콜라", 1000, "+20%", "설명이다 어쩌구 저쩌구", "설명이다 어쩌구 저쩌구 얼씨구 절씨구 "));
        stockData.add(new StockData(0, "코카콜라", 1000, "+20%", "설명이다 어쩌구 저쩌구", "설명이다 어쩌구 저쩌구 얼씨구 절씨구 "));
        stockData.add(new StockData(1, "코카콜라", 1000, "+20%", "설명이다 어쩌구 저쩌구", "설명이다 어쩌구 저쩌구 얼씨구 절씨구 "));
        stockData.add(new StockData(0, "코카콜라", 1000, "+20%", "설명이다 어쩌구 저쩌구", "설명이다 어쩌구 저쩌구 얼씨구 절씨구 "));
        stockData.add(new StockData(1, "코카콜라", 1000, "+20%", "설명이다 어쩌구 저쩌구", "설명이다 어쩌구 저쩌구 얼씨구 절씨구 "));
        stockData.add(new StockData(0, "코카콜라", 1000, "+20%", "설명이다 어쩌구 저쩌구", "설명이다 어쩌구 저쩌구 얼씨구 절씨구 "));
        stockData.add(new StockData(1, "코카콜라", 1000, "+20%", "설명이다 어쩌구 저쩌구", "설명이다 어쩌구 저쩌구 얼씨구 절씨구 "));
//        poemdatas.add(new PoemDatas("제목", "반가워", "", "서문이", startDate));
//        poemdatas.add(new PoemDatas("제목", "이름이뭐니", "", "서문이", startDate));
//        poemdatas.add(new PoemDatas("제목", "잘가", "", "서문이", startDate));

        adapter = new StockAdapter(stockData, clickEvent);
        stockRecyclerview.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    public View.OnClickListener clickEvent = new View.OnClickListener() {
        public void onClick(View v) {
            final int itemPosition = stockRecyclerview.getChildPosition(v);
            //Toast.makeText(getContext(), itemPosition + "번 리스트 클릭!!", Toast.LENGTH_SHORT).show();




        }
    };

}
