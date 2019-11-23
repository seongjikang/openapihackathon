package com.shinhan.dos.bonus.Stock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.QuestionActivity;
import com.shinhan.dos.bonus.R;
import com.shinhan.dos.bonus.data.DataResult;
import com.shinhan.dos.bonus.data.DataResultImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.*;

public class StockInquiryActivity extends AppCompatActivity {

    private RecyclerView stockRecyclerview;
    private StockAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ArrayList<StockData>> stockData = new ArrayList<>();
    ResultDataBody result;

    ImageView question_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_inquiry);

        question_btn =(ImageView)findViewById(R.id.question_img);
        question_btn.setOnClickListener(question);


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

    public OnClickListener clickEvent = new OnClickListener() {
        public void onClick(View v) {
            final int itemPosition = stockRecyclerview.getChildPosition(v);
            //Toast.makeText(getContext(), itemPosition + "번 리스트 클릭!!", Toast.LENGTH_SHORT).show();




        }
    };
    public OnClickListener question = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
            intent.putExtra("to","주식");
            startActivity(intent);

        }
    };

}
