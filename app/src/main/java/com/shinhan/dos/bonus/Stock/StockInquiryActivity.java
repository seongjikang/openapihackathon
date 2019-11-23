package com.shinhan.dos.bonus.Stock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.R;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockInquiryActivity extends AppCompatActivity {

    private RecyclerView stockRecyclerview;
    private StockAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ArrayList<StockData>> stockData = new ArrayList<>();
    ResultDataBody result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_inquiry);

        stockRecyclerview = (RecyclerView)findViewById(R.id.recyclerview_stock);

        //레이아웃 매니저 설정
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        stockRecyclerview.setLayoutManager(linearLayoutManager);

        Map params = new LinkedHashMap<>();
        params.put("hpno", "01071444074");
        DataResult dataResult = new DataResultImpl();
        dataResult.getInvestUsage(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
                if(response.isSuccessful()){

                    Gson gson = new Gson();
                    ResultDataBody result = gson.fromJson(response.body().get("dataBody"), ResultDataBody.class);

                    result.getHoldingStockList();
                    stockData.addAll(result.holdingStockList);
                    adapter = new StockAdapter(stockData, clickEvent);
                    stockRecyclerview.setAdapter(adapter);

                    adapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("STOCKJSON", "FAIL....");

            }
        }, params);



    }

    public View.OnClickListener clickEvent = new View.OnClickListener() {
        public void onClick(View v) {
            final int itemPosition = stockRecyclerview.getChildPosition(v);
            //Toast.makeText(getContext(), itemPosition + "번 리스트 클릭!!", Toast.LENGTH_SHORT).show();




        }
    };

}
